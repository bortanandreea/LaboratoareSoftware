package ro.ulbs.proiectaresoftware.lab2;

import java.util.*;


    public class Application {

        public static void main(String[] args) {

            List<Integer> x = new ArrayList<>();
            List<Integer> y = new ArrayList<>();
            List<Integer> xPlusY = new ArrayList<>();
            Set<Integer> zSet = new TreeSet<>();
            List<Integer> xMinusY = new ArrayList<>();
            int p = 4;
            List<Integer> xPlusYLimitedByP = new ArrayList<>();

            Random rand = new Random();

            for(int i=0;i<5;i++)
                x.add(rand.nextInt(11));

            for(int i=0;i<7;i++)
                y.add(rand.nextInt(11));

            Collections.sort(x);
            Collections.sort(y);

            System.out.println("x = " + x);
            System.out.println("y = " + y);
        }
    }

