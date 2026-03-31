package ro.ulbs.proiectaresoftware.lab5.advanced;

public class Lab5Adv {

    public static void main(String[] args) {

        // Integer
        NewIntCalculator calculator = new NewIntCalculator(10);
        int result = (Integer) calculator
                .add(5)
                .subtract(3)
                .multiply(2)
                .result();

        System.out.println("5.5.2 a) " + result);

        // Double
        DoubleCalculator fCalculator = new DoubleCalculator(10);
        double result2 = (Double) fCalculator
                .add(5)
                .subtract(3.3)
                .multiply(2.2)
                .result();

        System.out.println("5.5.2 b) " + result2);
    }
}