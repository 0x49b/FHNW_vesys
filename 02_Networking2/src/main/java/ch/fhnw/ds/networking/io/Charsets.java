package ch.fhnw.ds.networking.io;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

public class Charsets {

	public static void main(String[] args) {
		Map<String, Charset> m = Charset.availableCharsets();
		for (Entry<String, Charset> e : m.entrySet()) {
			System.out.println(e.getKey());
			System.out.println("\t" + e.getValue().aliases());
		}

		System.out.println("DEFAULT = " + Charset.defaultCharset());
	}

}
