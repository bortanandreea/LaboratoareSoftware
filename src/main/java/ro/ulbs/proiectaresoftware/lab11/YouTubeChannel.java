package ro.ulbs.proiectaresoftware.lab11;

import java.util.ArrayList;
import java.util.List;

import ro.ulbs.proiectaresoftware.lab11.Observer;
import ro.ulbs.proiectaresoftware.lab11.Subject;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel implements Subject {
    private final String channelName;
    private final List<Observer> observers = new ArrayList<>();

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    public void uploadVideo(String title) {
        System.out.println("{" + channelName + "} uploaded a new video: " + title);
        notifyObservers(title);
    }

    @Override public void addObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override public void notifyObservers(String videoTitle) {
        for (Observer observer : observers) {
            observer.update(channelName, videoTitle);
        }
    }
}