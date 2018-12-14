package gameboard;

import javafx.scene.paint.Color;

/**
 * Gets setup colours from the colour pickers on the set up screen and sets them to variables to be used by different methods to set the colours
 */
public class ColourSet {
    /**
     * Colour of the cookie set to be accessed by <Code>Cookie</Code> to set the colour
     */
    public static Color cookie;
    static Color wall;
    /**
     * Colour of the background set to be accessed by another class to set the colour of the background
     */
    public static Color background;

    /**
     * @param cookiecol Colour of the cookie the player has chosen
     */
    public void getCookieCol(Color cookiecol){
        cookie = cookiecol;
    }

    /**
     * @param wallcol Colour of the walls the player has chosen
     */
    public void getWallCol(Color wallcol){
        wall = wallcol;
    }

    /**
     * @param backcol Colour of the background the player has chosen
     */
    public void getBackgroundCol(Color backcol){
        background = backcol;
    }

}
