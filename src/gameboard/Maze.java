package gameboard;



import javafx.scene.Group;

import java.util.HashSet;
import java.util.Set;

/**
 * Maze of the game board
 * Implements singleton design pattern so only one instance of Maze is ever created
 */
public class Maze {

    private static Maze maze;

    public Set<BarObstacle> obstacles;

    /**
     * Creates a new maze
     */
    Maze() {
        obstacles = new HashSet<>();
    }

    public static Maze getInstance(){
        if(maze == null){
            maze = new Maze();
        }
        return maze;

    }

    /**
     * Checks if point is touching obstacles
     * @param x x-coordinate to check
     * @param y y-coordinate to check
     * @param padding Offset to add on when checking if an object is touching the maze
     * @return true or false if the object is touching the maze
     */
    public Boolean isTouching(double x, double y, double padding) {
        for (BarObstacle barObstacle:obstacles) {
            if (
                    x >= barObstacle.getX() - padding &&
                            x <= barObstacle.getX() + padding + barObstacle.getWidth() &&
                            y >= barObstacle.getY() - padding &&
                            y <= barObstacle.getY() + padding + barObstacle.getHeight())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * lets you know if there's an obstacle in the current coordinate
     * @param fromX x-coordinate start
     * @param toX x-coordinate end
     * @param fromY y-coordinate start
     * @param toY y-coordinate end
     * @return true/false if the object is touching the maze
     */
    public Boolean hasObstacle(double fromX,  double toX, double fromY, double toY) {
        boolean isTouching = false;
        for (double i = fromX; i < toX; i++) {
            for (double j = fromY; j < toY; j++) {
                if (this.isTouching(i, j, 0)) isTouching = true;
            }
        }
        return isTouching;
    }

    /**
     * Draws the maze
     * @param root Group where the obstacles are placed
     */
    public void CreateMaze(Group root) {
        //~~~~~~~~~~~~~~~~~~~~~~~~~ frame ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // top
        this.obstacles.add(new BarObstacle(0, 0, "horizontal", 48));
        // bottom
        this.obstacles.add(new BarObstacle(0, 600, "horizontal", 48));
        // left top
        this.obstacles.add(new BarObstacle(0, 0, "vertical", 11));
        //left bottom
        this.obstacles.add(new BarObstacle(0, 350, "vertical", 10));
        // right top
        this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 0, "vertical", 11));
        // right bottom
        this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 350, "vertical", 11));

        //~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // obsTopLeft
        this.obstacles.add(new BarObstacle(12 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, "vertical", 4));
        // obsTopRight
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, "vertical", 4));
        // obsBottomLeft
        this.obstacles.add(new BarObstacle(12 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "vertical", 4));
        // obsBottomRight
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "vertical", 4));
        // obsTopMiddle
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 17));
        // obsBottomMiddle
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 17));
        // obsLMTop
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsLMTop4
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsLMBottom
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsRMTop
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsRMTop2
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsRMBottom
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsLeftTop1
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsLeftTop2
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, "vertical", 6));
        // LobsLeftBottom1
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsLeftBottom2
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, "vertical", 6));
        // LobsRightTop1
        this.obstacles.add(new BarObstacle(40 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsRightTop2
        this.obstacles.add(new BarObstacle(44 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, "vertical", 6));
        // LobsRightBottom1
        this.obstacles.add(new BarObstacle(40 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsRightBottom2
        this.obstacles.add(new BarObstacle(44 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, "vertical", 6));
        // cageBottom
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 8 * BarObstacle.THICKNESS, "horizontal", 17));
        // cageRightV
        this.obstacles.add(new BarObstacle(32 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, "vertical", 8));
        // cageLeftV
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, "vertical", 8));
        // cateRightH
        this.obstacles.add(new BarObstacle(17 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
        // cateLeftH
        this.obstacles.add(new BarObstacle(27 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));

        root.getChildren().addAll(obstacles);
    }
}
