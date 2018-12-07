package scores.score;

import gameboard.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowScores {

    public void ShowScores() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("score/score.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Score");
            Scene theScene = new Scene(root);
            stage.setScene(theScene);
            Controller controller = fxmlLoader.getController();
            controller.scores();
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
