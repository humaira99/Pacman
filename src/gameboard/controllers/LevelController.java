package gameboard.controllers;

import gameboard.ColourSet;
import gameboard.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LevelController {

    @FXML
    private AnchorPane windw;

    @FXML
    public void easyLevel(ActionEvent actionEvent) {
        GameManager.level = 1;
        setStartPressed(1);
    }

    @FXML
    public void middleLevel(ActionEvent actionEvent) {
        GameManager.level = 2;
        setStartPressed(2);
    }

    @FXML
    public void hardLevel(ActionEvent actionEvent) {
        GameManager.level = 3;
        setStartPressed(3);

    }

    /**
     * When back is pressed on the setup window - takes the player to the previous start page
     * @param actionEvent When the back button is pressed on the setup page
     */
    @FXML
    public void backButton(javafx.event.ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/setup.fxml"));
            Parent root = fxmlLoader.load();
            windw.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
        Canvas canvas = new Canvas( 1225, 600 );
        root.getChildren().add( canvas );

        if(level == 1){ gameManager.drawBoardEasy(); }
        if(level == 2){ gameManager.drawBoardIntermediate(); }
        if(level == 3){ gameManager.drawBoardHard(); }

        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));

        stage.show();
        windw.getScene().getWindow().hide();
    }


}

