package gameboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Controller to control action events of the Setup window
 */
public class SetupController {

    @FXML
    private GridPane contentset;

    /**
     * When back is pressed on the setup window - takes the player to the previous start page
     * @param actionEvent When the back button is pressed on the setup page
     */
    @FXML
    public void setBackPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("../screens/fxml/start.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
            contentset.getChildren().setAll(root1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * When start is pressed on the setup window - the game will start
     * @param actionEvent When the start button is pressed on the setup window
     */
    @FXML
    public void setStartPressed(javafx.event.ActionEvent actionEvent) {

        Stage stage = new Stage();
        stage.setTitle("Pacman");

        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene(theScene);

        Canvas canvas = new Canvas( 1225, 600 );
        root.getChildren().add( canvas );
        if(ColourSet.background != null) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(ColourSet.background);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
        GameManager gameManager = new GameManager(root);

        gameManager.drawBoardEasy();

        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));

        stage.show();
        contentset.getScene().getWindow().hide();
    }

    @FXML
    ColorPicker cookieColourPicker;

    /**
     * Gets the colour that the player has chosen for the cookies in the game
     * @param actionEvent when the value of the colour picker has changed
     */
    @FXML
    public void cookieColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        set.getCookieCol(cookieColourPicker.getValue());

    }

    @FXML
    ColorPicker wallColourPicker;

    /**
     * Gets the colour that the player has chosen for the walls in the game
     * @param actionEvent when the value of the colour picker has changed
     */
    @FXML
    public void wallColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        set.getWallCol(wallColourPicker.getValue());

    }

    @FXML
    ColorPicker backColourPicker;

    /**
     * Gets the colour that the player has chosen for the background in the game
     * @param actionEvent when the value of the colour picker has changed
     */

    @FXML
    public void bkgColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        set.getBackgroundCol(backColourPicker.getValue());

    }
}
