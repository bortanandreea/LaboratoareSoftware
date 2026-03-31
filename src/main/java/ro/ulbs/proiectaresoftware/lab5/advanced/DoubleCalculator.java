package ro.ulbs.proiectaresoftware.lab5.advanced;

public class DoubleCalculator extends ACalculator {

    public DoubleCalculator(double value) {
        this.state = value;
    }

    @Override
    protected void init() {
        this.state = 0.0;
    }

    public DoubleCalculator add(double value) {
        this.state = (Double) state + value;
        return this;
    }

    public DoubleCalculator subtract(double value) {
        this.state = (Double) state - value;
        return this;
    }

    public DoubleCalculator multiply(double value) {
        this.state = (Double) state * value;
        return this;
    }
}