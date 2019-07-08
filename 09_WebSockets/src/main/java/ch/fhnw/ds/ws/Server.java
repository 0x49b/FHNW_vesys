package ch.fhnw.ds.ws;

import org.glassfish.grizzly.utils.Charsets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.*;

public class Server {

    public static void main(String[] args) throws Exception {
        try (ServerSocket ss = new ServerSocket(2222)) {
            while (true) {
                new Handler(ss.accept()).start();
            }
        }
    }

    static class Handler extends Thread {
        private final Socket s;
        private final Map<String, String> headers = new HashMap<>();

        public Handler(Socket s) {
            System.out.println(">> Handler()");
            this.s = s;
        }

        public void run() {
            try {
                InputStream st = s.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(st));
                String line = r.readLine(); // request line
                line = r.readLine();
                while (line != null && !"".equals(line)) {
                    int pos = line.indexOf(':');
                    String key = line.substring(0, pos).trim();
                    String value = line.substring(pos + 1, line.length()).trim();
                    System.out.println(key + ":  " + value);
                    headers.put(key.toLowerCase(), value);
                    line = r.readLine();
                }

                Writer w = new OutputStreamWriter(s.getOutputStream());
                w.write("HTTP/1.1 101 Switching Protocols\r\n");
                w.write("upgrade: websocket\r\n");
                w.write("connection: Upgrade\r\n");
                w.write("sec-websocket-accept: " + Sec.computeReturnKey(headers.get("sec-websocket-key")) + "\r\n");
                w.write("\r\n");
                w.flush();

                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        try {
                            sendData(s.getOutputStream());
                        } catch (IOException e) {
                        }
                    }
                }, 0, 5000);

                while (true) {
                    int b = st.read();
                    if (b == -1) break;
                    System.out.printf("%tT: 0x%02x %8s\n", new Date(), b, toBinary(b));
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private void sendData(OutputStream s) throws IOException {
            String text = "Hello";
            s.write(0b1000_0001);
            s.write(text.length());
            s.write(text.getBytes());
            System.out.println("");
            s.flush();
        }
    }

    private static String toBinary(int value) {
        String s = Integer.toBinaryString(value);
        while (s.length() < 8) s = "0" + s;
        return s;
    }

}
