package gameboard;


import characters.Cookie;
import characters.Ghost;
import characters.Pacman;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameManager {

    private Pacman pacman;
    private Group root;
    private Set<Cookie> cookieSet;
    private Set<Ghost> ghosts;
    private AnimationTimer leftPacmanAnimation;
    private AnimationTimer rightPacmanAnimation;
    private AnimationTimer upPacmanAnimation;
    private AnimationTimer downPacmanAnimation;
    private Maze maze;
    private int lifes;
    private int score;
    private Score scoreBoard;
    private boolean gameEnded;
    private int cookiesEaten;

    public void setScore(int score) {
        this.score = score;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setCookiesEaten(int cookiesEaten) {
        this.cookiesEaten = cookiesEaten;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Set<Cookie> getCookieSet() {
        return cookieSet;
    }

    public Set<Ghost> getGhosts() {
        return ghosts;
    }

    public int getScore() {
        return score;
    }

    public Score getScoreBoard() {
        return scoreBoard;
    }

    public int getCookiesEaten() {
        return cookiesEaten;
    }

    public static ArrayList<Integer> scboard = new ArrayList<>();

    /**
     * Constructor
     */
    GameManager(Group root) {
        this.root = root;
        this.maze = new Maze();
        this.pacman = new Pacman(this, 2.5 * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
        this.cookieSet = new HashSet<>();
        this.ghosts = new HashSet<>();
        this.leftPacmanAnimation = pacman.createAnimation("left");
        this.rightPacmanAnimation = pacman.createAnimation("right");
        this.upPacmanAnimation = pacman.createAnimation("up");
        this.downPacmanAnimation = pacman.createAnimation("down");
        this.lifes = 3;
        this.score = 0;
        this.cookiesEaten = 0;
        this.scoreBoard = scoreBoard;

    }

    /**
     * Set one life less
     */
    void lifeLost() {
        this.leftPacmanAnimation.stop();
        this.rightPacmanAnimation.stop();
        this.upPacmanAnimation.stop();
        this.downPacmanAnimation.stop();
        for (Ghost ghost : ghosts) {
            ghost.getAnimation().stop();
        }
        this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
        this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
        lifes--;
        scboard.add(score);
        score -= 10;

        this.scoreBoard.lifes.setText("Lifes: " + this.lifes);
        this.scoreBoard.score.setText("Score: " + this.score);
        if (lifes == 0) {
            this.endGame();
        }
    }

    /**
     * Ends the game
     */
    void endGame() {
        this.gameEnded = true;
        root.getChildren().remove(pacman);
        for (Ghost ghost : ghosts) {
            root.getChildren().remove(ghost);
        }
        javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
        ScoreFile scoreFile = new ScoreFile(this);
        scoreFile.writeToFile();
        scoreFile.readFromFile();
        ShowScores showScores = new ShowScores();
        showScores.ShowScores();
        endGame.setX(BarObstacle.THICKNESS * 3);
        endGame.setY(BarObstacle.THICKNESS * 28);
        endGame.setFont(Font.font("Arial", 40));
        endGame.setFill(Color.ROYALBLUE);
        root.getChildren().remove(this.scoreBoard.score);
        root.getChildren().remove(this.scoreBoard.lifes);
        root.getChildren().add(endGame);
    }

    /**
     * Restart the game
     * @param event
     */
    public void restartGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
            root.getChildren().clear();
            this.cookieSet.clear();
            this.ghosts.clear();
            this.drawBoard();
            this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
            this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
            this.lifes = 3;
            this.score = 0;
            this.cookiesEaten = 0;

            scboard.clear();
            CalculateScore.scboard2.clear();
            CalculateScore.order.clear();
            CalculateScore.sc.clear();
            CalculateScore.sortedMap.clear();

            gameEnded = false;
        }
    }

    /**
     * Draws the board of the game with the cookies and the Pacman
     */
    public void drawRow(Integer skip[], int row){
        for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, (2.5 + 2 * row) * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
    }

    public void drawBoard() {

        this.maze.CreateMaze(root);
        // 1st line
        drawRow(new Integer[]{5, 17}, 0);
        drawRow(new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21}, 1);
        drawRow(new Integer[]{1, 21}, 2);
        drawRow(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 3);
        drawRow(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 4);
        drawRow(new Integer[]{3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19}, 5);
        drawRow(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 6);
        drawRow(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 7);
        drawRow(new Integer[]{1, 21}, 8);
        drawRow(new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21}, 9);
        drawRow(new Integer[]{5, 17}, 10);

        root.getChildren().add(this.pacman);
        this.generateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root);
    }

    /**
     * Generates the ghosts for the pacman!
     */
    public void generateGhosts() {
        Image ghost1 = new Image ("characters/ghost1.png");
        Image ghost2 = new Image ("characters/ghost2.png");
        Image ghost3 = new Image ("characters/ghost3.png");
        Image ghost4 = new Image ("characters/ghost4.png");


        this.ghosts.add(new Ghost(18.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, ghost1, maze, this));
        this.ghosts.add(new Ghost(22.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, ghost2, maze, this));
        this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, ghost3, maze, this));
        this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, ghost4, maze, this));
    }

    /**
     * Moves the pacman
     * @param event
     */
    public void movePacman(KeyEvent event) {
        for (Ghost ghost : this.ghosts) {
            ghost.run();
        }
        switch(event.getCode()) {
            case RIGHT:
                this.rightPacmanAnimation.start();
                break;
            case LEFT:
                this.leftPacmanAnimation.start();
                break;
            case UP:
                this.upPacmanAnimation.start();
                break;
            case DOWN:
                this.downPacmanAnimation.start();
                break;
        }
    }

    /**
     * Stops the pacman
     * @param event
     */
    public void stopPacman(KeyEvent event) {
        switch(event.getCode()) {
            case RIGHT:
                this.rightPacmanAnimation.stop();
                break;
            case LEFT:
                this.leftPacmanAnimation.stop();
                break;
            case UP:
                this.upPacmanAnimation.stop();
                break;
            case DOWN:
                this.downPacmanAnimation.stop();
                break;
        }
    }

}
