package tomcat;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class Shutdown {

	private static final int SHUTDOWN_PORT = 8005;

	public static void main(String[] args) throws Exception {
		try (Socket s = new Socket("localhost", SHUTDOWN_PORT);
			 Writer w = new OutputStreamWriter(s.getOutputStream())) {
			w.write("SHUTDOWN\n");
		}
	}

}
