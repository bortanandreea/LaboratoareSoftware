package ro.ulbs.proiectaresoftware.lab3;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Application {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("in.txt");

        List<String> resultA = new ArrayList<>();
        List<String> resultB = new ArrayList<>();

        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                resultA.add(line + "\n");

                String[] parts = line.split("\\.");
                StringBuilder sb = new StringBuilder();

                for (String p : parts) {
                    if (!p.trim().isEmpty()) {
                        sb.append(p.trim()).append(".\n");
                    }
                }

                resultB.add(sb.toString());
            }
        }

        System.out.println("=== a) ===");
        for (String s : resultA) {
            System.out.print(s);
        }

        System.out.println("\n=== b) ===");
        for (String s : resultB) {
            System.out.print(s);
        }

        List<String> finalOutput = new ArrayList<>();
        finalOutput.addAll(resultA);
        finalOutput.addAll(resultB);

        Files.write(Paths.get("out.txt"), finalOutput);
    }
}