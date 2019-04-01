package ch.fhnw.ds.rmi.calculator;

import java.rmi.Naming;

public class Server {
	
	public static void main(String args[]) throws Exception {
		Calculator c = new CalculatorImpl(
					7777
		);
		Naming.rebind("rmi://localhost:1099/CalculatorService", c);
		System.out.println("Calculator server started...");
	}

}
