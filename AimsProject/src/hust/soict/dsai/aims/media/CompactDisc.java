package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist) {
        super(id, title, category, length, director, cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("track already exists.");
        } else {
            tracks.add(track);
            System.out.println("track added successfully.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("track removed successfully.");
        } else {
            System.out.println("track not found.");
        }
    }

    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) totalLength += track.getLength();
        return totalLength;
    }

    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
        for(Track track : tracks) track.play();
    }

    @Override
    public String toString() {
        return "CD  - " + getTitle() + " - " + getCategory() + " - " + getCost() + " - " + artist + " - " + getLength() + ": " + getCost() + " $";
    }
}
