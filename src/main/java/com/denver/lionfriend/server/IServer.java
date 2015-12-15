package com.denver.lionfriend.server;

import com.denver.lionfriend.client.IClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public interface IServer extends Remote {

    /**
     * register a user to the server
     * @param client
     * @param name
     * @return
     * @throws RemoteException
     */
    boolean registerToServer(IClient client, String name) throws RemoteException;

    /**
     * message from a server
     * @param msg
     * @param fromUser
     * @param toName
     * @throws RemoteException
     */
    void msgToServer(String msg, String fromUser, String toName) throws RemoteException;

    /**
     * logout a user
     * @param client
     * @param name
     * @throws RemoteException
     */
    void logoutToServer(IClient client, String name) throws RemoteException;
}
