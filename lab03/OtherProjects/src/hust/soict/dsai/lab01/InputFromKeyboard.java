//ex 6.2: InputFromKeyboard
import java.util.Scanner;

public class InputFromKeyboard {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("what's your name? ");
        String strName = keyboard.nextLine();

        System.out.print("how old are you? ");
        int iAge = keyboard.nextInt();
        
        System.out.println("how tall are you (m)? ");
        double dHeight = keyboard.nextDouble();

        System.out.println("Mrs/Ms. " + strName + ", " + iAge + " years old. Your height is " + dHeight + " m.");
    }
}
