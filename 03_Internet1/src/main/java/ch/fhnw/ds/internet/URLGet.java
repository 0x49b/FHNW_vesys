package ch.fhnw.ds.internet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLGet {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://www.fhnw.ch");
		try (BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()))) {
			r.lines().forEach(System.out::println);
		}
	}

}
