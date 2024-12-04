package hust.soict.dsai.test.store;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        //create some DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);

        //add DVDs to the store
        store.addDvd(dvd1);
        store.addDvd(dvd2);
        store.addDvd(dvd3);

        //show the store
        System.out.println("\ncurrent store:");
        store.printStore();

        //remove a DVD
        System.out.println("\nremoving \"Star Wars\" from the store...");
        store.removeDVD(dvd2);

        //show the store
        System.out.println("\ncurrent store:");
        store.printStore();

        //attempt to remove a DVD not in the store
        System.out.println("\nremoving a non-existent DVD:");
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Frozen", "Animation", 19.99f);
        store.removeDVD(dvd4);
    }
}
