package hust.soict.dsai.aims.store;
import hust.soict.dsai.aims.media.DigitalVideoDisc;


public class Store {
    private static final int MAX_STORE_ITEMS = 69; 
    private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_STORE_ITEMS];
    private int qtyInStore = 0; //# of DVDs currently in the store

    //add a DVD to the store
    public void addDvd(DigitalVideoDisc dvd) {
        if (qtyInStore < MAX_STORE_ITEMS) {
            itemsInStore[qtyInStore] = dvd;
            qtyInStore++;
            System.out.println("DVD \"" + dvd.getTitle() + "\" has been added to the store.");
        } else System.out.println("the store is almost full.");
    }

    //remove a DVD from the store
    public void removeDVD(DigitalVideoDisc dvd) {
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i].equals(dvd)) {
                //shift remaining items to the left
                for (int j = i; j < qtyInStore - 1; j++) 
                    itemsInStore[j] = itemsInStore[j + 1];
                itemsInStore[qtyInStore - 1] = null;
                qtyInStore--;
                System.out.println("DVD \"" + dvd.getTitle() + "\" has been removed from the store.");
                found = true;
                break;
            }
        }
        if (!found) System.out.println("DVD \"" + dvd.getTitle() + "\" is not found in the store.");
    }

    //display all DVDs in the store
    public void printStore() {
        System.out.println("***********************STORE***********************");
        if (qtyInStore == 0) {
            System.out.println("the store is empty.");
        } else {
            for (int i = 0; i < qtyInStore; i++) {
                System.out.println((i + 1) + ". " + itemsInStore[i].toString());
            }
        }
        System.out.println("***************************************************");
    }
}
