package gameboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ScoreFile {

    public GameManager game;
    public static ArrayList<Integer> highscoreList = new ArrayList<>();

    public ScoreFile(GameManager game) {
        this.game = game;
    }

    public void writeToFile() {
        try {
            File highscore = new File("src/gameboard/highscore.txt");
            FileWriter fileWriter = new FileWriter(highscore, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(game.getScore() + "\n");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {

        try {

            Scanner scanner = new Scanner(new File("src/gameboard/highscore.txt"));

            while (scanner.hasNextLine()) {
                highscoreList.add(Integer.parseInt(scanner.nextLine()));
            }

            Collections.sort(highscoreList);
            Collections.reverse(highscoreList);

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}