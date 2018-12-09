package gameboard;



import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Displays the players score and number of lives
 */
public class Score {

    /**
     * gets the score
     * @return current score
     */
    public Text getScore() {
        return score;
    }

    public Text score;
    public Text lifes;

    /**
     * Displays the players score
     * Displays the number of lives
     * @param root Group where the score and lives labels are added
     */
    Score(Group root) {
        this.score = new Text(BarObstacle.THICKNESS * 4, BarObstacle.THICKNESS * 28, "Score: 0");
        this.lifes = new Text(BarObstacle.THICKNESS * 20, BarObstacle.THICKNESS * 28,"Lifes: 3");
        score.setFill(Color.MAGENTA);
        score.setFont(Font.font("Arial", 30));

        lifes.setFill(Color.MAROON);
        lifes.setFont(Font.font("Arial", 30));

        root.getChildren().add(score);
        root.getChildren().add(lifes);
    }

}
