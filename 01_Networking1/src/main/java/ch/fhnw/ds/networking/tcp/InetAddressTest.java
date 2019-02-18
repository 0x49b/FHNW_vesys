package ch.fhnw.ds.networking.tcp;

import java.net.InetAddress;

public class InetAddressTest {

	public static void main(String[] args) throws Exception {
		InetAddress adr = InetAddress.getByName("www.fhnw.ch");
		System.out.println(adr);
		System.out.println("\tis reachable: " + adr.isReachable(5000));

		System.out.println("\nInetAddress.getAllByName(\"amazon.com\")");
		InetAddress[] google = InetAddress.getAllByName("amazon.com");
		for (InetAddress ia : google) {
			System.out.println(ia);
		}

		System.out.println("\nInetAddress.getLocalHost()");
		InetAddress local = InetAddress.getLocalHost();
		System.out.println(local);

		System.out.println("canonical host name: " + local.getCanonicalHostName());
		System.out.println("host address:        " + local.getHostAddress());
		System.out.println("host name:           " + local.getHostName());

		System.out.println("\nInetAddress.getLoopbackAddress()");
		InetAddress loopback = InetAddress.getLoopbackAddress();
		System.out.println(loopback);

		System.out.println("canonical host name: " + loopback.getCanonicalHostName());
		System.out.println("host address:        " + loopback.getHostAddress());
		System.out.println("host name:           " + loopback.getHostName());
	}

}
