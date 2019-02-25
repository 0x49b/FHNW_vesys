package ch.fhnw.ds.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

class SimpleClient {

	public static void main(String args[]) throws Exception {
		String host = "localhost";

		try (DatagramSocket socket = new DatagramSocket()) {
			System.out.println("local addr: " + socket.getLocalAddress());
			System.out.println("local port: " + socket.getLocalPort());

			InetAddress ia = InetAddress.getByName(host);

			for (int i = 0; i < 5; i++) {
				String s = new Date().toString();

				DatagramPacket packet = new DatagramPacket(s.getBytes(), s.length(), ia, 4711);

				socket.send(packet);

				System.out.println("Weg ist es");

				Thread.sleep(1000);
			}
		}
	}

}
