package characters;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class Pacman extends Circle {
    //private AnimationTimer leftAnimation, rightAnimation, upAnimation, downAnimation;
    //private Maze maze;

    public Pacman(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        Image pacmanpic = new Image ("characters/pacman.png");
        this.setFill(new ImagePattern(pacmanpic));
        //this.leftAnimation = createAnimation("left");
    }

    /**
     * Creates an animation of the movement.
     * @param direction
     * @return
     *
    private AnimationTimer createAnimation(String direction) {
        Pacman pacman = this;
        double step = 5;
        return new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                switch (direction) {
                    case "left":
                        if (!maze.isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setRotate(180);
                            pacman.setCenterX(pacman.getCenterX() - step);
                            checkCookieCoalition(pacman, "x");
                            checkGhostCoalition();
                        }
                        break;
                    case "right":
                        if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setRotate(0);
                            pacman.setCenterX(pacman.getCenterX() + step);
                            checkCookieCoalition(pacman, "x");
                            checkGhostCoalition();
                        }
                        break;
                    case "up":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
                            pacman.setRotate(270);
                            pacman.setCenterY(pacman.getCenterY() - step);
                            checkCookieCoalition(pacman, "y");
                            checkGhostCoalition();
                        }
                        break;
                    case "down":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
                            pacman.setRotate(90);
                            pacman.setCenterY(pacman.getCenterY() + step);
                            checkCookieCoalition(pacman, "y");
                            checkGhostCoalition();
                        }
                        break;
                }
                if(pacman.getCenterX() < 0.0){
                    pacman.setCenterX(1200 + pacman.getRadius());

                }
                if(pacman.getCenterX() > 1250){
                    pacman.setCenterX(0 + pacman.getRadius());

                }
            }
        };
    }*/


}
