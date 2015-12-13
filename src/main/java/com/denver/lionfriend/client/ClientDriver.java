package com.denver.lionfriend.client;

import com.denver.lionfriend.entity.User;
import com.denver.lionfriend.server.IServer;
import javafx.scene.layout.VBox;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ClientDriver {

    private static IServer iServer;

    private static ClientDriver instance;

    private VBox chatView;

    private ClientDriver() {
    }

    public static ClientDriver getInstance() {
        if (instance == null) {
            instance = new ClientDriver();
        }
        return instance;
    }

    public void registerUser() {

        try {

            iServer = (IServer) Naming.lookup("//" + User.getInstance().getHostname() + "/LionFriendServer");

            iServer.registerToServer(new Client(), User.getInstance().getNickname());

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public boolean sendMessage(String message, String fromUser, String toUser) {
        try {
            iServer.msgToServer(message, fromUser, toUser);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public VBox getChatView() {
        return chatView;
    }

    public void setChatView(VBox chatView) {
        this.chatView = chatView;
    }
}
