package ch.fhnw.ds.rmi.quotes;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class QuoteServerImpl extends UnicastRemoteObject implements QuoteServer, Runnable {
	private static final long serialVersionUID = -2348772191451287552L;

	// tracks all connected clients
	private final List<QuoteListener> clients = new CopyOnWriteArrayList<>();
	
	// maintains updatable list of quotes
	private final List<String> quotes = new ArrayList<String>(); 
	{
		quotes.add("Wer andern eine Grube graebt faellt selbst hinein");
		quotes.add("Steter Tropfen hoehlt den Stein");
		quotes.add("Stille Wasser gruenden tief");
		quotes.add("Wo viel Licht ist, ist viel Schatten.");
		quotes.add("Papier ist geduldig");
		quotes.add("640K ought to be enough for anybody -- Bill Gates");
		quotes.add("I think there is a world market for maybe five computers. -- T. Watson");
		quotes.add("There is no reason anyone would want a computer in their home. -- K. Olson");
		quotes.add("Das Vorhersagen ist nicht einfach, besonders, wenn es die Zukunft betrifft. -- A. Einstein");
	}

	private Thread clientThread = null;

	private static int counter = 0;

	public QuoteServerImpl() throws java.rmi.RemoteException {
		super(8888);
	}

	public void addQuoteListener(QuoteListener c) {
		clients.add(c);
		if (clientThread == null) {
			clientThread = new Thread(this, "clientThread");
			clientThread.start();
		}
	}

	public void run() {
		while (clientThread != null) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			updateClients();
		}
	}

	private void updateClients() {
		String data;
		synchronized (quotes) {
			int index = new Random().nextInt(quotes.size());
			data = ++counter + ": " + quotes.get(index);
		}
		for(QuoteListener c : clients) {
			try {
				// update the client asynchronously via callback
				c.update(data);
			} catch (RemoteException e) {
				System.out.println("client must have disconnected!");
				// get rid of remote reference for disconnected client
				clients.remove(c);
			}
		}
		if (clients.size() <= 0) {
			//no more clients- so stop server thread
			clientThread = null;
		}
	}

	public void addQuote(String quote) throws RemoteException {
		synchronized (quotes) {
			quotes.add(quote);
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			System.out.println("QuoteServer.main: creating registry");
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println(">> registry could not be exported");
			System.out.println(">> probably another registry runs on 1099");
		}

		System.out.println("QuoteServer.main: creating server");
		QuoteServerImpl quoteServer = new QuoteServerImpl();
		System.out.println("QuoteServer.main: binding server ");
		Naming.rebind("QuoteServer", quoteServer);
		System.out.println("QuoteServer bound in registry");
	}
}