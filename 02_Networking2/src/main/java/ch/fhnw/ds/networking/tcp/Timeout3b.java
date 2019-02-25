package ch.fhnw.ds.networking.tcp;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Timeout3b {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		try {
			InetSocketAddress adr = new InetSocketAddress("www.google.com", 1234);
			
			Socket s = new Socket();
			s.connect(adr, 2000);
			
			System.out.println(s);
			s.getInputStream().read();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() - start + " msec");
	}

}
