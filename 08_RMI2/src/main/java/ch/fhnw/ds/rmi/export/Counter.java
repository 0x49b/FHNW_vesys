package ch.fhnw.ds.rmi.export;

import java.rmi.*;

public interface Counter extends Remote {
    int reset() throws RemoteException;
    int getValue() throws RemoteException;
    int increment() throws RemoteException;
    
    Counter migrateBack() throws RemoteException;
    Counter migrateTo(String host) throws RemoteException;
}