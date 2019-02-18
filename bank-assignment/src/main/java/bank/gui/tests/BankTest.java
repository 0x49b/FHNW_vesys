/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package bank.gui.tests;

import bank.Bank;

public interface BankTest {
	String getName();
	boolean isEnabled(int size);
	String runTests(Bank bank, String currentAccountNumber) throws Exception;
}
