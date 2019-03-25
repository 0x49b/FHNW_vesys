package ch.fhnw.ds.rest.msg.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Client1 {

	public static void main(String[] args) {
		Client c = ClientBuilder.newClient();
		WebTarget r = c.target("http://localhost:9998/msg");

		String response1 = r.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println(MediaType.TEXT_PLAIN);
		System.out.println(response1);
		System.out.println();

		String response2 = r.request().accept(MediaType.APPLICATION_XML).get(String.class);
		System.out.println(MediaType.APPLICATION_XML);
		System.out.println(response2);
		System.out.println();

		String response3 = r.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(MediaType.APPLICATION_JSON);
		System.out.println(response3);
		System.out.println();

		String response4 = r.request().accept("application/xstream").get(String.class);
		System.out.println("application/xstream");
		System.out.println(response4);
	}

}
