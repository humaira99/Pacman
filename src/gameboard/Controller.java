package gameboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private GridPane content;

    @FXML
    private GridPane contentset;

    @FXML
    public void startButtonPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            content.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void setBackPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("start.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
            contentset.getChildren().setAll(root1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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

        gameManager.drawBoard();

        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));

        stage.show();
        contentset.getScene().getWindow().hide();
    }

    @FXML
    ColorPicker cookieColourPicker;

    @FXML
    public void cookieColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        //Color col = cookieColourPicker.getValue();
        set.getCookieCol(cookieColourPicker.getValue());

    }

    @FXML
    ColorPicker wallColourPicker;

    @FXML
    public void wallColour(javafx.event.ActionEvent actionEvent){
        ColourSet set = new ColourSet();
        //Color col = wallColourPicker.getValue();
        set.getWallCol(wallColourPicker.getValue());

    }

    @FXML
    ColorPicker backColourPicker;

    @FXML
    public void bkgColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        //Color col = backColourPicker.getValue();
        set.getBackgroundCol(backColourPicker.getValue());

    }

    @FXML
    Label topRound;

    @FXML
    Label middleRound;

    @FXML
    Label lowRound;

    @FXML
    Label topScore;

    @FXML
    Label middleScore;

    @FXML
    Label lowScore;

    @FXML
    public void scores(){
        CalculateScore.calculateScore();

        topRound.setText("" + CalculateScore.order.get(2));
        middleRound.setText("" + CalculateScore.order.get(1));
        lowRound.setText("" + CalculateScore.order.get(0));

        topScore.setText("" + CalculateScore.sc.get(2));
        middleScore.setText("" + CalculateScore.sc.get(1));
        lowScore.setText("" + CalculateScore.sc.get(0));


    }



}

