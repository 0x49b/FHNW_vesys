package ch.fhnw.ds.networking.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.GregorianCalendar;

class Deposit implements java.io.Serializable {
//	static final long serialVersionUID = 7064159111968756472L;

	private int account;
	private double amount;

//	int  x = 100;
//	Date date = new GregorianCalendar().getTime();

	Deposit(int account, double amount) {
		this.account = account;
		this.amount = amount;
	}

	public int getAccount() { return account; }
	public double getAmount() { return amount; }


//	private void readObject(ObjectInputStream stream)
//				throws IOException, ClassNotFoundException {
//		System.err.println("Deposit.readObject called");
//		ObjectInputStream.GetField fields = stream.readFields();
////		x     = fields.get("x", 0) + 1;
//		account = fields.get("account", 0);
//		amount  = fields.get("amount", 0.0);
//	}

}

/*
cd C:\Documents\Kurse\DistributedSystems\Teaching\vesys\02_Networking2\bin\main
serialver ch.fhnw.ds.networking.io.Deposit
 */
