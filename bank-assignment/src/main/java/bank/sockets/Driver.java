/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package bank.sockets;

import bank.InactiveException;
import bank.OverdrawException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Driver implements bank.BankDriver {
    private Bank bank = null;
    private Socket socket = null;


    @Override
    public void connect(String[] args) throws IOException {
        socket = new Socket(args[0], Integer.parseInt(args[1]));

        bank = new Bank(socket);

        if (socket.isConnected()) {
            System.out.println(String.format("connected to bank-server %s:%s", args[0], args[1]));
        }
    }

    @Override
    public void disconnect() throws IOException {
        bank = null;
        socket.close();
        System.out.println("disconnected...");
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    static class Bank implements bank.Bank {
        private HashSet<String> accountNumbers = new HashSet<String>();
        private final Map<String, Account> accounts = new HashMap<>();
        private PrintWriter out = null;
        private BufferedReader in = null;

        Bank(Socket socket) throws IOException {
            this.out = new PrintWriter(socket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public Set<String> getAccountNumbers() {
            return this.accountNumbers;
        }

        @Override
        public String createAccount(String owner) throws IOException {

            // TODO Protocoll has to be implemented
            out.println(owner);
            out.flush();

            this.accountNumbers.add(owner);
            System.out.println(String.format("Sent new account info for %s to server", owner));

            return in.readLine();
        }

        @Override
        public boolean closeAccount(String number) {
            // TODO has to be implemented
            System.out.println("Bank.closeAccount has to be implemented");
            return false;
        }

        @Override
        public bank.Account getAccount(String number) {
            return accounts.get(number);
        }

        @Override
        public void transfer(bank.Account from, bank.Account to, double amount)
                throws IOException, InactiveException, OverdrawException {
            // TODO has to be implemented
            System.out.println("Bank.transfer has to be implemented");
        }

    }

    static class Account implements bank.Account {
        private String number;
        private String owner;
        private double balance;
        private boolean active = true;

        Account(String owner) {
            this.owner = owner;
            // TODO account number has to be set here or has to be passed using the constructor
        }

        @Override
        public double getBalance() {
            return balance;
        }

        @Override
        public String getOwner() {
            return owner;
        }

        @Override
        public String getNumber() {
            return number;
        }

        @Override
        public boolean isActive() {
            return active;
        }

        @Override
        public void deposit(double amount) throws InactiveException {
            // TODO has to be implemented

            System.out.println("Account.deposit has to be implemented");
        }

        @Override
        public void withdraw(double amount) throws InactiveException, OverdrawException {
            // TODO has to be implemented
            System.out.println("Account.withdraw has to be implemented");
        }

    }

}