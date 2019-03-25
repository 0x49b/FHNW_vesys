package ch.fhnw.ds.rest.msg.client;

import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// Dasselbe Programm wie bei Client1, nur dass das Resultat nicht nur als String (also nur der Body) sondern als Response
// zurückgegeben wird. Aus der Response werden neben dem Body auch die Header ausgelesen und ausgegeben.
public class Client1a {

	public static void main(String[] args) {
		Client c = ClientBuilder.newClient();
		WebTarget r = c.target("http://localhost:9998/msg");
		
		Response response1 = r.request().accept(MediaType.TEXT_PLAIN).get();
		System.out.println(MediaType.TEXT_PLAIN);
		printResponse(response1);

		Response response2 = r.request().accept(MediaType.APPLICATION_XML).get();
		System.out.println(MediaType.APPLICATION_XML);
		printResponse(response2);

		Response response3 = r.request().accept(MediaType.APPLICATION_JSON).get();
		System.out.println(MediaType.APPLICATION_JSON);
		printResponse(response3);

		Response response4 = r.request().accept("application/xstream").get();
		System.out.println("application/xstream");
		printResponse(response4);
	}

	static void printResponse(Response r) {
		for(Entry<String, List<String>> entry : r.getStringHeaders().entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		System.out.println(r.readEntity(String.class));
		System.out.println();
	}
}
