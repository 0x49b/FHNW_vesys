/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package ch.fhnw.ds.compress;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.GZIPOutputStream;

public class CompressServer9 {

	public static void main(String[] args) throws IOException {
		int port = 55555;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		try (ServerSocket servSock = new ServerSocket(port)) {
			System.out.println("CompressServer started on port " + port);

			while (true) {
				Socket socket = servSock.accept();
				Thread thread = new Thread(() -> {
					try (socket; InputStream in = socket.getInputStream(); GZIPOutputStream out = new GZIPOutputStream(socket.getOutputStream())) {
						in.transferTo(out);
						out.finish();
					} catch (IOException ex) {
						System.err.println(ex);
					}

				});
				thread.start();
			}
		}
	}
}
