package hust.soict.dsai.aims.media;

import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title, category;
    private float cost;
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public int getId() {
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
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Media() {
    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; //check if the objects are the same instance
        if (obj == null) return false; //check for null
        if (!(obj instanceof Media)) return false; //check for type compatibility

        Media otherMedia = (Media) obj;
        return title != null ? title.equals(otherMedia.title) : otherMedia.title == null; //compare titles
    }
}
