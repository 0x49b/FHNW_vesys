package ch.fhnw.ds.internet;

import java.net.URL;

class ParseURL {
	public static void main(String[] args) throws Exception {
		String s = "ftp://gruntz:1234@imvs.fhnw.ch:8080/ds/faq/index.html?key=val#Lang";
		//String s = "file:///c:/temp/data.txt";
		//String s = "https://www.fhnw.ch";
		
		URL url = new URL(s);
		System.out.println(s);
		System.out.println("Protocol:    " + url.getProtocol());	// ftp
		System.out.println("Host:        " + url.getHost()); 		// imvs.fhnw.ch
		System.out.println("Port:        " + url.getPort()); 		// 8080
		System.out.println("File:        " + url.getFile()); 		// /ds/faq/index.html?key=val
		System.out.println("Path:        " + url.getPath()); 		// /ds/faq/index.html
		System.out.println("Query:       " + url.getQuery()); 		// key=val
		System.out.println("Ref:         " + url.getRef()); 		// Lang

		System.out.println("Authority:   " + url.getAuthority()); 	// gruntz:1234@imvs.fhnw.ch:8080
		System.out.println("DefaultPort: " + url.getDefaultPort()); // 21 [default port for ftp]
		System.out.println("UserInfo:    " + url.getUserInfo()); 	// gruntz:1234
	}
}
