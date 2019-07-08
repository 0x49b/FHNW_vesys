package ch.fhnw.ds.ws.echo.server.jsr356.annotated;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.glassfish.tyrus.server.Server;

@ServerEndpoint("/echo")
public class EchoServer {
	
	{
		System.out.println("EchoServer created " + this);
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server("localhost", 2222, "/websockets", null, EchoServer.class);
		server.start();
		System.out.println("Server started, press a key to stop the server");
		System.in.read();
	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.printf("New session %s\n", session.getId());
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.printf("Session %s closed because of %s\n", session.getId(), closeReason);
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		System.out.println("received message form " + session.getBasicRemote() + ": " + message);
		return "echo " + message;
	}

	@OnError
	public void onError(Throwable exception, Session session) {
		System.out.println("an error occured on connection " + session.getId() + ":" + exception);
	}

}