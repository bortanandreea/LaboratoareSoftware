package ro.ulbs.proiectaresoftware.lab3;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void ex(String inFile,String outFile) throws IOException {
        Path inPath= Paths.get(inFile);
        String lines= Files.readString(inPath);
        String[] parts = lines.split("\s+");
        StringBuilder aResult = new StringBuilder();
        for (String part : parts) {
            aResult.append(part).append('\n');
        }
        System.out.println(aResult);
        StringBuilder bResult = new StringBuilder();
        for (String part : parts) {
            for(int i=0;i<part.length();i++)
            {
                char ch= lines.charAt(i);
                bResult.append(ch);
                if(ch=='.')
                {
                    bResult.append('\n');
                }
            }
            bResult.append('\n');
        }
        System.out.println(bResult);
        String output = aResult.toString() + "\n\n" + bResult.toString();
        Files.writeString(Paths.get(outFile), output);
    }
    public static void main(String[] args) {
        try {
            ex("src/lab3/in.txt","src/lab3/out.txt");
            System.out.println("\n\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}