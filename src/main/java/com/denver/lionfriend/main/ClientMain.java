package com.denver.lionfriend.main;

import com.denver.lionfriend.client.ClientDriver;
import com.denver.lionfriend.entity.User;
import com.denver.lionfriend.utils.DialogPrompt;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ClientMain extends Application {

    /**
     * start the client primaryStage
     * @param primaryStage main UI component
     */
    @Override
    public void start(Stage primaryStage) {
        try {

            User.getInstance().setHostname(DialogPrompt.show("localhost", "Hostname", "Enter the chat room server hostname"));

            User.getInstance().setNickname(DialogPrompt.show("John", "Nickname", "Enter your nickname for the chat room"));

            Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
            primaryStage.setTitle("Lion Friend Client : " + User.getInstance().getNickname());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, e);
        }

    }


    /**
     * Logout user from chat room and exit
     */
    @Override
    public void stop() {
        ClientDriver.getInstance().logout();
        System.exit(1);
    }


    /**
     * Main method of the server
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
