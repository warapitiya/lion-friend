package com.denver.lionfriend.server;

import com.denver.lionfriend.client.IClient;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ServerDriver extends UnicastRemoteObject implements IServer {

    /**
     * Singleton Object
     */
    private static ServerDriver instance = null;

    /**
     * Registry collection
     */
    private static Registry reg = null;

    /**
     * Server bind name
     */
    private static final String SERVER_NAME = "LionFriendServer";


    private HashMap<String, IClient> users = new HashMap<String, IClient>();


    /**
     * Private constructor
     *
     * @throws RemoteException
     */
    private ServerDriver() throws RemoteException {
    }

    /**
     * Get instance method
     *
     * @return
     */
    public static ServerDriver getInstance() {
        if (instance == null) {
            try {
                instance = new ServerDriver();
            } catch (RemoteException e) {
                Logger.getLogger(ServerDriver.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return instance;
    }

    /**
     * Start the server
     */
    public void startServer() {

        try {
            reg = java.rmi.registry.LocateRegistry.createRegistry(1099);

            Naming.bind(ServerDriver.SERVER_NAME, new ServerDriver());
            System.out.println("Server Ready");
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ServerDriver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerDriver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Stop the server
     */
    public void stopServer() {
        try {
            reg.unbind(ServerDriver.SERVER_NAME);
            UnicastRemoteObject.unexportObject(reg, true);
            System.out.println("Server Stopped");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerDriver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServerDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean RegisterToServer(IClient client, String name) throws RemoteException {

        users.put(name, client);

        return true;
    }

    public void MsgToServer(String msg, String fromUser, String toName) throws RemoteException {

        try {

            for (IClient item : users.values()) {
                item.MsgArrived(msg, fromUser);
            }
        } catch (Exception e) {
            System.out.println("Awa " +  e);
        }

    }

    public void LogoutToServer(IClient client, String name) throws RemoteException {

    }
}
