package com.denver.lionfriend.client;

import com.denver.lionfriend.entity.User;
import javafx.application.Platform;
import javafx.geometry.Insets;
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
        System.out.println("update user");
        ClientDriver.getInstance().addToList(clientsName);
    }

    public void msgArrived(final String msg, final String fromUser) throws RemoteException {
        try {

            Platform.runLater(new Runnable() {
                public void run() {
                    VBox chatDivision = new VBox();
                    chatDivision.getChildren().add(new Text(fromUser));
                    chatDivision.getChildren().add(new Text(msg));

                    if(User.getInstance().getNickname().equals(fromUser)){
                        chatDivision.setStyle("-fx-background-color: linear-gradient(#D7E7FF, #FFFFFF); -fx-effect: dropshadow(three-pass-box, gray, 10, 0, 0, 0)");
                    } else{
                        chatDivision.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #D7E7FF); -fx-effect: dropshadow(three-pass-box, gray, 10, 0, 0, 0)");
                    }

                    VBox.setMargin(chatDivision, new Insets(5, 5, 5, 5));
                    chatDivision.setSpacing(5);
                    chatDivision.setPadding(new Insets(5, 5, 5, 5));
                    ClientDriver.getInstance().getChatView().getChildren().add(chatDivision);
                }
            });
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }
}
