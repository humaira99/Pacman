package characters;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Generates a cherry that Pacman eats to increase the score by value variable
 */
public class Cherry extends Circle {

    private int value;

    /**
     * Creates a cherry object, sets the value (number of points scores when pacman eats it) and image icon
     * @param x x-coordinate of where the cherry will be
     * @param y y-coordinate of where the cherry will be
     */
    public Cherry(double x, double y) {
        this.value = 20;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(20);
        Image cherrypic = new Image("characters/images/cherry.png");
        this.setFill(new ImagePattern(cherrypic));
    }

    /**
     * Getter for private variable value
     * @return Points the cherry is worth
     */
    public int getValue() {
        return value;
    }

    /**
     * Hides the cherry when it has been eaten
     */
    public void hide() {
        this.setVisible(false);
    }


}
