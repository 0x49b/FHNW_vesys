package ch.fhnw.ds.networking.tcp;

import java.io.InputStream;
import java.net.Socket;

public class Daytime {

	public static void main(String[] args) throws Exception {
		try (Socket s = new Socket("time-a.nist.gov", 13)) {
			InputStream in = s.getInputStream();
			int ch = in.read();
			while (ch != -1) {
				System.out.print((char) ch);
				ch = in.read();
			}
		}
	}

}
