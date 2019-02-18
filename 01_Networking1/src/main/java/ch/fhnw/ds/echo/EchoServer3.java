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

public class EchoServer3 {

	private static int SIZE_THREAD_POOL = 4;

	public static void main(String args[]) throws IOException {
		int port = 1234;
		ServerSocket server = new ServerSocket(port);
		System.out.println("Startet Echo Server on port " + port);
		for (int i = 0; i < SIZE_THREAD_POOL; i++) {
			Thread t = new Thread(new EchoHandler(server));
			t.start();
		}

	}

	private static class EchoHandler implements Runnable {
		private final ServerSocket server;

		EchoHandler(ServerSocket server) {
			this.server = server;
		}

		public void run() {
			while (true) {
				Socket s = null;
				try {
					s = server.accept();
					System.out.println("connection from " + s);

					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					String input = in.readLine();
					while (input != null && !"".equals(input)) {
						out.println(input);
						input = in.readLine();
					}
					System.out.println("done serving " + s);
				} catch (IOException e) {
					System.err.println(e);
				} finally {
					try {
						s.close();
					} catch (IOException e) {
						System.err.println(e);
					}
				}
			}
		}
	}
}
