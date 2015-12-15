package com.denver.lionfriend.main;

import com.denver.lionfriend.client.ClientDriver;
import com.denver.lionfriend.server.ServerDriver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

/**
 * Created by Malindu Warapitiya on 12/12/15.
 */
public class ServerController {

    private static boolean STATUS = false;

    @FXML
    private ToggleButton switchBtn;

    /**
     * onPress of the start button in Server UI
     */
    @FXML
    void startEngine() {

        if (switchBtn.isSelected()) {
            //start the server
            ServerDriver.getInstance().startServer();
            ServerController.STATUS = true;
            switchBtn.setText("Stop");
        } else {
            //if only server is already in start mode
            if (ServerController.STATUS) {
                ServerDriver.getInstance().stopServer();
            }
            switchBtn.setText("Start");
        }
    }

    /**
     * exitApplication method invoke when the server UI onClose
     *
     * @param event
     */
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }
}
