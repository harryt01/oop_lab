package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cart {
    public static final int MAX_NUMBER_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    public int getNumberOfItems() {
        return itemsOrdered.size();
    }

    public void addMedia(Media media){
        if (itemsOrdered.size() < MAX_NUMBER_ORDERED) {
            if (!itemsOrdered.contains(media)) {
                itemsOrdered.add(media);
                System.out.println("media item added: " + media.getTitle());
            } else System.out.println("media item already in the cart: " + media.getTitle());
        } else System.out.println("the cart is almost full.");
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("media item removed: " + media.getTitle());
        } else System.out.println("media item not found.");
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) total += media.getCost();
        return total;
    }

    public void printCart() {
        if (itemsOrdered.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            System.out.println("***********************CART***********************");
            System.out.println("Ordered Items:");
            for (int i = 0; i < itemsOrdered.size(); i++) {
                System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
            }
            System.out.println("Total cost: " + totalCost() + " $");
            System.out.println("***************************************************");
        }
    }

    public Media searchMedia(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Media found: " + media.toString());
                return media;
            }
        }
        System.out.println("No match found for ID: " + id);
        return null;
    }

    public Media searchMedia(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Media found: " + media.toString());
                return media;
            }
        }
        System.out.println("No match found for title: \"" + title + "\"");
        return null;
    }

     public void sortByTitle() {
        Collections.sort(itemsOrdered, new Comparator<Media>() {
            @Override
            public int compare(Media m1, Media m2) {
                int titleComparison = m1.getTitle().compareToIgnoreCase(m2.getTitle());
                if (titleComparison == 0) {
                    // If titles are the same, compare by cost
                    return Float.compare(m2.getCost(), m1.getCost());
                }
                return titleComparison;
            }
        });
    }

    public void sortByCost() {
        Collections.sort(itemsOrdered, new Comparator<Media>() {
            @Override
            public int compare(Media m1, Media m2) {
                int costComparison = Float.compare(m2.getCost(), m1.getCost());
                if (costComparison == 0) {
                    //if costs are the same, compare by title
                    return m1.getTitle().compareToIgnoreCase(m2.getTitle());
                }
                return costComparison;
            }
        });
    }

    public void clear() {
        itemsOrdered.clear();
        System.out.println("cart cleared.");
    }



}
