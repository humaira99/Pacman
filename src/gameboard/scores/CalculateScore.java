package gameboard.scores;

import gameboard.GameManager;

import java.util.*;

import static gameboard.GameManager.scboard;

/**
 * Calculates the score to be displayed on the score pop up after the game has ended
 */
public class CalculateScore {
    static int score1;
    static int score2;
    static int score3;
    public static int total = 0;

    public static HashMap<String, Integer> scboard2 = new HashMap<>();
    public static ArrayList<String> order = new ArrayList<>();
    public static ArrayList<Integer> sc = new ArrayList<>();
    public static Map<String, Integer> sortedMap = new LinkedHashMap<>();

    /**
     * Calculates scores for each round
     * Sorts through the hashmap in order to display score and round number from highest to lowest in the score pop up
     *
     */

    public static void calculateScore() {
        if(GameManager.lifes == 3){
            score1 = scboard.get(0);
            score2 = 0;
            score3 = 0;
        }
        else if(GameManager.lifes == 2){
            score1 = scboard.get(0);
            score2 = scboard.get(1) - score1;
            score3 = 0;
        }
        else {
            score1 = scboard.get(0);
            score2 = scboard.get(1) - score1;
            score3 = scboard.get(2) - (score1 + score2);
        }

        scboard2.put("Round 1", score1);
        scboard2.put("Round 2", score2);
        scboard2.put("Round 3", score3);

        List<Map.Entry<String, Integer>> list = new LinkedList<>(scboard2.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        int i = 0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            order.add(entry.getKey());
            sc.add(entry.getValue());
            total += sc.get(i);
            i++;

        }


    }
}