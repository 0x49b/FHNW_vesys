
package ch.fhnw.ds.rest.msg.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import ch.fhnw.ds.rest.msg.server.Msg;
import ch.fhnw.ds.rest.msg.server.XStreamProvider;

public class Client2 {

	public static void main(String[] args) throws Exception {
		Client c = ClientBuilder.newClient();
		c.register(XStreamProvider.class);
		
		WebTarget r = c.target("http://localhost:9998/msg");

		Msg msg = new Msg("Hello from Client2");
		r.request().put(Entity.entity(msg, "application/xstream"));

		Msg res;
		res = r.request().accept("application/xml").get(Msg.class);
		System.out.println(res);
		System.out.println(res.getText());
		System.out.println(res.getDate());

		System.out.println();
		Thread.sleep(1000);
		
		res = r.request().accept("application/xstream").get(Msg.class);
		System.out.println(res);
		System.out.println(res.getText());
		System.out.println(res.getDate());

		System.out.println();
		Thread.sleep(1000);
		
		res = r.request().accept("application/json").get(Msg.class);
		System.out.println(res);
		System.out.println(res.getText());
		System.out.println(res.getDate());
}
}

