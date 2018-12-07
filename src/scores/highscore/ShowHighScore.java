package scores.highscore;

import gameboard.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowHighScore {


    public void ShowHighScore() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("highscore/highscore.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Highscores");
            Scene theScene = new Scene(root);
            stage.setScene(theScene);
            Controller controller = fxmlLoader.getController();
            controller.high();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

