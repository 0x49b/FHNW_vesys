package ch.fhnw.ds.rmi.quotes;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SimpleClient2 {

	public static void main(String[] args) throws Exception {
		QuoteServer server;
		server = (QuoteServer) Naming.lookup("rmi://localhost/QuoteServer");

		server.addQuoteListener(new Listener());
	}

	static class Listener extends UnicastRemoteObject implements QuoteListener {
		private static final long serialVersionUID = 7428407045819978389L;

		public Listener() throws RemoteException { }

		@Override
		public void update(String s) {
			System.out.println(s);
		}
	}
}