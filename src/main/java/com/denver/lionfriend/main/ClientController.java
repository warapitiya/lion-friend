package com.denver.lionfriend.main;

import com.denver.lionfriend.client.ClientDriver;
import com.denver.lionfriend.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Created by Malindu Warapitiya on 12/13/15.
 */
public class ClientController {

    @FXML
    private VBox chatView;

    @FXML
    private TextArea messageConsole;

    @FXML
    private ListView<String> userList;

    @FXML
    void initialize() {
        ClientDriver.getInstance().registerUser();
        ClientDriver.getInstance().setChatView(chatView);
    }

    @FXML
    private void send() {
        ClientDriver.getInstance().sendMessage(messageConsole.getText(), User.getInstance().getNickname(), "All Users");
        messageConsole.setText("");
    }
}
