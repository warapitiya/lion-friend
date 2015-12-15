package com.denver.lionfriend.main;

import com.denver.lionfriend.client.ClientDriver;
import com.denver.lionfriend.entity.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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


    /**
     * initialize method invoke when the client UI onStart
     */
    @FXML
    void initialize() {
        ClientDriver.getInstance().registerUser();
        ClientDriver.getInstance().setChatView(chatView);
        ClientDriver.getInstance().setUserList(userList);
    }

    /**
     * onPress of send button in UI
     */
    @FXML
    private void send() {
        ClientDriver.getInstance().sendMessage(messageConsole.getText(), User.getInstance().getNickname(), userList.getSelectionModel().getSelectedItem());
        messageConsole.setText("");
    }

    /**
     * exitApplication method invoke when the client UI onClose
     *
     * @param event
     */
    @FXML
    public void exitApplication(ActionEvent event) {
        ClientDriver.getInstance().logout();
        Platform.exit();
    }

}
