/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package bank.gui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import bank.BankDriver;

public class DriverFactory {

	public static BankDriver getDriver(String[] args) {
		BankDriver driver = null;
		try {
			Class<?> c = Class.forName(args[0]);
			driver = (BankDriver) c.getConstructor().newInstance();
		} catch (InvocationTargetException e) {
			System.out.println("The following exception was thrown while creating an instance of class " + args[0] + ".");
			e.printStackTrace();
			System.exit(1);
		} catch (NoSuchMethodException e) {
			System.out.println("class " + args[0] + " must have a default constructor.");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.out.println("class " + args[0] + " could not be found.");
			System.exit(1);
		} catch (InstantiationException e) {
			System.out.println("class " + args[0] + " could not be instantiated.");
			System.out.println("probably it has no public default constructor!");
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.out.println("class " + args[0] + " could not be instantiated.");
			System.out.println("probably it is not declared public!");
			System.exit(1);
		}

		String[] serverArgs = new String[args.length - 1];
		System.arraycopy(args, 1, serverArgs, 0, args.length - 1);

		try {
			driver.connect(serverArgs);
		} catch (IOException e) {
			System.out.println("Problem while connecting to the server:");
			e.printStackTrace();
			System.exit(1);
		}

		return driver;
	}

}
