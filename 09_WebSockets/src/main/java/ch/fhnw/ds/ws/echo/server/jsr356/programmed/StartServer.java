package ch.fhnw.ds.ws.echo.server.jsr356.programmed;

import org.glassfish.tyrus.server.Server;

public class StartServer {

	public static void main(String[] args) throws Exception {
		Server server = new Server("localhost", 2222, "/websockets", null, Config.class);
		server.start();
		System.out.println("Server started, press a key to stop the server");
		System.in.read();
	}

}
