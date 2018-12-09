package characters;


import gameboard.ColourSet;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Generates a cookie that Pacman eats to increase the score by value variable
 */
public class Cookie extends Circle {

    private int value;

    /**
     * Creates a cookie object, sets the value (number of points scores when pacman eats it) and colour
     * @param x x-coordinate of where the cookie will be
     * @param y y-coordinate of where the cookie will be
     */
    public Cookie(double x, double y) {
        this.value = 5;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(12.5);
        if(ColourSet.cookie != null) {
            this.setFill(ColourSet.cookie);
        }
        else
            this.setFill(Color.SADDLEBROWN);
    }

    /**
     * Getter for private variable value
     * @return Points the cookie is worth
     */
    public int getValue() {
        return value;
    }

    /**
     * Hides the cookie when it has been eaten
     */
    public void hide() {
        this.setVisible(false);
    }

    /**
     * Shows the cookie again
     */
    public void show() {
        this.setVisible(true);
    }

}