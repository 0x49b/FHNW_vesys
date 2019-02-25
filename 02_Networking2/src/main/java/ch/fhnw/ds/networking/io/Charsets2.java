package ch.fhnw.ds.networking.io;

import java.nio.charset.StandardCharsets;

public class Charsets2 {
	
	public static void main(String[] args) {
		System.out.println("Charsets which are guaranteed to be available on every implementation of the Java platform:");

		System.out.println(StandardCharsets.ISO_8859_1);
		System.out.println(StandardCharsets.US_ASCII);
		System.out.println(StandardCharsets.UTF_16);
		System.out.println(StandardCharsets.UTF_16BE);
		System.out.println(StandardCharsets.UTF_16LE);
		System.out.println(StandardCharsets.UTF_8);
	}

}
