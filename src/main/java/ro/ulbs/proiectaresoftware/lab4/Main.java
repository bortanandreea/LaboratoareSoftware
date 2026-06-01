package ro.ulbs.proiectaresoftware.lab4;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static class Tanar {
        private  String name;
        private  int age;
        private  String address;

        Tanar(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Tanar{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> varste = new HashMap<>();
        varste.put("Ioan", 21);
        varste.put("Maria", 22);
        varste.put("Victor", 20);
        varste.put("Simina", 20);
        varste.put("Marius", 21);
        varste.put("Mihai", 21);
        varste.put("Daniela", 23);

        Map<String, String> adrese = Map.of(
                "Ioan", "Sibiu",
                "Maria", "Bucuresti",
                "Victor", "Cluj",
                "Simina", "Alba-Iulia",
                "Marius", "Medias",
                "Mihai", "Cisnadie",
                "Daniela", "Sibiu"
        );

        System.out.println("Varste initiale: " + varste);
        varste.put("Vlad", 19);
        varste.put("Iulia", 19);
        System.out.println("Varste dupa adaugare: " + varste);

        HashMap<String, Tanar> tineri = new HashMap<>();
        for (Map.Entry<String, Integer> entry : varste.entrySet()) {
            String prenume = entry.getKey();
            int age = entry.getValue();

            String address = adrese.getOrDefault(prenume, "Necunoscuta");
            tineri.put(prenume, new Tanar(prenume, age, address));
        }

        System.out.println("Tineri: " + tineri);
    }
}