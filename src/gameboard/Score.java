package gameboard;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ImageView imageView, imageView2, imageView3;

    /**
     * Displays the players score
     * Displays the number of lives
     * @param root Group where the score and lives labels are added
     */
    Score(Group root) {
        this.score = new Text(BarObstacle.THICKNESS * 4, BarObstacle.THICKNESS * 28, "Score: 0");
        this.lifes = new Text(BarObstacle.THICKNESS * 16, BarObstacle.THICKNESS * 28,"Lives: ");
        score.setFill(Color.MAGENTA);
        score.setFont(Font.font("Arial", 30));

        lifes.setFill(Color.LIGHTSEAGREEN);
        lifes.setFont(Font.font("Arial", 30));

        Image image = new Image("characters/images/pacman.png");

        imageView = new ImageView(image);
        imageView2 = new ImageView(image);
        imageView3 = new ImageView(image);

        imageView.setX(BarObstacle.THICKNESS * 20);
        imageView.setY(BarObstacle.THICKNESS * 26);
        imageView2.setX(BarObstacle.THICKNESS * 23);
        imageView2.setY(BarObstacle.THICKNESS * 26);
        imageView3.setX(BarObstacle.THICKNESS * 26);
        imageView3.setY(BarObstacle.THICKNESS * 26);

        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView2.setFitHeight(40);
        imageView2.setFitWidth(40);
        imageView3.setFitHeight(40);
        imageView3.setFitWidth(40);

        root.getChildren().add(score);
        root.getChildren().add(lifes);
        root.getChildren().add(imageView);
        root.getChildren().add(imageView2);
        root.getChildren().add(imageView3);

    }

}
