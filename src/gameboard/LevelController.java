package gameboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class LevelController {

    public void easyLevel(ActionEvent actionEvent) {
        GameManager.level = 1;
    }

    public void middleLevel(ActionEvent actionEvent) {
        GameManager.level = 2;
    }

    public void hardLevel(ActionEvent actionEvent) {
        GameManager.level = 3;
    }

    @FXML
    GridPane contents;

    /**
     * When back is pressed on the setup window - takes the player to the previous start page
     * @param actionEvent When the back button is pressed on the setup page
     */
    public void backButton(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("../screens/fxml/setup.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
            contents.getChildren().setAll(root1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

