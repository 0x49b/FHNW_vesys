package ch.fhnw.ds.rmi;

import java.rmi.Naming;

public class ListRMIRegistry {

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = 1099;
		if (args.length > 0) {
			host = args[0];
			if (args.length > 1) {
				port = Integer.parseInt(args[1]);
			}
		}

		String names[] = Naming.list("rmi://" + host + ":" + port);
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}

}
