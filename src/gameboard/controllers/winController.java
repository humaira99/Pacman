package gameboard.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class winController {

    public static winController win;

    public void winController() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/win.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("You Win");
            Scene theScene = new Scene(root);
            theScene.getStylesheets().add(getClass().getResource("/gameboard/style.css").toExternalForm());
            stage.setScene(theScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static winController getInstance(){
        if(win == null){
            win = new winController();
            win.winController();
        }
        return win;
    }

}
