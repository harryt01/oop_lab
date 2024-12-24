//import hust.soict.dsai.lab02.Cart;
//import hust.soict.dsai.lab02.DigitalVideoDisc;

public class Aims {
    public static void main(String[] args) {
        //create a new cart
        Cart anOrder = new Cart();

        //create new dvd objects and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);
            
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);

        //print total cost of the items in the cart
        System.out.println("total cost is: " + anOrder.totalCost());

        //remove one item from the cart
        anOrder.removeDigitalVideoDisc(dvd1);

        //print total cost of the items in the cart
        System.out.println("total cost is: " + anOrder.totalCost());
    }
}