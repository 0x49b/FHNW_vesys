package ch.fhnw.ds.rmi.calculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
	private static final long serialVersionUID = -3166604013329444676L;

	public CalculatorImpl() throws RemoteException {
		super();
	}

	public CalculatorImpl(int port) throws RemoteException {
		super(port);
	}

	@Override
	public long add(long a, long b) {
		return a + b;
	}

	@Override
	public long sub(long a, long b) {
		return a - b;
	}

	@Override
	public long mul(long a, long b) {
		return a * b;
	}

	@Override
	public long div(long a, long b) {
		return a / b;
	}

}
