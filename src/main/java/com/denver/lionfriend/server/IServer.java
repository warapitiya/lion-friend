package com.denver.lionfriend.server;

import com.denver.lionfriend.client.IClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public interface IServer extends Remote {
    boolean registerToServer(IClient client, String name) throws RemoteException;

    void msgToServer(String msg, String fromUser, String toName) throws RemoteException;

    void logoutToServer(IClient client, String name) throws RemoteException;
}
