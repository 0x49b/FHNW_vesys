package ch.fhnw.ds.rest.msg.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import ch.fhnw.ds.rest.msg.data.Msg;

public class Client1b {

	public static void main(String[] args) {
		Client c = ClientBuilder.newClient();
		WebTarget r = c.target("http://localhost:9998/msg");

		Msg responseXml = r.request().accept(MediaType.APPLICATION_XML).get(Msg.class);
		System.out.println(MediaType.APPLICATION_XML);
		System.out.println(responseXml);
		System.out.println(responseXml.getText());
		System.out.println(responseXml.getDate());
		System.out.println();

		Msg responseJson = r.request().accept(MediaType.APPLICATION_JSON).get(Msg.class);
		System.out.println(MediaType.APPLICATION_JSON);
		System.out.println(responseJson);
		System.out.println(responseJson.getText());
		System.out.println(responseJson.getDate());
		System.out.println();
	}

}
