package ch.fhnw.ds.rest.msg.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import ch.fhnw.ds.rest.msg.data.Msg;

public class Client2 {

	public static void main(String[] args) {
		Client c = ClientBuilder.newClient();
		WebTarget r = c.target("http://localhost:9998/msg");

		Msg msg = new Msg("Hello from Client2");

		r.request().put(Entity.entity(msg, MediaType.APPLICATION_JSON));
	}

}
