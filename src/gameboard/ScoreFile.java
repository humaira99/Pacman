package gameboard;

import screens.StartController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ScoreFile {

    public GameManager game;
    public static HashMap<String, Integer> highscoreList = new HashMap<>();
    public static Map<String, Integer> sortedMap = new LinkedHashMap<>();
    public static ArrayList<String> name = new ArrayList<>();
    public static ArrayList<Integer> score = new ArrayList<>();


    public ScoreFile(GameManager game) {
        this.game = game;
    }

    public void writeToFile() {
        try {
            File highscore = new File("src/gameboard/highscore.txt");
            FileWriter fileWriter = new FileWriter(highscore, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(StartController.uname + "\n");
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
                highscoreList.put(scanner.nextLine(), Integer.parseInt(scanner.nextLine()));
            }

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

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
