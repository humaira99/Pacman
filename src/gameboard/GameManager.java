package gameboard;


import characters.Cherry;
import characters.Cookie;
import characters.Ghost;
import characters.Pacman;
import gameboard.controllers.winController;
import gameboard.scores.CalculateScore;
import gameboard.scores.ScoreFile;
import gameboard.scores.ShowScores;
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

/**
 * Sets up game by drawing the board, generating the ghosts and moving/stopping pacman
 * Checks whether life is lost, game has ended or restarted
 */
public class GameManager {

    private Pacman pacman;
    private Group root;
    private Set<Cookie> cookieSet;
    private Set<Cherry> cherrySet;
    private Set<Ghost> ghosts;
    private AnimationTimer leftPacmanAnimation;
    private AnimationTimer rightPacmanAnimation;
    private AnimationTimer upPacmanAnimation;
    private AnimationTimer downPacmanAnimation;
    private Maze maze;
    public static int lifes;
    private int score;
    private Score scoreBoard;
    private boolean gameEnded;
    private int cookiesEaten;
    private int cherriesEaten;
    public static int level;

    void setScore(int score) {
        this.score = score;
    }

    public Maze getMaze() {
        return maze;
    }

    void setCookiesEaten(int cookiesEaten) {
        this.cookiesEaten = cookiesEaten;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Set<Cookie> getCookieSet() {
        return cookieSet;
    }

    Set<Cherry> getCherrySet() {
        return cherrySet;
    }

    public Set<Ghost> getGhosts() {
        return ghosts;
    }

    public int getScore() {
        return score;
    }

    Score getScoreBoard() {
        return scoreBoard;
    }

    int getCookiesEaten() {
        return cookiesEaten;
    }

    void setCherriesEaten(int cherriesEaten) {
        this.cherriesEaten = cherriesEaten;
    }

    int getCherriesEaten() {
        return cherriesEaten;
    }

    public static ArrayList<Integer> scboard = new ArrayList<>();

    /**
     * Constructor
     */
    public GameManager(Group root) {
        this.root = root;
        this.maze = Maze.getInstance();
        this.pacman = Pacman.getInstance(this, 2.5 * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
        this.cookieSet = new HashSet<>();
        this.cherrySet = new HashSet<>();
        this.ghosts = new HashSet<>();
        this.leftPacmanAnimation = pacman.createAnimation("left");
        this.rightPacmanAnimation = pacman.createAnimation("right");
        this.upPacmanAnimation = pacman.createAnimation("up");
        this.downPacmanAnimation = pacman.createAnimation("down");
        lifes = 3;
        this.score = 0;
        this.cookiesEaten = 0;
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

        if (lifes == 2) {
            this.scoreBoard.imageView3.setOpacity(0);
        }
        if (lifes == 1) {
            this.scoreBoard.imageView2.setOpacity(0);
        }
        this.scoreBoard.score.setText("Score: " + this.score);
        if (lifes == 0) {
            this.scoreBoard.imageView.setOpacity(0);
            this.endGame();
        }
    }

    /**
     * Ends the game
     * If lives are left: you win screen pops up
     * Writes and reads from different text files based on level
     * Prints out score board
     */
    void endGame() {
        this.gameEnded = true;
        root.getChildren().remove(pacman);
        for (Ghost ghost : ghosts) {
            root.getChildren().remove(ghost);
        }
        if(lifes == 3){
            scboard.add(score);
            scboard.add(0);
            scboard.add(0);
        }
        if(lifes == 2){
            scboard.add(score);
            scboard.add(0);
        }
        if(lifes == 1){
            scboard.add(score);
        }

        javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
        ScoreFile scoreFile = ScoreFile.getInstance(this);

        if(level == 1){
            scoreFile.writeToFileEasy();
            scoreFile.readFromFileEasy();
        }
        if(level == 2){
            scoreFile.writeToFileInt();
            scoreFile.readFromFileInt();
        }
        if(level == 3){
            scoreFile.writeToFileHard();
            scoreFile.readFromFileHard();
        }

        ShowScores.getInstance();
        if(lifes >= 1) {
            winController.getInstance();
        }
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
     * Sets all counters/variables/lists to null
     * @param event - if player presses esc on the keyboard, the game should restart
     */
    public void restartGame(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
            root.getChildren().clear();
            this.cookieSet.clear();
            this.cherrySet.clear();
            this.ghosts.clear();
            if(level == 1){
                this.drawBoardEasy();
            }
            if(level == 2){
                this.drawBoardIntermediate();
            }
            if(level == 3){
                this.drawBoardHard();
            }

            ShowScores.show = null;
            ScoreFile.write = 0;
            winController.win = null;
            Pacman.pacman = null;
            this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
            this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
            lifes = 3;
            this.score = 0;
            this.cookiesEaten = 0;
            this.cherriesEaten = 0;

            scboard.clear();
            CalculateScore.scboard2.clear();
            CalculateScore.order.clear();
            CalculateScore.sc.clear();
            CalculateScore.sortedMap.clear();
            CalculateScore.total = 0;
            ScoreFile.sortedMap.clear();
            ScoreFile.highscoreList.clear();
            ScoreFile.name.clear();
            ScoreFile.score.clear();

            gameEnded = false;
        }
    }

    /**
     * Draws one row of the game board
     * @param row Each row of the gameboard which needs drawing
     * @param skip Array of integers where the cookies should not be drawn due to obstacles
     * Refactored from drawBoard method to eliminate repeating code
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

    /**
     * Draws one row of the game board
     * @param row Each row of the gameboard which needs drawing
     * @param skip Array of integers where the cherries should be drawn
     */
    public void drawRowCherry(Integer skip[], int row){
        for (int i = 0; i < 23; i++) {
            if (Arrays.asList(skip).contains(i)) {
                Cherry cherry = new Cherry(((2*i) + 2.5) * BarObstacle.THICKNESS, (2.5 + 2 * row) * BarObstacle.THICKNESS);
                this.cherrySet.add(cherry);
                root.getChildren().add(cherry);
            }
        }
    }

    /**
     * Draws the board of the game with the cookies, cherries and the Pacman for the easy level
     */
    public void drawBoardEasy() {

        this.maze.CreateMazeEasy(root);

        drawRow(new Integer[]{21}, 0);
        drawRow(new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 19, 20, 21}, 1);
        drawRow(new Integer[]{1, 21}, 2);
        drawRow(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 3);
        drawRow(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 21}, 4);
        drawRow(new Integer[]{ 7, 8, 9, 10, 11, 12, 13, 14, 15}, 5);
        drawRow(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 6);
        drawRow(new Integer[]{1, 4, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 7);
        drawRow(new Integer[]{1, 21}, 8);
        drawRow(new Integer[]{1, 2, 3, 7, 8, 9, 10, 11, 12, 13, 14, 15, 19, 20, 21}, 9);
        drawRow(new Integer[]{13}, 10);

        drawRowCherry(new Integer[]{21}, 0);
        drawRowCherry(new Integer[]{5}, 1);
        drawRowCherry(new Integer[]{17}, 4);
        drawRowCherry(new Integer[]{4}, 7);
        drawRowCherry(new Integer[]{13}, 10);


        root.getChildren().add(this.pacman);
        this.generateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root);
    }

