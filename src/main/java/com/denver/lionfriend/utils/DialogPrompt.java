package com.denver.lionfriend.utils;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by Malindu Warapitiya on 12/15/15.
 */
public class DialogPrompt {

    /**
     * Shows the dialog prompt and return the value
     * @param initial
     * @param title
     * @param message
     * @return
     */
    public static String show(String initial, String title, String message) {

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
        } else {
            System.exit(1);
        }

        return initial;
    }
}
