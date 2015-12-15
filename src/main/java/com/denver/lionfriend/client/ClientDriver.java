package com.denver.lionfriend.client;

import com.denver.lionfriend.entity.User;
import com.denver.lionfriend.server.IServer;
import com.denver.lionfriend.utils.DialogPrompt;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ClientDriver {

    /**
     * IServer reference
     */
    private static IServer iServer;

    /**
     * ClientDriver singleton instance
     */
    private static ClientDriver instance;

    /**
     * Client instance for the current client
     */
    private Client client;

    /**
     * VBOX instance to update chat messages in client UI
     */
    private VBox chatView;

    /**
     * ListView instance to update user list in client UI
     */
    private ListView<String> userList;

    /**
     * Register status
     */
    private boolean registerStatus = false;

    private ClientDriver() {
    }

    /**
     * Get Instance of this singleton class
     *
     * @return
     */
    public static ClientDriver getInstance() {
        if (instance == null) {
            instance = new ClientDriver();
        }
        return instance;
    }


    /**
     * Connect the client to the given host and register the user
     */
    public void registerUser() {

        try {

            if (!registerStatus) {
                iServer = (IServer) Naming.lookup("//" + User.getInstance().getHostname() + "/LionFriendServer");
                client = new Client();
                registerStatus = true;
            }

            boolean status = iServer.registerToServer(client, User.getInstance().getNickname());

            if (!status) {
                User.getInstance().setNickname(DialogPrompt.show("Ryan", "Nickname", "Enter your nickname for the chat room"));
                registerUser();
            }

        } catch (NotBoundException e) {
            Logger.getLogger(ClientDriver.class.getName()).log(Level.SEVERE, null, e);
        } catch (MalformedURLException e) {
            Logger.getLogger(ClientDriver.class.getName()).log(Level.SEVERE, null, e);
        } catch (RemoteException e) {
            Logger.getLogger(ClientDriver.class.getName()).log(Level.SEVERE, null, e);
        }

    }


    /**
     * Send message to the server
     *
     * @param message
     * @param fromUser
     * @param toUser
     * @return
     */
    public boolean sendMessage(String message, String fromUser, String toUser) {
        try {
            iServer.msgToServer(message, fromUser, toUser);
            return true;
        } catch (RemoteException e) {
            Logger.getLogger(ClientDriver.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }


    /**
     * update the client UI user list
     *
     * @param keys
     */
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

    /**
     * Logout the client
     */
    public void logout() {
        try {
            iServer.logoutToServer(client, User.getInstance().getNickname());
        } catch (RemoteException e) {
            Logger.getLogger(ClientDriver.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    /**
     * getChatView
     *
     * @return
     */
    public VBox getChatView() {
        return chatView;
    }


    /**
     * setChatView
     *
     * @param chatView
     */
    public void setChatView(VBox chatView) {
        this.chatView = chatView;
    }

    /**
     * getUserList
     *
     * @return
     */
    public ListView<String> getUserList() {
        return userList;
    }

    /**
     * setUserList
     *
     * @param userList
     */
    public void setUserList(ListView<String> userList) {
        this.userList = userList;
    }
}
