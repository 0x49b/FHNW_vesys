package ch.fhnw.ds.networking.tcp;

import java.net.ServerSocket;

public class Timeout1 {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(12345);
		System.out.println(ss);

		ss.setSoTimeout(5000);
		ss.accept();
	}

}
