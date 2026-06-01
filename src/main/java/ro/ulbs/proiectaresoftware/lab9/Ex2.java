package ro.ulbs.proiectaresoftware.lab9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ex2 {
    public static void main(String[] args) {
        String text = "Acesta este un program scris in java pentru expresii lambda";
        List<String> words = Arrays.asList(text.split("\s+"));

        List<String> filtered = words.stream()
                .filter(w -> w.length() >=5)
                .collect(Collectors.toList());
        System.out.println("Filtrate: " + filtered);

        List<String> sorted = filtered.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Sortate: " + sorted);

        words.stream()
                .filter(w -> w.startsWith("p"))
                .findFirst()
                .ifPresent(w -> System.out.println("Incepe cu p: " + w));
    }
}