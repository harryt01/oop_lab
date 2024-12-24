//ex 6.3: asterisk triangle

import java.util.Scanner;

public class ex6_3 {
    public static void main(String[] args) {
        // Create a Scanner object to get input from user
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the height of the triangle
        System.out.print("enter the triangle height: ");
        int n = scanner.nextInt();

        // Loop to print each row of the triangle
        for (int i = 1; i <= n; i++) {
            // Print spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }

            // Print stars
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }

            // Move to the next line after each row
            System.out.println();
        }

        // Close the scanner
        scanner.close();
    }
}

