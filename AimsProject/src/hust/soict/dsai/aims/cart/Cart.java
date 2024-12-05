package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Cart {
    public static final int MAX_NUMBER_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<>();
    //private int qtyOrdered = 0;

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

    /*public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBER_ORDERED) {
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("the disc has been added.");
        } else System.out.println("the cart is almost full.");
    }

    public void addDigitalVideoDisc(DigitalVideoDisc [] dvdList) {
        if (qtyOrdered < MAX_NUMBER_ORDERED) {
            for (int i = 0; i < dvdList.length; i++) {
                itemsOrdered[qtyOrdered] = dvdList[i];
                qtyOrdered++;
                System.out.println("the disc " + dvdList[i].getTitle() + " has been added.");
            }
        } else System.out.println("the cart is almost full.");
    }

    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        if (qtyOrdered < MAX_NUMBER_ORDERED) {
                itemsOrdered[qtyOrdered] = dvd1;
                qtyOrdered++;
                System.out.println("the disc " + dvd1.getTitle() + " has been added.");
                if (qtyOrdered < MAX_NUMBER_ORDERED) {
                    itemsOrdered[qtyOrdered] = dvd2;
                    qtyOrdered++;
                    System.out.println("the disc " + dvd2.getTitle() + " has been added.");
                } else System.out.println("the cart is almost full.");
        } else System.out.println("the cart is almost full.");
    }     


    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].equals(disc)) {
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                qtyOrdered--;
                System.out.println("the disc has been removed.");
                return;
            }
        } 
        System.out.println("the disc is not found in the cart.");
    }*/

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
