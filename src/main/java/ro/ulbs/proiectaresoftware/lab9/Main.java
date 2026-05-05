package ro.ulbs.proiectaresoftware.lab9;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> lista = IntStream.range(0, 10)
                .map(i -> 5 + random.nextInt(21)) // [5..25]
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Lista: " + lista);
        int suma = lista.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Suma: " + suma);
        int max = lista.stream()
                .max(Integer::compare)
                .get();

        int min = lista.stream()
                .min(Integer::compare)
                .get();

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

        List<Integer> filtrata = lista.stream()
                .filter(n -> n >= 10 && n <= 20)
                .collect(Collectors.toList());

        System.out.println("Filtrata [10..20]: " + filtrata);

        List<Double> listaDouble = lista.stream()
                .map(n -> n.doubleValue())
                .collect(Collectors.toList());

        System.out.println("Lista Double: " + listaDouble);

        boolean exista12 = lista.stream()
                .anyMatch(n -> n == 12);

        System.out.println("Contine 12? " + exista12);

        //problema 9.3.2

        String text= "Acesta este un program scris in java pentru expresii lambda";

        List<String> cuvinte = Arrays.asList(text.split(" "));
        System.out.println("Lista cuvinte: " + cuvinte);

        List<String> filtrate = cuvinte.stream()
                .filter(cuv -> cuv.length() >= 5)
                .collect(Collectors.toList());

        System.out.println("cuvinte >=5: " + filtrate);
        System.out.println("numar: " + filtrate.size());

        List<String> sortate= filtrate.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("sortare"+ sortate);


        Optional <
        Optional<String> cuvant = cuvinte.stream()
                .filter(c -> c.startsWith("p"))
                .findFirst();

        if (cuvant.isPresent()) {
            System.out.println("Cuvant cu p: " + cuvant.get());
        } else {
            System.out.println("Nu exista cuvant cu p");
        }
    }
}


