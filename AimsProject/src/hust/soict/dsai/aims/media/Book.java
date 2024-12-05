package hust.soict.dsai.aims.media;

import java.lang.Object;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Book extends Media {  
    private List<String> authors = new ArrayList<String>();
    private static int nbBooks = 0;

    public Book(String title, String category, float cost){
        super(++nbBooks, title, category, cost);
    }

    public void addAuthors(String authorName){
          if(!authors.contains(authorName)){
            authors.add(authorName);
            System.out.println("author" + authorName + "added.");  
          } else System.out.println("author" + authorName + "already exists.");
    }

    public void removeAuthors(String authorName){
        if(authors.contains(authorName)){
            authors.remove(authorName);
            System.out.println("author" + authorName + "removed.");  
        } else System.out.println("author" + authorName + "does not exist.");
    }

    @Override
    public String toString() {
        String authorList = String.join(", ", authors);  
        return "Book - " + getTitle() + " - " + getCategory() + " - =" + getCost() + " - " + authorList + " - " + getCost() + " $"; 
    }
    
    
}
