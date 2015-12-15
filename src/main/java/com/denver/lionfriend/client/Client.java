package com.denver.lionfriend.client;

import com.denver.lionfriend.entity.User;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Malindu Warapitiya on 12/13/15.
 */
public class Client extends UnicastRemoteObject implements IClient {

    /**
     * Constructor override
     * @throws RemoteException
     */
    protected Client() throws RemoteException {
    }

    /**
     * Call the method addToList when a new user logged to the chat room
     * @param clientsName
     * @throws RemoteException
     */
    public void updateUserList(List<String> clientsName) throws RemoteException {
        ClientDriver.getInstance().addToList(clientsName);
    }


    /**
     * method invoke when a new message arrived
     * @param msg
     * @param fromUser
     * @throws RemoteException
     */
    public void msgArrived(final String msg, final String fromUser) throws RemoteException {
        try {

            Platform.runLater(new Runnable() {
                public void run() {
                    VBox chatDivision = new VBox();
                    Text a = new Text(fromUser);
                    a.setFill(Color.DARKBLUE);
                    chatDivision.getChildren().add(a);
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
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
