package com.denver.lionfriend.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ServerMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/server.fxml"));
            primaryStage.setTitle("Lion Friend Server");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Main method of the server
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
