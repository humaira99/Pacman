package gameboard.scores;

import gameboard.GameManager;
import gameboard.controllers.StartController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Writes username and score to a file and reads from that file to generate the highscore list
 */
public class ScoreFile {

    public GameManager game;
    public static HashMap<String, Integer> highscoreList = new HashMap<>();
    public static Map<String, Integer> sortedMap = new LinkedHashMap<>();
    public static ArrayList<String> name = new ArrayList<>();
    public static ArrayList<Integer> score = new ArrayList<>();

    /**
     * Constructor
     *
     * @param game The game instance
     */
    public ScoreFile(GameManager game) {
        this.game = game;
    }

    /**
     * Writes the username that the player inputted on the start screen with their score to the highscore text file
     */
    public void writeToFileEasy() {
        try {
            File highscoreeasy = new File("src/gameboard/scores/highscoreEasy.txt");

            FileWriter fileWriter = new FileWriter(highscoreeasy, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(StartController.uname + "\n");
            writer.write(game.getScore() + "\n");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the username that the player inputted on the start screen with their score to the highscore text file
     */
    public void writeToFileInt() {
        try {

            File highscoreInt = new File("src/gameboard/scores/highscoreInt.txt");

            FileWriter fileWriter = new FileWriter(highscoreInt, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(StartController.uname + "\n");
            writer.write(game.getScore() + "\n");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the username that the player inputted on the start screen with their score to the highscore text file
     */
    public void writeToFileHard() {
        try {

            File highscoreHard = new File("src/gameboard/scores/highscoreHard.txt");

            FileWriter fileWriter = new FileWriter(highscoreHard, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(StartController.uname + "\n");
            writer.write(game.getScore() + "\n");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the username and score from the file and adds the values to HashMap
     * Sorts through the hashmap and orders the scores to find the top 10 scores and their usernames
     * Adds top 10 in to another list to be printed by the highscore pop up screen
     */
    public void readFromFileEasy() {

        try {

            Scanner scanner = new Scanner(new File("src/gameboard/scores/highscoreEasy.txt"));

            while (scanner.hasNextLine()) {
                highscoreList.put(scanner.nextLine(), Integer.parseInt(scanner.nextLine()));
            }

            sortList();

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readFromFileInt() {
        try {

            Scanner scanner = new Scanner(new File("src/gameboard/scores/highscoreInt.txt"));

            while (scanner.hasNextLine()) {
                highscoreList.put(scanner.nextLine(), Integer.parseInt(scanner.nextLine()));
            }

            sortList();

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFileHard() {
        try {

            Scanner scanner = new Scanner(new File("src/gameboard/scores/highscoreHard.txt"));

            while (scanner.hasNextLine()) {
                highscoreList.put(scanner.nextLine(), Integer.parseInt(scanner.nextLine()));
            }

            sortList();

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sortList(){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(highscoreList.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Collections.reverse(list);

        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            name.add(entry.getKey());
            score.add(entry.getValue());
        }
    }

}
