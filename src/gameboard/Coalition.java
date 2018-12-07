package gameboard;

import characters.Cookie;
import characters.Pacman;

import java.util.Set;

public class Coalition {

    GameManager game;
//    int score = game.getScore();
    Pacman pacman;
    Set<Cookie> cookieSet;
    Set<Ghost> ghosts;
//    int cookiesEaten = game.getCookiesEaten();
    Score scoreBoard;



    /**
     * Constructor
     *
     * @param
     */
    Coalition(GameManager game, Pacman pacman, Set<Cookie> cookieSet, Set<Ghost> ghosts, Score scoreBoard) {

        this.game = game;
        this.pacman = pacman;
        this.cookieSet = cookieSet;
        this.ghosts = ghosts;
        this.scoreBoard = scoreBoard;
    }




    /**
     * Checks if the Pacman touches cookies.
     * @param pacman
     * @param axis
     */
    public void checkCookieCoalition(Pacman pacman, String axis) throws Exception {
        double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();


        for (Cookie cookie:game.getCookieSet()) {
            double cookieCenterX = cookie.getCenterX();
            double cookieCenterY = cookie.getCenterY();
            double cookieLeftEdge = cookieCenterX - cookie.getRadius();
            double cookieRightEdge = cookieCenterX + cookie.getRadius();
            double cookieTopEdge = cookieCenterY - cookie.getRadius();
            double cookieBottomEdge = cookieCenterY + cookie.getRadius();
            if (axis.equals("x")) {
                // pacman goes right

                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanRightEdge >= cookieLeftEdge && pacmanRightEdge <= cookieRightEdge)) {
                    cookieEaten(cookie);
                }
                // pacman goes left
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanLeftEdge >= cookieLeftEdge && pacmanLeftEdge <= cookieRightEdge)) {
                    cookieEaten(cookie);
                }
            } else {
                // pacman goes up
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanBottomEdge >= cookieTopEdge && pacmanBottomEdge <= cookieBottomEdge)) {
                    cookieEaten(cookie);
                }
                // pacman goes down
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanTopEdge <= cookieBottomEdge && pacmanTopEdge >= cookieTopEdge)) {
                    cookieEaten(cookie);
                }
            }
            scoreBoard.score.setText("Score: " + game.getScore());
            if (game.getCookiesEaten() == cookieSet.size()) {
                this.game.getClass().getDeclaredMethod("endGame");
            }
        }
    }

    public void cookieEaten(Cookie cookie){
        if (cookie.isVisible()) {
            int score = game.getScore();
            score += cookie.getValue();
            int eat = game.getCookiesEaten();
                    eat++;
        }
        cookie.hide();
    }
    /**
     * Checks if pacman is touching a ghost
     */
    public void checkGhostCoalition() throws Exception {
        double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();
        for (Ghost ghost : ghosts) {
            double ghostLeftEdge = ghost.getX();
            double ghostRightEdge = ghost.getX() + ghost.getWidth();
            double ghostTopEdge = ghost.getY();
            double ghostBottomEdge = ghost.getY() + ghost.getHeight();
            if ((pacmanLeftEdge <= ghostRightEdge && pacmanLeftEdge >= ghostLeftEdge) || (pacmanRightEdge >= ghostLeftEdge && pacmanRightEdge <= ghostRightEdge)) {
                if ((pacmanTopEdge <= ghostBottomEdge && pacmanTopEdge >= ghostTopEdge) || (pacmanBottomEdge >= ghostTopEdge && pacmanBottomEdge <= ghostBottomEdge)) {
                    this.game.getClass().getDeclaredMethod("lifeLost");
                }
            }
        }
    }

}
