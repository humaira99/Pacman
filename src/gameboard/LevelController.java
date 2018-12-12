package gameboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LevelController {

    public void easyLevel(ActionEvent actionEvent) {
        GameManager.level = 1;
        setStartPressed(1);
    }

    public void middleLevel(ActionEvent actionEvent) {
        GameManager.level = 2;
        setStartPressed(2);
    }

    public void hardLevel(ActionEvent actionEvent) {
        GameManager.level = 3;
        setStartPressed(3);

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

    static Canvas canvas = new Canvas( 1225, 600 );

    /**
     * When start is pressed on the setup window - the game will start
     *
     */
    public void setStartPressed(Integer level) {

        Stage stage = new Stage();
        stage.setTitle("Pacman");

        Group root = new Group();
        Scene theScene = new Scene(root, ColourSet.background );
        stage.setScene(theScene);
        GameManager gameManager = new GameManager(root);

        root.getChildren().add( canvas );

        if(level == 1){ gameManager.drawBoardEasy(); }
        if(level == 2){ gameManager.drawBoardIntermediate(); }
        if(level == 3){ gameManager.drawBoardHard(); }

        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));

        stage.show();
        contents.getScene().getWindow().hide();
    }


}

