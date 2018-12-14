package gameboard.scores;

import gameboard.controllers.ScoresController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Creates a window which shows the players scores for each round
 * Implements singleton design pattern
 */
public class ShowScores {

    public static ShowScores show;
    /**
     * Creates a score pop up after the game has ended to show scores from all 3 rounds, in descending order
     */
    private void showScores() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/score.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Score");
            Scene theScene = new Scene(root);
            theScene.getStylesheets().add(getClass().getResource("/gameboard/style.css").toExternalForm());
            stage.setScene(theScene);
            ScoresController controller = fxmlLoader.getController();
            controller.scores();
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Singleton design pattern to ensure only one score pop up is shown per game
     * @return single instance of the score pop up board
     */
    public static ShowScores getInstance(){
        if(show == null){
            show = new ShowScores();
            show.showScores();
        }
        return show;

    }

}
