package characters;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class Pacman extends Circle {

    public Pacman(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        Image pacmanpic = new Image ("characters/pacman.png");
        this.setFill(new ImagePattern(pacmanpic));
    }
}
