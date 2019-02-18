/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package ch.fhnw.ds.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {

	public static void main(String args[]) throws IOException {
		int port = 1234;
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Startet Echo Server on port " + port);
			while (true) {
				Socket s = server.accept();
				Thread t = new Thread(new EchoHandler(s));
				t.start();
			}
		}
	}

	private static class EchoHandler implements Runnable {
		private final Socket sock;

		private EchoHandler(Socket sock) { this.sock = sock; }

		public void run() {
			System.out.println("connection from " + sock);
			try (Socket s = sock; 
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {

				String input = in.readLine();
				while (input != null && !"".equals(input)) {
					out.println(input);
					input = in.readLine();
				}
				System.out.println("done serving " + s);
			} catch (IOException e) {
				System.err.println(e);
				throw new RuntimeException(e);
			}
		}
	}
}
