package com.denver.lionfriend.main;

import com.denver.lionfriend.client.ClientDriver;
import com.denver.lionfriend.entity.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ClientMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            User.getInstance().setHostname(dialogPrompt("localhost", "Hostname", "Enter the chat room server hostname"));

            User.getInstance().setNickname(dialogPrompt("John", "Nickname", "Enter your nickname for the chat room"));

            Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
            primaryStage.setTitle("Lion Friend Client : " + User.getInstance().getNickname());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        ClientDriver.getInstance().logout();
        System.exit(1);
    }

    private String dialogPrompt(String initial, String title, String message) {

        TextInputDialog dialog = new TextInputDialog(initial);
        dialog.setTitle(title + " prompt");
        dialog.setHeaderText(message);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equalsIgnoreCase("")) {
                return initial;
            }
            return result.get();
        }

        return initial;
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
