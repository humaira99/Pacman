package gameboard;

import java.io.*;
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

        /*try{

        //File highscore = new File("src/gameboard/highscore.txt");
        FileReader fileReader = new FileReader ("src/gameboard/highscore.txt");
        BufferedReader reader = new BufferedReader(fileReader);

        String currentLine;

        while((currentLine = reader.readLine()) != null){
            currentLine;
        }

        reader.close();

    }
    catch(Exception e){
        e.printStackTrace();
    }*/
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