    /**
     * Draws the board of the game with the cookies, cherries and the Pacman for the intermediate level
     */
    public void drawBoardIntermediate() {

        this.maze.CreateMazeIntermediate(root);

        drawRow(new Integer[]{5, 17, 22}, 0);
        drawRow(new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21}, 1);
        drawRow(new Integer[]{1, 11, 21}, 2);
        drawRow(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 3);
        drawRow(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 18, 21}, 4);
        drawRow(new Integer[]{3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19}, 5);
        drawRow(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 6);
        drawRow(new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 7);
        drawRow(new Integer[]{1, 21}, 8);
        drawRow(new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21}, 9);
        drawRow(new Integer[]{1, 5, 17, 18}, 10);

        drawRowCherry(new Integer[]{22}, 0);
        drawRowCherry(new Integer[]{11}, 2);
        drawRowCherry(new Integer[]{18}, 4);
        drawRowCherry(new Integer[]{2}, 7);
        drawRowCherry(new Integer[]{1, 18}, 10);

        root.getChildren().add(this.pacman);
        this.generateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root);
    }

    /**
     * Draws the board of the game with the cookies, cherries and the Pacman for the hard level
     */
    public void drawBoardHard() {

        this.maze.CreateMazeHard(root);

        drawRow(new Integer[]{5, 17, 10, 11}, 0);
        drawRow(new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21}, 1);
        drawRow(new Integer[]{1, 11, 21}, 2);
        drawRow(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 3);
        drawRow(new Integer[]{1, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 18, 19, 21}, 4);
        drawRow(new Integer[]{3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19}, 5);
        drawRow(new Integer[]{1, 3, 4, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 21}, 6);
        drawRow(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 7);
        drawRow(new Integer[]{1, 11, 21}, 8);
        drawRow(new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21}, 9);
        drawRow(new Integer[]{5, 11, 17, 22}, 10);

        drawRowCherry(new Integer[]{10}, 0);
        drawRowCherry(new Integer[]{11}, 2);
        drawRowCherry(new Integer[]{18}, 4);
        drawRowCherry(new Integer[]{11}, 5);
        drawRowCherry(new Integer[]{4}, 6);
        drawRowCherry(new Integer[]{11}, 8);
        drawRowCherry(new Integer[]{22}, 10);

        root.getChildren().add(this.pacman);
        this.generateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root);
    }



    /**
     * Generates the ghosts for the pacman!
     * Generates 3 ghosts for easy level, 4 for intermediate and 5 for hard
     */
    private void generateGhosts() {


        Image ghost1 = new Image ("characters/images/ghost1.png");
        Image ghost2 = new Image ("characters/images/ghost2.png");
        Image ghost3 = new Image ("characters/images/ghost3.png");
        Image ghost4 = new Image ("characters/images/ghost4.png");
        Image ghost5 = new Image ("characters/images/ghost5.png");

        this.ghosts.add(new Ghost(18.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, ghost1, maze, this));
        this.ghosts.add(new Ghost(22.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, ghost2, maze, this));
        this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, ghost3, maze, this));

        if(level == 2){
           this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, ghost4, maze, this));
        }
        if(level == 3){
            this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, ghost4, maze, this));
            this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 12.0 * BarObstacle.THICKNESS, ghost5, maze, this));
        }
    }

    /**
     * Moves the pacman
     * @param event Moves in the direction of the arrow key pressed (up/down/left/right)
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
     * @param event When an arrow key is pressed
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
