package ch.fhnw.ds.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EchoServer {

	public static void main(String args[]) throws Exception {
		try (DatagramSocket socket = new DatagramSocket(1234)) {
			System.out.println(socket.getLocalAddress());
			System.out.println(socket.getLocalPort());
			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

			while (true) {
				// needs a new array to store incoming data
				packet.setData(new byte[1024]);
				socket.receive(packet);
				String response = "Echo: " + new String(packet.getData(), packet.getOffset(), packet.getLength());
				packet.setData(response.getBytes());
				socket.send(packet);
			}
		}
	}

}
