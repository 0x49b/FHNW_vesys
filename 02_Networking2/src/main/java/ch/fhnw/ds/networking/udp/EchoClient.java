package ch.fhnw.ds.networking.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

class EchoClient {

	public static void main(String args[]) throws Exception {
		String host = "localhost";
		InetAddress ia = InetAddress.getByName(host);

		try (DatagramSocket socket = new DatagramSocket()) {
			DatagramPacket packet = new DatagramPacket(new byte[512], 512, ia, 1234);
			socket.setSoTimeout(1000);

			try (BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
				String input = stdin.readLine();
				while (input != null && !input.equals("")) {
					packet.setData(input.getBytes());
					socket.send(packet);
					try {
						packet.setData(new byte[1024]);
						socket.receive(packet);
						System.out.println(new String(packet.getData(), packet.getOffset(), packet.getLength()));
						System.out.println(packet.getSocketAddress());
					} catch (SocketTimeoutException e) {
						System.out.println("no answer received");
					}
					input = stdin.readLine();
				}
			}
			System.out.println("disconnected.");
		}
	}

}
