package ch.fhnw.ds.rest.msg.client.resteasy;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.fhnw.ds.rest.msg.server.Msg;
import ch.fhnw.ds.rest.msg.server.XStreamProvider;

public class Client1 {

	public static void main(String[] args) throws InterruptedException {
		Client client = ClientBuilder.newClient();
		client.register(XStreamProvider.class);
		WebTarget target = client.target("http://localhost:9998");
		
		ResteasyWebTarget rtarget = (ResteasyWebTarget)target;
		MsgResource service = rtarget.proxy(MsgResource.class);

		Msg res;
		String s;

		s = service.getPlain();
		System.out.println("text/plain");
		System.out.println(s);

		System.out.println("GET application/xml");
		res = service.getXml();
		System.out.println(res.getText());
		System.out.println(res.getDate());

		System.out.println("\nGET application/json");
		res = service.getJson();
		System.out.println(res.getText());
		System.out.println(res.getDate());
		
		System.out.println("\nGET application/xstream");
		res = service.getXstream();
		System.out.println(res.getText());
		System.out.println(res.getDate());
		
		System.out.println("\nPUT text/plain");
		service.setTextPlain("new plain message");
		
		System.out.println("\nGET application/json");
		res = service.getJson();
		System.out.println(res.getText());
		System.out.println(res.getDate());
		
		System.out.println("\nPUT application/json");
		service.setTextJson(new Msg("hello from ReastEasy"));
		
		System.out.println("\nGET application/json");
		res = service.getJson();
		System.out.println(res.getText());
		System.out.println(res.getDate());
		
		Response resp = service.createNewMessage(new Msg("posted msg"));
		System.out.println(resp.getLocation());
		String id = resp.getLocation().getPath().substring(5);
		System.out.println(id);
		resp.close();

		s = service.getData(id);
		System.out.println(s);
	}

}
