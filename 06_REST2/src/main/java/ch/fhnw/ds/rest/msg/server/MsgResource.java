package ch.fhnw.ds.rest.msg.server;

/*
Resource /msg
curl -X GET -i http://localhost:9998/msg
curl -X GET -i -H "Accept: text/plain"            http://localhost:9998/msg
curl -X GET -i -H "Accept: application/xml"       http://localhost:9998/msg
curl -X GET -i -H "Accept: application/json"      http://localhost:9998/msg
curl -X GET -i -H "Accept: application/xstream"   http://localhost:9998/msg

curl -X PUT -i -H "Content-Type: application/json" --data "{\"text\":\"JSON message\"}" http://localhost:9998/msg
curl -X PUT -i -H "Content-Type: application/xml"  --data "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><msg><text>XML message</text></msg>" http://localhost:9998/msg
curl -X PUT -i -H "Content-Type: text/plain" --data "Hallo text/plain" http://localhost:9998/msg
curl -X PUT -i     --data msg=Hallo http://localhost:9998/msg
=> per default application/x-www-form-urlencoded is used by curl
 
curl -X POST -i -H "Content-Type: application/json" --data "{\"text\":\"json\"}" http://localhost:9998/msg


Resource /msg/{id}
curl -X GET -i http://localhost:9998/msg/0
curl -X GET -i http://localhost:9998/msg/1
curl -X GET -i http://localhost:9998/msg/2


curl -X OPTIONS --head http://localhost:9998/msg
curl -X OPTIONS --head http://localhost:9998/msg/0

*/

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Singleton
@Path("/msg")
public class MsgResource {
	private String msg = "Hello, world!";

	public MsgResource() {
		System.out.println("MsgResource() called");
	}

	@GET
	@Produces("text/plain")
	public String getPlain() {
		return msg + "\n";
	}

	@GET
	@Produces("text/html")
	public String getHtml() {
		StringBuffer buf = new StringBuffer();
		buf.append("<html><body><h1>Message Text</h1>" + msg + "<br>");
		buf.append("<form method=\"POST\" action=\"/msg\">");
		buf.append("<p>Text: <input name=\"msg\" type=\"text\" size=20/>");
		buf.append("<input type=\"submit\" value=\"Submit\" />");
		buf.append("</form>");
		buf.append("</body></html>");
		return buf.toString();
	}

	@GET
	@Produces("text/xml")
	public String getSimpleXml() {
		return "<string>" + msg + "</string>";
	}

	@GET
	@Produces( { "application/json", "application/xml", "application/xstream" })
	public Msg getXml() {
		return new Msg(msg);
	}

	
	@PUT
	@Consumes("text/plain")
	public void setTextPlain(String msg) {
		this.msg = msg;
	}

	@PUT
	@Consumes( { "application/json", "application/xml", "application/xstream" })
	public void setTextXml(Msg message) {
		msg = message.getText();
	}

	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/html")
	public String doPost(@FormParam("msg") String msg) {
		this.msg = msg;
		return getHtml();
	}



	private final AtomicInteger counter = new AtomicInteger();
	private final Map<Integer, Msg> map = new HashMap<>();
	
	@POST
	@Consumes({ "application/xml", "application/json", "application/xstream" })
	public Response createNewMessage(@Context UriInfo uriInfo, Msg message) {
		int id = counter.getAndIncrement();
		map.put(id, message);
		URI location = uriInfo.getAbsolutePathBuilder().path("" + id).build();
		return Response.created(location).build();
	}

	@GET
	@Produces("text/plain")
	@Path("{id}")
	public String getData(@PathParam("id") int id) {
		return map.get(id).getText() + "\n";
	}

}
