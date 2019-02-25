package ch.fhnw.ds.networking.io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Read {

	public static void main(String[] args) throws Exception {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.txt"))) {

			Object obj = in.readObject();
	
			if (obj instanceof Deposit) {
				System.out.println("Deposit Object read:");
				System.out.println("account: " + ((Deposit) obj).getAccount());
				System.out.println("amount:  " + ((Deposit) obj).getAmount());
	
				try {
					Object x = Deposit.class.getDeclaredField("x").get(obj);
					System.out.println("x:       " + x);
				} catch (Exception e) {
				}
	
				try {
					Object date = Deposit.class.getDeclaredField("date").get(obj);
					System.out.println("date:    " + date);
				} catch (Exception e) {
				}
			}
		}
	}
}
