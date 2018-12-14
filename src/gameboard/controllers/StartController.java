package gameboard.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Controls action events from the start window
 */
public class StartController {

    @FXML
    private GridPane content;

    @FXML
    TextField username;

    @FXML
    Label validate;

    public static String uname;

    /**
     * When start is pressed, the set up window is shown
     * The username entered is checked for validity by calling the isInputValid method
     * If username is valid, it is saved
     * @param actionEvent when the start button on the start page is pressed
     */
    @FXML
    public void startButtonPressed(javafx.event.ActionEvent actionEvent) {

        if(isInputValid() == Boolean.TRUE) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/setup.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                content.getChildren().setAll(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            uname = username.getText();
        }
    }

    /**
     * Checks if the player has typed in a username
     * If not, prints an error message and adds a red border to the username input box
     * @return <code>true</code> if valid; <code>false</code> if invalid
     */
    @FXML
    public Boolean isInputValid() {
        if (username.getText() == null || username.getText().length() == 0) {
            validate.setText("Please enter a username");
            username.setStyle("-fx-border-color: red; -fx-border-width: 3");
            return false;
        }
        else {
            return true;
        }
    }


}
