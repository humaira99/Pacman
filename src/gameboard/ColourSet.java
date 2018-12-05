package gameboard;

import javafx.scene.paint.Color;

public class ColourSet {
    public static Color cookie;
    public static Color wall;
    public static Color background;

    public void getCookieCol(Color cookiecol){
        cookie = cookiecol;
    }

    public void getWallCol(Color wallcol){
        wall = wallcol;
    }

    public void getBackgroundCol(Color backcol){
        background = backcol;
    }

}
