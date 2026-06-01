package ro.ulbs.proiectaresoftware.lab11;

public class MediaInterested implements Observer {
    private final String name;

    public MediaInterested(String name) {
        this.name = name;
    }

    @Override public void update(String channelName, String videoTitle) {
        System.out.println(name + " notified about {" + channelName + "} video: " + videoTitle);
    }
}