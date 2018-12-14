package gameboard;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Creates a bar obstacle for the maze i.e. a wall/island
 */
class BarObstacle extends Rectangle {

    static double THICKNESS = 25;
    /**
     * Makes a new obstacle with the set orientation and length
     * Sets colour to the colour the player chose during set up, else sets to the default (cadetblue)
     * @param x x-coordinate of the bar
     * @param y y-coordinate of the bar
     * @param orientation - horizontal or vertical
     * @param length - the length of the bar (1 == 100px)
     */
    BarObstacle(double x, double y, String orientation, double length) {
        this.setX(x);
        this.setY(y);
        if (orientation.equals("horizontal")) {
            this.setHeight(BarObstacle.THICKNESS);
            this.setWidth(length * BarObstacle.THICKNESS);
        } else {
            this.setHeight(length * BarObstacle.THICKNESS);
            this.setWidth(BarObstacle.THICKNESS);
        }
        if(ColourSet.wall != null) {
            this.setFill(ColourSet.wall);
        }
        else
            this.setFill(Color.CADETBLUE);

        this.setStrokeWidth(3);
    }
}
