package ro.ulbs.proiectaresoftware.lab9;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> numbers = IntStream.range(0,10)
                .map(i ->5 + random.nextInt(21))
                .boxed()
                .collect(Collectors.toList());

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Lista: " + numbers);
        System.out.println("Suma: " + sum);

        int min = numbers.stream().mapToInt(Integer::intValue).min().orElse(0);
        int max = numbers.stream().mapToInt(Integer::intValue).max().orElse(0);
        System.out.println("Min: " + min + ", Max: " + max);

        List<Integer> filtered = numbers.stream()
                .filter(n -> n >=10 && n <=20)
                .collect(Collectors.toList());
        System.out.println("Interval [10..20]: " + filtered);

        List<Double> asDoubles = numbers.stream()
                .map(Integer::doubleValue)
                .collect(Collectors.toList());
        System.out.println("Lista Double: " + asDoubles);

        boolean contains12 = numbers.stream().anyMatch(n -> n ==12);
        System.out.println("Contine12: " + contains12);
    }
}

