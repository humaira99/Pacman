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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private GridPane content;

    @FXML
    private GridPane contentset;

    @FXML
    TextField username;

    public static String uname;

    @FXML
    public void startButtonPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../screens/setup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            content.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        uname = username.getText();
    }

    @FXML
    public void setBackPressed(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("../screens/start.fxml"));
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
        //theScene.getStylesheets().add("screens/style.css");
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
        set.getCookieCol(cookieColourPicker.getValue());

    }

    @FXML
    ColorPicker wallColourPicker;

    @FXML
    public void wallColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
        set.getWallCol(wallColourPicker.getValue());


    }

    @FXML
    ColorPicker backColourPicker;

    @FXML
    public void bkgColour(javafx.event.ActionEvent actionEvent){

        ColourSet set = new ColourSet();
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
    Label totalScore;

    @FXML
    public void scores(){
       CalculateScore.calculateScore();

        topRound.setText("" + CalculateScore.order.get(2));
        middleRound.setText("" + CalculateScore.order.get(1));
        lowRound.setText("" + CalculateScore.order.get(0));

        topScore.setText("" + CalculateScore.sc.get(2));
        middleScore.setText("" + CalculateScore.sc.get(1));
        lowScore.setText("" + CalculateScore.sc.get(0));


        totalScore.setText("" + CalculateScore.total);



    }

    @FXML
    Label s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

    @FXML
    Label n1, n2, n3, n4, n5, n6, n7, n8, n9, n10;

    @FXML
    public void showHighscore(javafx.event.ActionEvent actionEvent) {

        ShowHighScore show = new ShowHighScore();
        show.ShowHighScore();

    }

    @FXML
    public void high(){

        n1.setText("" +ScoreFile.name.get(0));
        n2.setText("" +ScoreFile.name.get(1));
        n3.setText("" +ScoreFile.name.get(2));
        n4.setText("" +ScoreFile.name.get(3));
        n5.setText("" +ScoreFile.name.get(4));
        n6.setText("" +ScoreFile.name.get(5));
        n7.setText("" +ScoreFile.name.get(6));
        n8.setText("" +ScoreFile.name.get(7));
        n9.setText("" +ScoreFile.name.get(8));
        n10.setText("" +ScoreFile.name.get(9));

        s1.setText("" +ScoreFile.score.get(0));
        s2.setText("" +ScoreFile.score.get(1));
        s3.setText("" +ScoreFile.score.get(2));
        s4.setText("" +ScoreFile.score.get(3));
        s5.setText("" +ScoreFile.score.get(4));
        s6.setText("" +ScoreFile.score.get(5));
        s7.setText("" +ScoreFile.score.get(6));
        s8.setText("" +ScoreFile.score.get(7));
        s9.setText("" +ScoreFile.score.get(8));
        s10.setText("" +ScoreFile.score.get(9));

    }

}

