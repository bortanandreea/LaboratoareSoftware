package ro.ulbs.proiectaresoftware.lab12.lab12_starter.ro.ulbs.proiectaresoftware.lab12;

public class Restaurant {

    private static final Restaurant restaurant;
    private long usedWater; //ml

    static {
        restaurant = new Restaurant();
    }

    private Restaurant() {
        this.usedWater = 0;
    }

    public static Restaurant getRestaurant() {
        return restaurant;
    }

    // Synchronize to prevent race condition
    public synchronized void washHands() {
        for (int i = 0; i < 1000; i++) {
            this.usedWater++;
        }
    }

    // Synchronize to prevent race condition
    public void washDishes() {
        synchronized (this) {
            for (int i = 0; i < 1000; i++) {
                this.usedWater++;
            }
        }
    }

    // Synchronize for visibility guarantees
    public long getUsedWater() {
        synchronized (this) {
            return usedWater;
        }
    }
}