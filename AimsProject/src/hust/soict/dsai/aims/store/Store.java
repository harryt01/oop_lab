package hust.soict.dsai.aims.store;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;


public class Store {
    private static final int MAX_STORE_ITEMS = 69; 
    //private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_STORE_ITEMS];
    private ArrayList<DigitalVideoDisc> itemsInStore = new ArrayList<>();
    private int qtyInStore = 0; //# of DVDs currently in the store

    //add a DVD to the store
    public void addMedia(Media media){
        if (qtyInStore < MAX_STORE_ITEMS) {
            itemsInStore.add((DigitalVideoDisc) media);
            System.out.println("media item added: " + media.getTitle());
            qtyInStore++;
        } else System.out.println("the store is almost full.");
    }

    //remove a DVD from the store
    public void removeMedia(Media media){
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("media item removed: " + media.getTitle());
            qtyInStore--;
        } else System.out.println("media item not found.");
    }

    //display all DVDs in the store
    public void printStore() {
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            System.out.println("***********************STORE***********************");
            if (qtyInStore == 0) {
                System.out.println("the store is empty.");
            } else {
                for (int i = 0; i < qtyInStore; i++) {
                    System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
                }
            }
            System.out.println("***************************************************");
        }
    }

    public Media searchMedia(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    


}
