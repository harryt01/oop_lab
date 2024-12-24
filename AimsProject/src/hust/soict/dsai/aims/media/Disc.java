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
    
    public Disc(int id, String director) {
        super(id, null, null, 0);
        this.director = director;
    }

    public Disc(int id, String director, int length) {
        super(id, null, null, 0);
        this.director = director;
        this.length = length;
    }    

    public Disc(int id, String title, String category, int length, String director, float cost) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }

    public Disc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public Disc(int id, String title, String category, String director, float cost) {
        super(id, title, category, cost);
        this.director = director;
    }
}
