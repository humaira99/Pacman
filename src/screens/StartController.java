package screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class StartController {

    @FXML
    private GridPane content;

    @FXML
    TextField username;

    public static String uname;

    @FXML
    public void startButtonPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../screens/setup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            content.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        uname = username.getText();
    }
}
