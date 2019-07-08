package ch.fhnw.ds.ws;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		Socket s = new Socket("echo.websocket.org", 80);
//		Socket s = new Socket("localhost", 2222);
		PrintWriter out = new PrintWriter(s.getOutputStream());
		out.print("GET / HTTP/1.1\r\n");
		out.print("Host: echo.websocket.org:80\r\n");
		out.print("Connection: Upgrade\r\n");
		out.print("Upgrade: websocket\r\n");
		out.print("Sec-Websocket-Key: mqn5Pm7wtXEX6BzqDInLjw==\r\n");
		out.print("Sec-Websocket-Version: 13\r\n\r\n");
		out.flush();
		
		InputStream in = s.getInputStream();
		while(true) {
			int ch = in.read();
			System.out.println(ch + "/" + (char)ch);
		}
	}

}
