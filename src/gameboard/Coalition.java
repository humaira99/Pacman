package gameboard;

import characters.Cherry;
import characters.Cookie;
import characters.Ghost;
import characters.Pacman;

import java.util.Set;

/**
 * Checks if Pacman is touching a cookie or a ghost
 */
public class Coalition {

    GameManager game;
    Pacman pacman;
    Set<Cookie> cookieSet;
    Set<Ghost> ghosts;

    /**
     * Constructor
     *
     * @param game GameManager instance
     * @param pacman gets the pacman to check its coalition
     * @param cookieSet gets the set of cookies to check if pacman is touching any
     * @param ghosts gets the set of ghosts to check if pacman is touching any
     *
     */
    public Coalition(GameManager game, Pacman pacman, Set<Cookie> cookieSet, Set<Ghost> ghosts) {

        this.game = game;
        this.pacman = pacman;
        this.cookieSet = cookieSet;
        this.ghosts = ghosts;
    }


    /**
     * Checks if the Pacman touches cookies.
     * @param pacman pacman object
     * @param axis x or y axis to check which way pacman is going
     */
    public void checkCookieCoalition(Pacman pacman, String axis) {
        double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();

        for (Cookie cookie: game.getCookieSet()) {
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
            game.getScoreBoard().getScore().setText("Score: " + this.game.getScore());
            if (game.getCookiesEaten() == cookieSet.size() && game.getCherriesEaten() == game.getCherrySet().size()) {
                this.game.endGame();
            }
        }
    }

    /**
     * Hides the cookie when pacman touches it and increases score
     * Refactored from checkCookieCoalition class so code is not repeated
     * @param cookie gets the specific cookie the pacman touches
     */
    public void cookieEaten(Cookie cookie){
        if (cookie.isVisible()) {
            int score = game.getScore();
            game.setScore(score + cookie.getValue());
            game.setCookiesEaten(game.getCookiesEaten() + 1);

        }
        cookie.hide();
    }

    /**
     * Checks if the Pacman touches a cherry
     * @param pacman pacman object
     * @param axis x or y axis to check which way pacman is going
     */
    public void checkCherryCoalition(Pacman pacman, String axis){
        double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();

        for (Cherry cherry:game.getCherrySet()) {
            double cherryCenterX = cherry.getCenterX();
            double cherryCenterY = cherry.getCenterY();
            double cherryLeftEdge = cherryCenterX - cherry.getRadius();
            double cherryRightEdge = cherryCenterX + cherry.getRadius();
            double cherryTopEdge = cherryCenterY - cherry.getRadius();
            double cherryBottomEdge = cherryCenterY + cherry.getRadius();

            if (axis.equals("x")) {
                // pacman goes right

                if ((cherryCenterY >= pacmanTopEdge && cherryCenterY <= pacmanBottomEdge) && (pacmanRightEdge >= cherryLeftEdge && pacmanRightEdge <= cherryRightEdge)) {
                    cherryEaten(cherry);
                }
                // pacman goes left
                if ((cherryCenterY >= pacmanTopEdge && cherryCenterY <= pacmanBottomEdge) && (pacmanLeftEdge >= cherryLeftEdge && pacmanLeftEdge <= cherryRightEdge)) {
                    cherryEaten(cherry);
                }
            } else {
                // pacman goes up
                if ((cherryCenterX >= pacmanLeftEdge && cherryCenterX <= pacmanRightEdge) && (pacmanBottomEdge >= cherryTopEdge && pacmanBottomEdge <= cherryBottomEdge)) {
                    cherryEaten(cherry);
                }
                // pacman goes down
                if ((cherryCenterX >= pacmanLeftEdge && cherryCenterX <= pacmanRightEdge) && (pacmanTopEdge <= cherryBottomEdge && pacmanTopEdge >= cherryTopEdge)) {
                    cherryEaten(cherry);
                }
            }
            game.getScoreBoard().getScore().setText("Score: " + this.game.getScore());
            if (game.getCookiesEaten() == cookieSet.size() && game.getCherriesEaten() == game.getCherrySet().size()) {
                this.game.endGame();
            }
        }
    }

    /**
     * Hides the cherry when pacman touches it and increases score
     * @param cherry gets the specific cookie the pacman touches
     */
    public void cherryEaten(Cherry cherry){
        if (cherry.isVisible()) {
            int score = game.getScore();
            game.setScore(score + cherry.getValue());
            game.setCherriesEaten(game.getCherriesEaten() + 1);

        }
        cherry.hide();
    }

    /**
     * Checks if pacman is touching a ghost
     * If touching a ghost - lose a life
     */
    public void checkGhostCoalition() {
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
                    this.game.lifeLost();
                }
            }
        }
    }

}
