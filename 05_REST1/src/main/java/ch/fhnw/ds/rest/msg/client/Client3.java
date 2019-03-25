package ch.fhnw.ds.rest.msg.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class Client3 {

	public static void main(String[] args) {
		Client c = ClientBuilder.newClient();
		WebTarget r = c.target("http://localhost:9998/msg");

		Form f = new Form();
		f.param("msg", "Hello from Client3");

		String result = r.request().accept(MediaType.TEXT_HTML).post(Entity.form(f)).toString();
		System.out.println(result);
	}

}
