/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package ch.fhnw.ds.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class CompressClient {

	public static final int BUFSIZE = 256; // Size of read buffer
	
	public static final String FILE_NAME = "Test"; // name of the generated file
	public static final int FILE_SIZE = 1000; // size of the generated file in KBtyes

	public static void main(String[] args) throws Exception {
		String server = "localhost";
		int port = 55555;
		if (args.length > 0) { server = args[0]; }
		if (args.length > 1) { port = Integer.parseInt(args[1]); }
		
		// create a file with a given size
		createFile(FILE_NAME, FILE_SIZE * 1024);
		
		// Open input and output file (named input.gz)
		final FileInputStream fileIn = new FileInputStream(FILE_NAME);
		final FileOutputStream fileOut = new FileOutputStream(FILE_NAME + ".gz");

		// Create socket connected to server on specified port
		final Socket sock = new Socket(server, port);
		
		// Send uncompressed byte stream to server
		sendBytes(sock, fileIn);

		// Receive compressed byte stream from server
		InputStream sockIn = sock.getInputStream();
		int bytesRead;
		byte[] buffer = new byte[BUFSIZE]; 
		while ((bytesRead = sockIn.read(buffer)) != -1) {
			fileOut.write(buffer, 0, bytesRead);
			System.out.print("R"); // Reading progress indicator
		}
		System.out.println(); // End progress indicator line

		sock.close();
		fileIn.close();
		fileOut.close();
	}

	private static void createFile(String name, int size) throws Exception {
		Random r = new Random();
		int next = 0;
		OutputStream out = new FileOutputStream(new File(name));
		for(int i=0; i<size; i++){
			if(i % 3 == 0) next = r.nextInt();
			out.write(next);
		}
		out.close();
	}

	private static void sendBytes(Socket sock, InputStream fileIn)
			throws IOException {
		OutputStream sockOut = sock.getOutputStream();
		int bytesRead;
		byte[] buffer = new byte[BUFSIZE];
		while ((bytesRead = fileIn.read(buffer)) != -1) {
			sockOut.write(buffer, 0, bytesRead);
			System.out.print("W"); // Writing progress indicator
		}
		sock.shutdownOutput();
		System.out.println(); // End progress indicator line
	}
}
