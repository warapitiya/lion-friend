package com.denver.lionfriend.client;

import com.denver.lionfriend.server.IServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ClientDriver extends UnicastRemoteObject implements IClient {

    private static IServer iServer;

    public ClientDriver() throws RemoteException {
    }

    public void registerUserAndSendMessage() {

        String host = "localhost";

        try {

            iServer = (IServer) Naming.lookup("//" + host + "/LionFriendServer");

            String name = "Yo" + Math.random();

            iServer.RegisterToServer(new ClientDriver(), name);

            iServer.MsgToServer("Hello World", name, "All Users");

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void UpdateUserList(List<String> ClientsName) throws RemoteException {

    }

    public void MsgArrived(String msg, String FromUser) throws RemoteException {
        System.out.println(msg + " : " + FromUser);
    }
}
