package ch.fhnw.ds.rmi.export;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

	private static BufferedReader sysIn;
	
	private static final String SERVER1 = "localhost";
	
	@SuppressWarnings("unused")
	private static final String SERVER2 = "10.35.147.24";
	
    public static void main(String args[]) throws Exception {
        sysIn = new BufferedReader(new InputStreamReader(System.in));
        
		Counter counter  = new CounterImpl();
		System.out.println("InitialValue: " + counter.getValue());
		System.out.println(counter);

		int value;
		prompt("increment ...");
		value = counter.increment();
		System.out.println("New Value: " + value);
		System.out.println(counter);
		
		prompt("increment ...");
		value = counter.increment();
		System.out.println("New Value: " + value);
		System.out.println(counter);

		prompt("migrate to " + SERVER1 + " ...");
		counter = counter.migrateTo(SERVER1);
		System.out.println(counter);

		prompt("increment ...");
		value = counter.increment();
		System.out.println("New Value: " + value);
		System.out.println(counter);

		prompt("increment ...");
		value = counter.increment();
		System.out.println("New Value: " + value);
		System.out.println(counter);

//		prompt("migrate to " + SERVER2 + " ...");
//		counter = counter.migrateTo(SERVER2);
//		System.out.println(counter);
//
//		prompt("increment ...");
//		value = counter.increment();
//		System.out.println("New Value: " + value);
//		System.out.println(counter);
//
//		prompt("increment ...");
//		value = counter.increment();
//		System.out.println("New Value: " + value);
//		System.out.println(counter);

		prompt("migrate back ...");
		counter = counter.migrateBack();
		System.out.println("New Value: " + value);
		System.out.println(counter);

		prompt("increment ...");
		value = counter.increment();
		System.out.println("New Value: " + value);
		System.out.println(counter);
		
		prompt("increment ...");
		value = counter.increment();
		System.out.println("New Value: " + value);
		System.out.println(counter);
   }
    
    private static void prompt(String msg) throws IOException {
		System.out.print(msg + " (Press Enter)");
		sysIn.readLine();
    }
}