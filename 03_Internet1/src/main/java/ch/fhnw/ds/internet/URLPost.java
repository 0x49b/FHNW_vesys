package ch.fhnw.ds.internet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class URLPost {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:80/bank");
		HttpURLConnection c = (HttpURLConnection) url.openConnection();
		c.setRequestMethod("POST");
		c.setRequestProperty("Bank-Command", "deposit");
		c.setDoOutput(true);
		c.setDoInput(true);
		c.connect();

		OutputStream os = c.getOutputStream();
		OutputStreamWriter wr = new OutputStreamWriter(os);
		wr.write("user=" + URLEncoder.encode("Müller2", "UTF-8") + "&amount=1234");
		wr.flush();

		InputStream is = c.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		
		rd.lines().forEach(System.out::println);
	}

}
