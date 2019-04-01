package ch.fhnw.ds.rmi.quotes;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class SimpleClient {

	public static void main(String[] args) throws Exception {
		QuoteServer server;
		server = (QuoteServer) Naming.lookup("rmi://localhost/QuoteServer");

		server.addQuoteListener(new QuoteListener() {
			{
				UnicastRemoteObject.exportObject(this, 0);
			}

			public void update(String s) {
				System.out.println(s);
			}
		});
	}
}