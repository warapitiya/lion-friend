package com.denver.lionfriend.server;

import com.denver.lionfriend.client.IClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public interface IServer extends Remote {
    boolean RegisterToServer(IClient client, String Name) throws RemoteException;
    void MsgToServer(String msg, String FrmUser, String ToName) throws RemoteException;
    void LogoutToServer(IClient client, String Name) throws RemoteException;
}
