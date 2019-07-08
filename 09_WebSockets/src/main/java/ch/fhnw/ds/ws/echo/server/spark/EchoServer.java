package ch.fhnw.ds.ws.echo.server.spark;

import static spark.Spark.init;
import static spark.Spark.port;
import static spark.Spark.webSocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

// Source: http://sparkjava.com/documentation#embedded-web-server
public class EchoServer {
    public static void main(String[] args) {
    	port(2222);	// default port: 4567
    	
    	webSocket("/websockets/echo", EchoWebSocket.class);
    	init(); // Needed if you don't define any HTTP routes after your WebSocket routes
    }
    
    @WebSocket
    static public class EchoWebSocket {

        // Store sessions if you want to, for example, broadcast a message to all users
        private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

        @OnWebSocketConnect
        public void connected(Session session) {
            sessions.add(session);
        }

        @OnWebSocketClose
        public void closed(Session session, int statusCode, String reason) {
            sessions.remove(session);
        }

        @OnWebSocketMessage
        public void message(Session session, String message) throws IOException {
            System.out.println("Got: " + message);   // Print message
            session.getRemote().sendString(message + " from Spark"); // and send it back
        }

    }
}