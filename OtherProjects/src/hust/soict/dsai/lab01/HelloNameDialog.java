//eg 2.2.3: HelloNameDialog.java

import javax.swing.JOptionPane;

public class HelloNameDialog {
    public static void main(String[] args) {
        String res = JOptionPane.showInputDialog("please entere your name:");
        JOptionPane.showMessageDialog(null, "hi " + res + "!");
        System.exit(0);
    }
}
