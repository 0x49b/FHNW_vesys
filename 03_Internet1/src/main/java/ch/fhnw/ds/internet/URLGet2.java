package ch.fhnw.ds.internet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLGet2 {

	public static void main(String[] args) throws Exception {
		URL url = new URL("https://www.fhnw.ch");
		HttpURLConnection c = (HttpURLConnection)url.openConnection();
		c.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");

		try (BufferedReader r = new BufferedReader(new InputStreamReader(c.getInputStream()))) {
			r.lines().forEach(System.out::println);
		}
	}

}
