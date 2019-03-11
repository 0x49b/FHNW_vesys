package ch.fhnw.ds.servlet.echo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ObjEchoClient {

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClient.newHttpClient();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject("Hello");
		//oos.writeObject(new X(2018));
		oos.close();
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofByteArray(baos.toByteArray());

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/ds/obj"))
				.POST(body)
				.build();

		HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
		
		System.out.println("Statuscode: " + response.statusCode());
		ObjectInputStream ois = new ObjectInputStream(response.body());
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
