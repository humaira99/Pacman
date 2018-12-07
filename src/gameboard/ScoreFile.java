package gameboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ScoreFile {

    public GameManager game;

    public ScoreFile(GameManager game){
        this.game = game;
    }

   public void writeToFile(){
        try {
            File highscore = new File("src/gameboard/highscore.txt");
            FileWriter fileWriter = new FileWriter(highscore, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(game.getScore() +"\n");

            writer.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
