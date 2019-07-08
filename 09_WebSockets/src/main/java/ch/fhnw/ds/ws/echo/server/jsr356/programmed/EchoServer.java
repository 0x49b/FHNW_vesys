package ch.fhnw.ds.ws.echo.server.jsr356.programmed;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

public class EchoServer extends Endpoint {

	{
		System.out.println("EchoServer created " + this);
	}

	@Override
	public void onOpen(final Session session, EndpointConfig config) {
		System.out.printf("New session %s\n", session.getId());
		final RemoteEndpoint.Basic remote = session.getBasicRemote();
		session.addMessageHandler(String.class, msg -> {
			System.out.printf("received message over session %s: %s\n", session.getId(), msg);
			try {
				remote.sendText("Echo " + msg);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

//		session.addMessageHandler(String.class, (msg, last) -> {
//			System.out.printf("received message over session %s: %s [%s]\n", session.getId(), msg, last);
//			try {
//				if (last)
//					remote.sendText("Echo " + msg);
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//		});
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		System.out.printf("Session %s closed because of %s\n", session.getId(), closeReason);
	}

	@Override
	public void onError(Session session, Throwable exception) {
		System.out.println("an error occured on connection " + session.getId() + ":" + exception);
	}

}