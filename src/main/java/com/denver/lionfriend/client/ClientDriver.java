package com.denver.lionfriend.client;

import com.denver.lionfriend.entity.User;
import com.denver.lionfriend.server.IServer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ClientDriver {

    private static IServer iServer;

    private static ClientDriver instance;

    private Client client;

    private VBox chatView;

    private ListView<String> userList;

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

            client = new Client();
            boolean status = iServer.registerToServer(client, User.getInstance().getNickname());

            if (!status) {
                System.out.println("User already exist");
            }

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

    public void addToList(List<String> keys) {
        final ObservableList<String> names = FXCollections.observableArrayList(keys);
        names.add(0, "Everyone");
        Platform.runLater(new Runnable() {
            public void run() {
                userList.setItems(names);
                userList.getSelectionModel().select(0);
            }
        });
    }

    public void logout() {
        try {
            iServer.logoutToServer(client, User.getInstance().getNickname());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public VBox getChatView() {
        return chatView;
    }

    public void setChatView(VBox chatView) {
        this.chatView = chatView;
    }

    public ListView<String> getUserList() {
        return userList;
    }

    public void setUserList(ListView<String> userList) {
        this.userList = userList;
    }
}
