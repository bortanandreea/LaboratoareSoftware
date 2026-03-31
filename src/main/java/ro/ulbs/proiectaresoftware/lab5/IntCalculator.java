package ro.ulbs.proiectaresoftware.lab5;

public class IntCalculator {

 protected int state;

    public IntCalculator(int state) {
        this.state = state;
    }

    public IntCalculator add(int value) {
        this.state += value;
        return this;
    }

    public IntCalculator subtract(int value) {
        this.state -= value;
        return this;
    }

    public IntCalculator multiply(int value) {
        this.state *= value;
        return this;
    }

    public int result() {
        return state;
    }

    public IntCalculator clear() {
        this.state = 0;
        return this;
    }
}