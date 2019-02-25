package ch.fhnw.ds.networking.tcp;

import java.net.InetAddress;

public class InetAddressTest {

    public static void main(String[] args) throws Exception {
        InetAddress adr = InetAddress.getByName("www.fhnw.ch");
        System.out.println(adr);
        System.out.println("\tis reachable: " + adr.isReachable(5000));

        System.out.println("\nInetAddress.getAllByName(\"google.com\")");
        InetAddress[] google = InetAddress.getAllByName("google.com");
        for (InetAddress ia : google) {
            System.out.println(ia);
        }

        System.out.println("\nInetAddress.getLocalHost()");
        InetAddress local = InetAddress.getLocalHost();
        System.out.println(local);
        printInfo(local);

        System.out.println("\nInetAddress.getLoopbackAddress()");
        InetAddress loopback = InetAddress.getLoopbackAddress();
        System.out.println(loopback);
        printInfo(loopback);
    }

    private static void printInfo(InetAddress local) {
        System.out.println("canonical host name: " + local.getCanonicalHostName());
        System.out.println("host address:        " + local.getHostAddress());
        System.out.println("host name:           " + local.getHostName());
    }

}
