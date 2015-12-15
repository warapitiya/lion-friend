package com.denver.lionfriend.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public interface IClient extends Remote {

    /**
     * update user list
     * @param clientsName
     * @throws RemoteException
     */
    void updateUserList(List<String> clientsName) throws RemoteException;

    /**
     * message arrived
     * @param msg
     * @param fromUser
     * @throws RemoteException
     */
    void msgArrived(String msg, String fromUser) throws RemoteException;

}
