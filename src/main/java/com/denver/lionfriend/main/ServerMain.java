package com.denver.lionfriend.main;

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
public class ServerMain extends Application {

    /**
     * start the client primaryStage
     *
     * @param primaryStage main UI component
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/server.fxml"));
            primaryStage.setTitle("Lion Friend Server");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Logout user from chat room and exit
     */
    @Override
    public void stop() {
        Logger.getLogger(ServerMain.class.getName()).log(Level.INFO, "Shutting down server");
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
