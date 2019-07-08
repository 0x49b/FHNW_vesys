package ch.fhnw.ds.ws.echo.client.jsr356.annotated;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

@ClientEndpoint
public class EchoClient {

	private static CountDownLatch latch = new CountDownLatch(1);

	@OnOpen
	public void onOpen(Session session) throws IOException {
		System.out.println("onOpen " + Thread.currentThread());
		session.getBasicRemote().sendText("Hello");
		// session.getBasicRemote().sendBinary(ByteBuffer.wrap(new byte[]{'h', 'e', 'l', 'o'}));
		
//        session.getBasicRemote().sendText("Hello", false);
//        session.getBasicRemote().sendText("World", true);
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		System.out.println("onMessage " + message + " " + Thread.currentThread());
		session.close();
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.printf("[%s] Session %s closed because of %s\n", Thread.currentThread(), session.getId(), closeReason);
		latch.countDown();
	}

	@OnError
	public void onError(Throwable exception, Session session) {
		System.out.println("an error occured on connection " + session.getId() + ":" + exception);
	}

	public static void main(String[] args) throws Exception {
		// URI url = new URI("ws://echo.websocket.org/");
		URI url = new URI("ws://localhost:2222/websockets/echo");
		
		//System.out.println(Thread.currentThread());
		ClientManager client = ClientManager.createClient();
		client.connectToServer(EchoClient.class, url);
		latch.await();
	}

}
