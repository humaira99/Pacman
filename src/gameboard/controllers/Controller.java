package gameboard.controllers;

import gameboard.scores.ShowHighScore;
import gameboard.scores.CalculateScore;
import gameboard.scores.ScoreFile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controls the action events from the score pop up screen
 * Sets score pop up screen labels
 * Sets highscore pop up screen labels
 */
public class Controller {

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

    /**
     * Displays the scores from highest round to lowest on the score pop up
     */
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

    /**
     * Creates a new high score pop up screen when the "view highscore" button is pressed
     * @param actionEvent This pop up is shown when the "View Highscore" button is pressed on the score screen
     */
    @FXML
    public void showHighscore(javafx.event.ActionEvent actionEvent) {

        ShowHighScore show = new ShowHighScore();
        show.ShowHighScore();

    }

    /**
     * Displays the top 10 highest scores and usernames of the player who got that score on the highscreen pop up
     */
    @FXML
    public void high(){

        n1.setText("" + ScoreFile.name.get(0));
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

