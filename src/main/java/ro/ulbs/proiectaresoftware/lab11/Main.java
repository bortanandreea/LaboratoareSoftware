package ro.ulbs.proiectaresoftware.lab11;
import ro.ulbs.proiectaresoftware.lab11.MediaInterested;
import ro.ulbs.proiectaresoftware.lab11.YouTubeChannel;

public class Main {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("LabChannel");

        MediaInterested obs1 = new MediaInterested("Ana");
        MediaInterested obs2 = new MediaInterested("Ion");

        channel.addObserver(obs1);
        channel.addObserver(obs2);
        channel.uploadVideo("Observer Pattern Intro");

        channel.removeObserver(obs2);
        channel.uploadVideo("Observer Pattern Demo");
    }
}