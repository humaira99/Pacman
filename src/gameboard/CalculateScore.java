package gameboard;

import java.util.*;

import static gameboard.GameManager.scboard;

public class CalculateScore {
    static int score1;
    static int score2;
    static int score3;

    public static HashMap<String, Integer> scboard2 = new HashMap<>();
    public static ArrayList<String> order = new ArrayList<>();
    public static ArrayList<Integer> sc = new ArrayList<>();
    public static Map<String, Integer> sortedMap = new LinkedHashMap<>();

    public static void calculateScore() {
        score1 = scboard.get(0);
        score2 = scboard.get(1) - score1 + 10;
        score3 = scboard.get(2) - (score1 + score2) + 10;

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

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            order.add(entry.getKey());
            sc.add(entry.getValue());

        }
        System.out.println(sc.get(0));
        System.out.println(sc.get(1));
        System.out.println(sc.get(2));

    }
}