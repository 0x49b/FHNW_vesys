package ch.fhnw.ds.echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String args[]) throws IOException {
		int port = 1234;
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Startet Echo Server on port " + port);
			while (true) {
				Socket s = server.accept();
				Thread t = new Thread(() -> {
					try (s) {
						s.getInputStream().transferTo(s.getOutputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("done serving " + s);
				});
				t.start();
			}
		}
	}
}
