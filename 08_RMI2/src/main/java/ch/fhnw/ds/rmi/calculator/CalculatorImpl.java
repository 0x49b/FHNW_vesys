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

        long startTime = System.currentTimeMillis();

        System.err.println("Eintritt");
        System.out.println(System.identityHashCode(this));
        System.out.println(Thread.currentThread());


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("Austritt");
        System.out.println(System.identityHashCode(this));
        System.out.println(Thread.currentThread());

        long stopTime = System.currentTimeMillis();

        System.out.println("duration " + (stopTime - startTime) + "ms");
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
