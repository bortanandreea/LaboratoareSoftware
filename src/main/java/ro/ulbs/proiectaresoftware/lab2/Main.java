package ro.ulbs.proiectaresoftware.lab2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    List<Integer> x = new ArrayList<>();
    List<Integer> y = new ArrayList<>();
    List<Integer> xPlusY = new ArrayList<>(); //a
    Set<Integer> zSet = new TreeSet<>(); //b
    List<Integer> xMinusY = new ArrayList<>();//c
    int p = 4;
    List<Integer> xPlusYLimitedByP = new ArrayList<>(); //d

    public void solve() {
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            x.add(rand.nextInt(11));
        }

        for (int i = 0; i < 7; i++) {
            y.add(rand.nextInt(11));
        }

        Collections.sort(x);
        Collections.sort(y);

        xPlusY.addAll(x);
        xPlusY.addAll(y);
        Collections.sort(xPlusY);

        for (Integer val : x) {
            if (y.contains(val)) {
                zSet.add(val);
            }
        }

        for (Integer val : x) {
            if (!y.contains(val)) {
                xMinusY.add(val);
            }
        }

        for (Integer val : x) {
            if (val <= p) {
                xPlusYLimitedByP.add(val);
            }
        }
        for (Integer val : y) {
            if (val <= p) {
                xPlusYLimitedByP.add(val);
            }
        }
        Collections.sort(xPlusYLimitedByP);

        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("a) xPlusY: " + xPlusY);
        System.out.println("b) zSet: " + zSet);
        System.out.println("c) xMinusY: " + xMinusY);
        System.out.println("d) xPlusYLimitedByP: " + xPlusYLimitedByP);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}