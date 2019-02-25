package ch.fhnw.ds.networking.tcp;

import java.io.InputStream;
import java.net.Socket;

public class Timeout2 {

	public static void main(String[] args) throws Exception {
		Socket s = new Socket("www.google.com", 80);
		System.out.println(s);

		s.setSoTimeout(5000);
		InputStream in = s.getInputStream();
		System.out.println(in.read());
	}

}
