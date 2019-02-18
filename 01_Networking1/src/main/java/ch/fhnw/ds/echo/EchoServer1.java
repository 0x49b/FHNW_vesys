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

public class EchoServer1 {

	public static void main(String args[]) throws IOException {
		try (ServerSocket server = new ServerSocket(1234)) {
			System.out.println("Startet Echo Server on port " + server.getLocalPort());
			while (true) {
				try (Socket s = server.accept()) {
					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);

					String input = in.readLine();
					while (input != null && !"".equals(input)) {
						out.println(input);
						input = in.readLine();
					}
					System.out.println("done serving " + s);
				}
			}
		}
	}
}