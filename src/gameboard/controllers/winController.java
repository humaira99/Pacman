package gameboard.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for win pop up
 * Implements singleton design pattern so only 1 screen is shown per game
 */
public class winController {

    public static winController win;

    /**
     * Creates a new pop up screen if the player wins which is an image saying "you win"
     */
    private void wincontroller() {
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

    /**
     * Singleton design patern
     * If a pop up has already been shown - does not make a new one in case program loops
     * @return single instance win pop up
     */
    public static winController getInstance(){
        if(win == null){
            win = new winController();
            win.wincontroller();
        }
        return win;
    }

}
