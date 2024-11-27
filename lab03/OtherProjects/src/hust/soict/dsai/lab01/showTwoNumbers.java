//eg 2.2.4: showTwoNumbers.java

import javax.swing.JOptionPane;

public class showTwoNumbers {
    public static void main(String[] args) {
        String strNum1, strNum2;
        String strNotification = "you've just entered: ";
        
        strNum1 = JOptionPane.showInputDialog(null, "please enter the first number", "enter the first number", JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum1 + " and ";
        
        strNum2 = JOptionPane.showInputDialog(null, "please enter the second number", "enter the second number", JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum2;
        
        JOptionPane.showMessageDialog(null, strNotification, "show two numbers", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
}
