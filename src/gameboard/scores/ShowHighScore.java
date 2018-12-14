package gameboard.scores;

import gameboard.controllers.ScoresController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Creates a new window which shows the all time highscore list
 */
public class ShowHighScore {

    /**
     * Creates a new window pop up which shows the highscores when the 'view highscore' list button is pressed
     */
    public void showHighScore() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/highscore.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Highscores");
            Scene theScene = new Scene(root);
            theScene.getStylesheets().add(getClass().getResource("/gameboard/style.css").toExternalForm());
            stage.setScene(theScene);
            ScoresController controller = fxmlLoader.getController();
            controller.high();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


