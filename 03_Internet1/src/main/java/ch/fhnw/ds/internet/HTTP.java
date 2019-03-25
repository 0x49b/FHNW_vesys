
package ch.fhnw.ds.internet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class HTTP connects to a host and prints the first 100 lines of the response.
 */
public class HTTP {

	public static void main(String[] args) throws Exception {
		String host = "www.google.com";
		//host = "localhost";

		try(Socket s = new Socket(host, 80);
			PrintWriter out = new PrintWriter(s.getOutputStream())) {

			// Use HTTP 1.0
//			out.print("GET / HTTP/1.0\r\n\r\n");

			// Use HTTP 1.1 with the specification of the Host 
//			out.print("GET / HTTP/1.1\r\nHost: "+host+"\r\n\r\n");
			out.print("GET / HTTP/1.1\r\nAccept-Encoding: gzip\r\nHost: "+host+"\r\n\r\n");

			// other HTTP methods
//	    	out.print("HEAD / HTTP/1.1\r\nHost: "+host+"\r\n\r\n");
//	    	out.print("OPTIONS / HTTP/1.1\r\nHost: "+host+"\r\n\r\n");
//			out.print("DELETE /index.html HTTP/1.1\r\nHost: "+host+"\r\n\r\n");
			
//			out.print("GET /date HTTP/1.1\r\n\r\n");
//			out.print("GET /date HTTP/1.1\r\nHost: "+host+"\r\n\r\n");

			out.flush();

			try(BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
				r.lines().limit(100).forEach(System.out::println);
			}

		}
	}
}
