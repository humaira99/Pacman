package gameboard.scores;

import gameboard.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Creates a window which shows the players scores
 */

public class ShowScores {
    public static ShowScores show;
    /**
     * Creates a score pop up after the game has ended to show scores from all 3 rounds, in descending order
     */
    public void ShowScores() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/score.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Score");
            Scene theScene = new Scene(root);
            theScene.getStylesheets().add(getClass().getResource("/gameboard/style.css").toExternalForm());
            stage.setScene(theScene);
            Controller controller = fxmlLoader.getController();
            controller.scores();
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ShowScores getInstance(){
        if(show == null){
            show = new ShowScores();
            show.ShowScores();
        }
        return show;

    }

}
