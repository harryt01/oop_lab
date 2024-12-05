package hust.soict.dsai.aims.media;

public class Disc extends Media {
    private String director;
    private int length;

    public String getDirector() {
        return director;
    }
    public int getLength() {
        return length;
    }
    
    public Disc(String director) {
        this.director = director;
    }

    public Disc(String director, int length) {
        this.director = director;
        this.length = length;
    }    

    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }
}
