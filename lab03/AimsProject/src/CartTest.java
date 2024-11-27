public class CartTest {
    public static void main(String[] args) {
        //create a new cart
        Cart cart = new Cart();

        //create new dvd objects and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);
            
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);
        cart.addDigitalVideoDisc(dvd3);

        //test
        //print method
        cart.printCart();

        //search by id
        System.out.println("\nsearching for DVD with ID 1...");
        cart.searchDvd(1);

        System.out.println("\nsearching for DVD with ID 9...");
        cart.searchDvd(9);

        //search by title
        System.out.println("\nsearching for DVD with title \"Aladdin\"...");
        cart.searchDvd("Aladdin");

        System.out.println("\nsearching for DVD with title \"Frozen\"...");
        cart.searchDvd("Frozen");
    }
}
