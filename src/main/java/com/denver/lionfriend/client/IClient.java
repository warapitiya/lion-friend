package com.denver.lionfriend.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public interface IClient extends Remote {

    void UpdateUserList(List<String> ClientsName) throws RemoteException;
    void MsgArrived(String msg, String FromUser) throws RemoteException;

}
