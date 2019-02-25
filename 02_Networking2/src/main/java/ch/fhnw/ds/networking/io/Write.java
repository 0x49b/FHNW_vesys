package ch.fhnw.ds.networking.io;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Write {

	public static void main(String[] args) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.txt"));

		Deposit cmd = new Deposit(4456, 130.50);

		out.writeObject(cmd);
		out.close();
	}

}
