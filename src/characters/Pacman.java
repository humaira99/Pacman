package characters;



import gameboard.Coalition;
import gameboard.GameManager;
import gameboard.Maze;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Creates a Pacman Character which the player controls
 * Pacman dies when caught by a ghost
 * Eats cookies to collect points
 *
 * Implements singleton design pattern so only one pacman is ever made per game
 */
public class Pacman extends Circle {

    private GameManager game;
    private Maze maze;
    /**
     * Pacman instance so it can be accessed by <code>getinstance()</code> to implement the singleton pattern
     */
    public static Pacman pacman;

    /**
     * Loads an image of pacman as the icon
     * @param game GameManager instance
     * @param x x-coordinate of pacman on the board
     * @param y y-coordinate of pacman on the board
     *
     */
    private Pacman(GameManager game, double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        Image pacmanpic = new Image("characters/images/pacman.png");
        this.setFill(new ImagePattern(pacmanpic));
        this.game = game;
    }

    /**
     * Creates a single instance of pacman (singleton pattern) If there is no pacman made already: creates one. If there is - returns existing pacman
     * @param game GameManager instance
     * @param x x-coordinate of pacman
     * @param y y-coordinate of pacman
     * @return single instance of pacman
     */
    public static Pacman getInstance(GameManager game, double x, double y){
        if(pacman == null){
            pacman = new Pacman(game, x, y);
        }
        return pacman;

    }


    /**
     * Creates an animation of the movement.
     * Rotates pacman so it is facing the way it is moving
     * Calls coalition class to check if pacman is touching a ghost/cookie
     * If pacman exits the doors on the left/right of the maze, he will enter from the opposite side
     * @param direction direction pacman is moving
     * @return pacman movement
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
                            coalition.checkCherryCoalition(pacman, "x");
                            coalition.checkGhostCoalition();
                        }
                        break;
                    case "right":
                        if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
                            pacman.setRotate(0);
                            pacman.setCenterX(pacman.getCenterX() + step);
                            coalition.checkCookieCoalition(pacman, "x");
                            coalition.checkCherryCoalition(pacman, "x");
                            coalition.checkGhostCoalition();
                        }
                        break;
                    case "up":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
                            pacman.setRotate(270);
                            pacman.setCenterY(pacman.getCenterY() - step);
                            coalition.checkCookieCoalition(pacman, "y");
                            coalition.checkCherryCoalition(pacman, "y");
                            coalition.checkGhostCoalition();
                        }
                        break;
                    case "down":
                        if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
                            pacman.setRotate(90);
                            pacman.setCenterY(pacman.getCenterY() + step);
                            coalition.checkCookieCoalition(pacman, "y");
                            coalition.checkCherryCoalition(pacman, "y");
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
