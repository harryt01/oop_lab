package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Cart {
    public static final int MAX_NUMBER_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

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
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }

    public void searchMedia(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Media found: " + media.toString());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("No match found for ID: " + id);
    }

    public void searchMedia(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Media found: " + media.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No match found for title: \"" + title + "\"");
    }
}
