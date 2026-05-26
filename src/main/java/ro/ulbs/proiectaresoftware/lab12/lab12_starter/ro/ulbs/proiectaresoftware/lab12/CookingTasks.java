package ro.ulbs.proiectaresoftware.lab12.lab12_starter.ro.ulbs.proiectaresoftware.lab12;

import java.util.List;

public class CookingTasks extends Thread {

    private final List<String> tasks;

    // Add constructor to receive the list of tasks
    public CookingTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        Restaurant.getRestaurant().washHands();

        // Execute the tasks
        for (String task : tasks) {
            System.out.println(Thread.currentThread().getName() + " - " + task);
        }

        Restaurant.getRestaurant().washDishes();
    }
}