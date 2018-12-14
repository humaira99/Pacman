package gameboard.controllers;

import gameboard.ColourSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;

/**
 * Controller to control action events of the Setup window
 */
public class SetupController {

    @FXML
    private GridPane contentset;

    /**
     * When back is pressed on the setup window - takes the player to the previous start page
     * @param actionEvent When the back button is pressed on the setup page
     */
    @FXML
    public void setBackPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/screens/start.fxml"));
            Parent root1 = fxmlLoader1.load();
            contentset.getChildren().setAll(root1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * When next button is pressed, goes to next screen which is levels
     * @param actionEvent Button press
     */
    @FXML
    public void setStartPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/screens/level.fxml"));
            Parent root = fxmlLoader1.load();
            contentset.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    ColorPicker cookieColourPicker;

    /**
     * Gets the colour that the player has chosen for the cookies in the game
     * @param actionEvent when the value of the colour picker has changed
     */
    @FXML
    public void cookieColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        set.getCookieCol(cookieColourPicker.getValue());

    }

    @FXML
    ColorPicker wallColourPicker;

    /**
     * Gets the colour that the player has chosen for the walls in the game
     * @param actionEvent when the value of the colour picker has changed
     */
    @FXML
    public void wallColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        set.getWallCol(wallColourPicker.getValue());

    }

    @FXML
    ColorPicker backColourPicker;

    /**
     * Gets the colour that the player has chosen for the background in the game
     * @param actionEvent when the value of the colour picker has changed
     */

    @FXML
    public void bkgColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        set.getBackgroundCol(backColourPicker.getValue());

    }
}
