package gameboard;

import java.util.ArrayList;
import java.util.Collections;

import static gameboard.GameManager.scboard;

public class CalculateScore {
    public static int gameScore;
    static int score1;
    static int score2;
    static int score3;

    public static ArrayList<Integer> scboard2 = new ArrayList<>();

    public static void calculateScore(){
        score1 = scboard.get(0);
        score2 = scboard.get(1) - score1;
        score3 = scboard.get(2) - (score1 + score2);

        System.out.println(score1);
        System.out.println(score2);
        System.out.println(score3);

        scboard2.add(score1);
        scboard2.add(score2);
        scboard2.add(score3);
        Collections.sort(scboard2);

        score1 = scboard2.get(0);
        score2 = scboard2.get(1);
        score3 = scboard2.get(2);
    }
}
