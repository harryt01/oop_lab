package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import java.util.Scanner;

public class Aims {
    private static final Store store = new Store();
    private static final Cart cart = new Cart();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeStore();

        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (option) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("exit AIMS successfully.");
                    break;
                default:
                    System.out.println("invalid option. please try again.");
                } 
        } while (option != 0);
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void viewStore() {
        System.out.println("STORE ITEMS:");
        store.printStore();
        int option;
        do {
            storeMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (option) {
                case 1:
                    seeMediaDetails();
                    break;
                case 2:
                    addMediaToCart();
                    break;
                case 3:
                    playMedia();
                    break;
                case 4:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }            
        } while (option != 0);
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void seeMediaDetails() {
        System.out.println("enter the media title: ");
        String title = scanner.nextLine();
        Media media = store.searchMedia(title);
        if (media != null) { 
            System.out.println(media.toString());
            mediaDetailsMenu(media);
        } else {
            System.out.println("Media not found!");
        }
    }

    public static void mediaDetailsMenu(Media media) {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        if (media instanceof DigitalVideoDisc || media instanceof CompactDisc) {
            System.out.println("2. Play");
        }
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
        int option = scanner.nextInt();
        scanner.nextLine(); // consume newline
        switch (option) {
            case 1:
                cart.addMedia(media);
                System.out.println("Media added to cart.");
                break;
            case 2:
                if (media instanceof DigitalVideoDisc || media instanceof CompactDisc) {
                    if (media instanceof DigitalVideoDisc) {
                        ((DigitalVideoDisc) media).play();
                    } else if (media instanceof CompactDisc) {
                        ((CompactDisc) media).play();
                    }
                } else {
                    System.out.println("Invalid option for this media.");
                }
                break;
            case 0:
                System.out.println("Returning...");
                break;
            default:
                System.out.println("Invalid option. Returning...");
                break;
        }        
    }

    public static void addMediaToCart() {
        System.out.println("Enter the title of the media to add to the cart: ");
        String title = scanner.nextLine();
        Media media = store.searchMedia(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Media added to cart. Current cart size: " + cart.getNumberOfItems());
        } else {
            System.out.println("Media not found in the store.");
        }
    }

    public static void playMedia() {
        System.out.println("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = store.searchMedia(title);
        if (media instanceof DigitalVideoDisc || media instanceof CompactDisc) {
            ((DigitalVideoDisc) media).play();
        } else {
            System.out.println("Media cannot be played or was not found.");
        }
    }

    public static void updateStore() {
        System.out.println("Options:");
        System.out.println("1. Add a media to the store");
        System.out.println("2. Remove a media from the store");
        System.out.println("0. Back");
        int option = scanner.nextInt();
        scanner.nextLine(); // consume newline
        switch (option) {
            case 1:
                System.out.println("Enter the title of the media to add: ");
                String title = scanner.nextLine();
                System.out.println("Enter the category: ");
                String category = scanner.nextLine();
                System.out.println("Enter the cost: ");
                float cost = scanner.nextFloat();
                scanner.nextLine(); // consume newline
                store.addMedia(new DigitalVideoDisc(title, category, cost));
                System.out.println("Media added to store.");
                break;
            case 2:
                System.out.println("Enter the title of the media to remove: ");
                title = scanner.nextLine();
                Media media = store.searchMedia(title);
                if (media != null) {
                    store.removeMedia(media);
                    System.out.println("Media removed from store.");
                } else {
                    System.out.println("Media not found.");
                }
                break;
            case 0:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }        
    }

    public static void seeCurrentCart() {
        System.out.println("CURRENT CART:");
        cart.printCart();
        int option;
        do {
            cartMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (option) {
                case 1:
                    filterCart();
                    break;
                case 2:
                    sortCart();
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    playFromCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }            
        } while (option != 0);
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static void filterCart() {
        System.out.println("Filter options: ");
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by title");
        System.out.println("0. Back");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (option) {
            case 1:
                System.out.println("Enter ID to filter: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Media media = cart.searchMedia(id);
                if (media != null) {
                    System.out.println(media.toString());
                } else {
                    System.out.println("No media found with the given ID.");
                }
                break;
            case 2:
                System.out.println("Enter title to filter: ");
                String title = scanner.nextLine();
                media = cart.searchMedia(title);
                if (media != null) {
                    System.out.println(media.toString());
                } else {
                    System.out.println("No media found with the given title.");
                }
                break;
            case 0:
                System.out.println("Returning to cart menu...");
                break;
            default:
                System.out.println("Invalid option. Returning to cart menu...");
                break;
        }        
    }

    public static void sortCart() {
        System.out.println("Sort options: ");
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by cost");
        System.out.println("0. Back");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (option) {
            case 1:
                cart.sortByTitle();
                System.out.println("Cart sorted by title:");
                cart.printCart();
                break;
            case 2:
                cart.sortByCost();
                System.out.println("Cart sorted by cost:");
                cart.printCart();
                break;
            case 0:
                System.out.println("Returning to cart menu...");
                break;
            default:
                System.out.println("Invalid option. Returning to cart menu...");
                break;
        }        
    }

    public static void removeFromCart() {
        System.out.println("Enter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media media = cart.searchMedia(title);
        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Media removed from cart.");
        } else {
            System.out.println("Media not found in the cart.");
        }
    }

    public static void playFromCart() {
        System.out.println("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = cart.searchMedia(title);
        if (media instanceof DigitalVideoDisc || media instanceof CompactDisc) {
            ((DigitalVideoDisc) media).play();
        } else {
            System.out.println("Media cannot be played or was not found.");
        }
    }

    public static void placeOrder() {
        System.out.println("Order placed! Your cart is now empty.");
        cart.clear();
    }

    private static void initializeStore() {
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f));
        store.addMedia(new DigitalVideoDisc("Aladdin", "Animation", 18.99f));
        store.addMedia(new Book("Harry Potter", "Fantasy", 12.99f));
        store.addMedia(new CompactDisc("Thriller", "Music", 14.99f));
    }
}