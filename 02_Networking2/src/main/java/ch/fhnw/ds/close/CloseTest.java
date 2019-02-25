/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package ch.fhnw.ds.close;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Formatter;

public class CloseTest {

    public static void main(String[] args) throws Exception {
        final String msg = "This is line %3d of a long message " +
                "sent over TCP/IP to the server.\n";
        final int n = 600;

        String host = "localhost";
        int port = 55555;
        if (args.length > 0) {
            host = args[0];
        }
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        Socket s = new Socket(host, port);
        System.out.println("sending data to " + s);

        OutputStream out = s.getOutputStream();

        // class Formatter formats data to a given out stream
        try (Formatter formatter = new Formatter(out)) {
            for (int i = 0; i < n; i++) {
                formatter.format(msg, i);
            }

            System.out.println(s.getSendBufferSize());
            formatter.flush();
            s.close();


            System.out.println("done.");
        }
    }
}
