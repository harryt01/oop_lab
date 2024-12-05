package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    //private String title;
    //private String category;
    //private String director;
    //private int length;
    //private float cost;
    //private int id;

    private static int nbDigitalVideoDiscs = 0;

    /*public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
   
    public String getCategory() {
        return category;
    }
   
    public float getCost() {
        return cost;
    }*/

    public String getDirector() {
        return director;
    }
    
    public int getLength() {
        return length;
    }
    

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DigitalVideoDisc(String title) {
        this.title = title;
        incrementNbDiscs();
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        incrementNbDiscs();
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.cost = cost;
        incrementNbDiscs();
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
        incrementNbDiscs();
    }  

    private void incrementNbDiscs() {
        nbDigitalVideoDiscs++;         //increment class-level counter
        this.id = nbDigitalVideoDiscs; // Assign unique ID to this instance
    }

    //override toString() for formatted output
    @Override
    public String toString() {
        return "DVD - " + title + " - " + category + " - " + director + " - " + length + ": " + cost + " $";
    }

    public boolean isMatch(String title) {
        return this.title.equalsIgnoreCase(title); //case insensitive
    }

    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
}
