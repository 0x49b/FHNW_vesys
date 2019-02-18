/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package ch.fhnw.ds.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = 1234;
//		host = "86.119.38.130";
//		port = 7;
		if (args.length > 0) { host = args[0]; }
		if (args.length > 1) { port = Integer.parseInt(args[1]); }
		
		System.out.println("connecting to " + host + ":" + port);

		Socket s = new Socket(host, port, null, 0);
		// null means any network interface
		// 0 means any port

		System.out.println("connected to " + s.getRemoteSocketAddress());

		PrintWriter out = new PrintWriter(s.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String input = stdin.readLine();
		while (input != null && !input.equals("")) {
			out.println(input);
			out.flush();
			System.out.println("Echo: " + in.readLine());
			input = stdin.readLine();
		}
		s.close();
		System.out.println("disconnected.");
	}
}
