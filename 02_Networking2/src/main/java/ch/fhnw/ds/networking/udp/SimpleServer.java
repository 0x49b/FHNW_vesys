package ch.fhnw.ds.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SimpleServer {
	
	public static void main(String args[]) throws Exception {
		try ( DatagramSocket socket = new DatagramSocket(4711)) {
			System.out.println(socket.getLocalAddress());
			System.out.println(socket.getLocalPort());
			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
	//		DatagramPacket packet = new DatagramPacket(new byte[10], 10);
	
			while (true) {
				socket.receive(packet);
	
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				int len = packet.getLength();
				byte[] data = packet.getData();
	
				System.out.println("Anfrage von " + address + " vom Port " + port
						+ " Laenge " + len + "\n" + new String(data, 0, len));
				System.out.println(data.length); // remains unchanged
			}
		}
	}

}
