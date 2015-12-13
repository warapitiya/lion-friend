package com.denver.lionfriend.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public interface IClient extends Remote {

    void updateUserList(List<String> clientsName) throws RemoteException;
    void msgArrived(String msg, String fromUser) throws RemoteException;

}
