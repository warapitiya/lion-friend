package com.denver.lionfriend.client;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Malindu Warapitiya on 12/13/15.
 */
public class Client extends UnicastRemoteObject implements IClient {

    protected Client() throws RemoteException {
    }

    public void updateUserList(List<String> clientsName) throws RemoteException {
        for (String s : clientsName) {
            System.out.println(s);
        }
    }

    public void msgArrived(final String msg, final String fromUser) throws RemoteException {
        try {

            Platform.runLater(new Runnable() {
                public void run() {
                    VBox chatDivision = new VBox();
                    chatDivision.getChildren().add(new Text(fromUser));
                    chatDivision.getChildren().add(new Text(msg));
                    chatDivision.setStyle("-fx-background-color: skyblue");
                    ClientDriver.getInstance().getChatView().getChildren().add(chatDivision);
                }
            });
        } catch (Exception e) {
            System.out.println("methana kela una");
        }
    }
}
