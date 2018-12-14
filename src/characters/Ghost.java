package characters;



import gameboard.Coalition;
import gameboard.GameManager;
import gameboard.Maze;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Creates a Ghost character which moves around the maze to catch pacman
 * Implements runnable interface for animation
 */
public class Ghost extends Rectangle implements Runnable {

    private String direction;
    private GameManager gameManager;
    private Maze maze;
    private AnimationTimer animation;
    private int timesWalked;

    /**
     * Loads an image of ghost as the icon
     * @param x x-coordinate of ghost on the board
     * @param y y-coordinate of ghost on the board
     * @param img Ghost image for icon
     * @param maze Game maze instance
     * @param gameManager Game instance
     */
    public Ghost(double x, double y, Image img, Maze maze, GameManager gameManager) {
        this.getX();
        this.getY();
        this.setX(x);
        this.setY(y);
        this.maze = maze;
        this.gameManager = gameManager;
        this.setHeight(50);
        this.setWidth(50);
        this.setFill(new ImagePattern(img));
        this.timesWalked = 0;
        this.direction = "down";
        this.createAnimation();
    }

    /**
     * Gets a random direction which the ghost should start moving in
     * @param exclude1 Doesn't allow the ghost to move in this direction
     * @param exclude2 Doesn't allow the ghost to move in this direction
     * @return Random direction the ghost should move
     */
    private String getRandomDirection(String exclude1, String exclude2) {
        String[] directions = {"left", "right", "up", "down"};
        int rnd = new Random().nextInt(directions.length);
        while (directions[rnd].equals(exclude1) || directions[rnd].equals(exclude2)) {
            rnd = new Random().nextInt(directions.length);
        }
        return directions[rnd];
    }

    /**
     * Gets a random boolean
     * @return Random boolean
     */
    private boolean getRandomBoolean() {
        Random rand = new Random();
        return rand.nextBoolean();
    }

    /**
     * Gets the animation for the ghost
     * @return animation
     */
    public AnimationTimer getAnimation() {
        return animation;
    }

    /**
     * Checks for obstacles/wall in the ghosts path. If there is, ghost changes direction
     * @param direction Direction to check for obstacles in
     */
    private void checkIftheresPathToGo(String direction) {
        double rightEdge, leftEdge, topEdge, bottomEdge;
        switch (direction) {
            case "down":
                leftEdge = getX() - 10;
                bottomEdge = getY() + getHeight() + 15;
                rightEdge = getX() + getWidth() + 10;
                if (!maze.hasObstacle(leftEdge, rightEdge, bottomEdge - 1, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "up":
                leftEdge = getX() - 10;
                rightEdge = getX() + getWidth() + 10;
                topEdge = getY() - 15;
                if (!maze.hasObstacle(leftEdge, rightEdge, topEdge - 1, topEdge)) {
                    this.direction = direction;
                }
                break;
            case "left":
                leftEdge = getX() - 15;
                bottomEdge = getY() + getHeight() + 10;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(leftEdge - 1, leftEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "right":
                bottomEdge = getY() + getHeight() + 10;
                rightEdge = getX() + getWidth() + 15;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(rightEdge - 1, rightEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
        }
        if(this.getX() < 0.0){
            this.setX(1250);

        }
        if(this.getX() > 1300){
            this.setX(50);

        }

    }

    /**
     * Moves the ghost in the same direction until it touches an obstacle/wall
     *
     * @param whereToGo Direction of arrow key pressed by user
     * @param whereToChangeTo Which direction to change to after it touches a wall
     * @param leftEdge Left edge of the ghost
     * @param topEdge Top edge of the ghost
     * @param rightEdge Right edge of the ghost
     * @param bottomEdge Bottom edge of the ghost
     * @param padding Offset added on to ghost edges when it hits a wall
     */
    private void moveUntilYouCant(String whereToGo, String whereToChangeTo, double leftEdge, double topEdge, double rightEdge, double bottomEdge, double padding) {
        double step = 5;

        if(this.getX() < 0.0){
            this.setX(1250);

        }
        if(this.getX() > 1300){
            this.setX(50);

        }
        switch (whereToGo) {
            case "left":
                this.setScaleX(-1);
                if (!maze.isTouching(leftEdge, topEdge, padding)) {
                    setX(leftEdge - step);
                } else {
                    while (maze.isTouching(getX(), getY(), padding)) {
                        setX(getX() + 1);
                    }
                    direction = whereToChangeTo;
                }
                break;
            case "right":
                this.setScaleX(1);
                if (!maze.isTouching(rightEdge, topEdge, padding)) {
                    setX(leftEdge + step);
                } else {
                    while (maze.isTouching(getX() + getWidth(), getY(), padding)) {
                        setX(getX() - 1);
                    }
                    direction = whereToChangeTo;
                }
                break;
            case "up":
                if (!maze.isTouching(leftEdge, topEdge, padding)) {
                    setY(topEdge - step);
                } else {
                    while (maze.isTouching(getX(), getY(), padding)) {
                        setY(getY() + 1);
                    }
                    direction = "left";
                }
                break;
            case "down":
                if (!maze.isTouching(leftEdge, bottomEdge, padding)) {
                    setY(topEdge + step);
                } else {
                    while (maze.isTouching(getX(), getY() + getHeight(), padding)) {
                        setY(getY() - 1);
                    }
                    direction = "right";
                }
                break;
        }

    }

    /**
     * Creates an animation of the ghost
     */
    public void createAnimation() {

        this.animation = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                Coalition coalition = new Coalition(gameManager, gameManager.getPacman(), gameManager.getCookieSet(), gameManager.getGhosts());
                coalition.checkGhostCoalition();

                double leftEdge = getX();
                double topEdge = getY();
                double rightEdge = getX() + getWidth();
                double bottomEdge = getY() + getHeight();
                double padding = 12;
                timesWalked++;

                switch (direction) {
                    case "left":
                        moveUntilYouCant("left", "down", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        timesWalkedlr();
                        break;
                    case "right":
                        moveUntilYouCant("right", "up", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        timesWalkedlr();
                        break;
                    case "up":
                        moveUntilYouCant("up", "left", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        timesWalkedud();
                        break;
                    case "down":
                        moveUntilYouCant("down", "right", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        timesWalkedud();
                        break;
                }
            }
        };
    }

    /**
     * Gets the number of times the ghost has walked either up or down
     * Refactored from createAnimation class to stop repeating code
     */
    private void timesWalkedud(){
        int walkAtLeast = 4;

        if (timesWalked > walkAtLeast) {
            checkIftheresPathToGo(getRandomDirection("up", "down"));
            timesWalked = 0;
        }
    }

    /**
     * Gets the number of times the ghost has walked either left or right
     * Refactored from createAnimation class to stop repeating code
     */
    private void timesWalkedlr(){
        int walkAtLeast = 4;

        if (timesWalked > walkAtLeast) {
            checkIftheresPathToGo(getRandomDirection("left", "right"));
            timesWalked = 0;
        }
    }

    /**
     * Starts the ghost moving
     */
    @Override
    public void run() {
        this.animation.start();
    }
}

