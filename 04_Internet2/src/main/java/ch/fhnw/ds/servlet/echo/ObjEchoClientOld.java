package ch.fhnw.ds.servlet.echo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObjEchoClientOld {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/ds/obj");
		HttpURLConnection c = (HttpURLConnection) url.openConnection();
		c.setDoOutput(true);
		// c.setRequestMethod("POST");							// default if data is written
		// c.setRequestProperty("User-Agent", "Mozilla/5.0");	// not necessary

		ObjectOutputStream oos = new ObjectOutputStream(c.getOutputStream());

		oos.writeObject("Hello");
		//oos.writeObject(new X(2018));
		oos.flush();
		// oos.close();	// not necessary

		int responseCode = c.getResponseCode();
		System.out.println("Response Code: " + responseCode);

		ObjectInputStream ois = new ObjectInputStream(c.getInputStream());
		Object res = ois.readObject();
		System.out.println(res);
	}
	
	static class X implements Serializable {
		private static final long serialVersionUID = 3363078218892961994L;
		private final int value;
		private X(int value) { this.value = value; }
		public String toString() { return String.format("X(%d)", value); }
	}

}
