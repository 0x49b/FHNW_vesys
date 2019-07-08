package ch.fhnw.ds.ws.echo.client.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.Date;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

public class Client {

	public static void main(String[] args) throws Exception {
		CountDownLatch latch = new CountDownLatch(1);
		
		WebSocket ws = HttpClient
				.newHttpClient()
				.newWebSocketBuilder()
				.buildAsync(
//						URI.create("ws://echo.websocket.org"),
						URI.create("ws://localhost:2222/websockets/echo"),
						new WebSocketClient(latch))
				.join();
		
		System.out.println("WebSocket created " + ws);
		ws.sendText("This is a message sent at " + new Date(), true);
		latch.await();
	}
		
	private static class WebSocketClient implements WebSocket.Listener {
		private final CountDownLatch latch;

		public WebSocketClient(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void onOpen(WebSocket webSocket) {
			System.out.println("onOpen " + Thread.currentThread());
			webSocket.request(1);
		}

		@Override
		public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
			System.out.println("onText received with data " + data);
			webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "OK");
			webSocket.request(1);
			return null;
		}

		@Override
		public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
			System.out.println("Closed with status " + statusCode + ", reason: " + reason);
			latch.countDown();
			return null;
		}
	};		

}
