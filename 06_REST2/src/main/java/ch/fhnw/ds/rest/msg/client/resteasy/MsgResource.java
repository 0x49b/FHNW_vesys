package ch.fhnw.ds.rest.msg.client.resteasy;

/*
Resource /msg
curl -X GET -i http://localhost:9998/msg
curl -X GET -i -H "Accept: text/plain"            http://localhost:9998/msg
curl -X GET -i -H "Accept: application/xml"       http://localhost:9998/msg
curl -X GET -i -H "Accept: application/json"      http://localhost:9998/msg
curl -X GET -i -H "Accept: application/xstream"   http://localhost:9998/msg

curl -X DELETE   http://localhost:9998/msg

curl -X PUT -i -H "Content-Type: application/json" --data "{\"text\":\"JSON message\"}" http://localhost:9998/msg
curl -X PUT -i -H "Content-Type: application/xml"  --data "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><msg><text>XML message</text></msg>" http://localhost:9998/msg
curl -X PUT -i -H "Content-Type: text/plain" --data "msg=Hallo TextPlain" http://localhost:9998/msg
curl -X PUT -i     --data msg=Hallo http://localhost:9998/msg
 
curl -X POST -i -H "Content-Type: application/json" --data "{\"text\":\"json\"}" http://localhost:9998/msg


Resource /msg/{id}
curl -X GET -i http://localhost:9998/msg/Dominik
curl -X GET -i http://localhost:9998/msg/Dominik/headers


curl -X OPTIONS -i http://localhost:9998/msg
curl -X HEAD -i http://localhost:9998/msg

*/

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.fhnw.ds.rest.msg.server.Msg;

@Path("msg")
public interface MsgResource {

	@GET
	@Produces("text/plain")
	public String getPlain();

	@GET
	@Produces("application/json")
	public Msg getJson();

	@GET
	@Produces("application/xml")
	public Msg getXml();

	@GET
	@Produces("application/xstream")
	public Msg getXstream();

	@PUT
	@Consumes("text/plain")
	public void setTextPlain(String new_msg);

	@PUT
	@Consumes("application/xml")
	public void setTextXml(Msg message);

	@PUT
	@Consumes("application/json")
	public void setTextJson(Msg message);

	@PUT
	@Consumes("application/xstream")
	public void setTextXStream(Msg message);

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/html")
	public String doPost(@FormParam("msg") String new_msg);

	@POST
	@Consumes("application/json")
	public Response createNewMessage(Msg message);

	@GET
	@Produces("text/plain")
	@Path("{id}")
	public String getData(@PathParam("id") String id);

}
