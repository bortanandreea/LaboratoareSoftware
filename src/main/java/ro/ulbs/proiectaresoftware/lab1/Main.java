package ro.ulbs.proiectaresoftware.lab1;

public class Main {

    public static void main(String[] args) {

        String alfabet = "";

        for(char c = 'a'; c <= 'z'; c++) {
            alfabet = alfabet + c;
        }

        System.out.println(alfabet.toLowerCase());
        System.out.println(alfabet.toUpperCase());

    }
}