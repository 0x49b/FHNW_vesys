package ch.fhnw.ds.rmi.quotes;

import java.awt.BorderLayout;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class FrameClient extends JFrame {
	private static final long serialVersionUID = -3723097174162139000L;

	public FrameClient(String title) throws Exception {
		super(title);

		JTextArea output = new JTextArea();
		JTextField input = new JTextField();
		output.setEditable(false);
		output.setRows(15);
		output.setColumns(50);
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);
		add(input, BorderLayout.SOUTH);

		QuoteServer server = (QuoteServer) Naming.lookup("rmi://localhost/QuoteServer");
		
		class FrameQuoteListener extends UnicastRemoteObject implements QuoteListener {
			private static final long serialVersionUID = 7531061415726944879L;
			private FrameQuoteListener() throws RemoteException {}
			public void update(String s) {
				output.append(s + "\n");
			}
		}

		server.addQuoteListener(new FrameQuoteListener());

		input.addActionListener(event -> {
			try {
				server.addQuote(input.getText());
				input.setText("");
			} catch (java.rmi.RemoteException e) {
				throw new RuntimeException(e);
			}
		});

		pack();
		input.requestFocus();

	}

	public static void main(String args[]) throws Exception {
		JFrame f = new FrameClient("Quotes of the day");
		f.setVisible(true);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
