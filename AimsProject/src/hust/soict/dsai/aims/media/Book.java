package hust.soict.dsai.aims.media;

import java.lang.Object;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Book extends Media {  
    private List<String> authors = new ArrayList<String>();
/*   private int id;
    private String title, category;
    private float cost;
  

    //public Book(){}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
*/
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
