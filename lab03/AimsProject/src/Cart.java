public class Cart {
    public static final int MAX_NUMBER_ORDERED = 20;
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBER_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
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
    }
    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }
}
