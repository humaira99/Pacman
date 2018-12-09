package screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    public static String uname;

    /**
     * When start is pressed, the set up window is shown
     * The username entered is saved
     * @param actionEvent when the start button on the start page is pressed
     */
    @FXML
    public void startButtonPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/setup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            content.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        uname = username.getText();
    }
}
