package characters;


import gameboard.Coalition;
import gameboard.GameManager;
import gameboard.Maze;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class Pacman extends Circle {

    private GameManager game;
    private Maze maze;


    public Pacman(GameManager game, double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        Image pacmanpic = new Image ("characters/pacman.png");
        this.setFill(new ImagePattern(pacmanpic));
        this.game = game;
    }

    /**
     * Creates an animation of the movement.
     * @param direction
     * @return
     */
    public AnimationTimer createAnimation(String direction) {
        Pacman pacman = this;
        double step = 5;
        Coalition coalition = new Coalition(game, this, game.getCookieSet(), game.getGhosts());
        maze = game.getMaze();
        return new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                switch (direction) {
                    case "left":
                        if (!maze.isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setRotate(180);
                            pacman.setCenterX(pacman.getCenterX() - step);
                            coalition.checkCookieCoalition(pacman, "x");
                            coalition.checkGhostCoalition();
                        }
                        break;
                    case "right":
                        if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setRotate(0);
                            pacman.setCenterX(pacman.getCenterX() + step);
                            coalition.checkCookieCoalition(pacman, "x");
                            coalition.checkGhostCoalition();
                        }
                        break;
                    case "up":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
                            pacman.setRotate(270);
                            pacman.setCenterY(pacman.getCenterY() - step);
                            coalition.checkCookieCoalition(pacman, "y");
                            coalition.checkGhostCoalition();
                        }
                        break;
                    case "down":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
                            pacman.setRotate(90);
                            pacman.setCenterY(pacman.getCenterY() + step);
                            coalition.checkCookieCoalition(pacman, "y");
                            coalition.checkGhostCoalition();
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
    }


}
