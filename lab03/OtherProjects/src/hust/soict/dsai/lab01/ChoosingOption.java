//ex 6.1: ChoosingOption
import javax.swing.JOptionPane;

public class ChoosingOption {
    public static void main(String[] args) {
        int option = JOptionPane.showConfirmDialog(null, "do you want to change the first class ticket?");

        JOptionPane.showMessageDialog(null, "you've chosen: " + (option == JOptionPane.YES_OPTION ? "yes" : "no"));

        System.exit(0);
    }
}
