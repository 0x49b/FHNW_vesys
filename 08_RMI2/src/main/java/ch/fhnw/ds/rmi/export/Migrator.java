package ch.fhnw.ds.rmi.export;

import java.rmi.*;

public interface Migrator extends Remote {
    Counter migrate(Counter counter) throws RemoteException;
}