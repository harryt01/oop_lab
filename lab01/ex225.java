//ex 2.2.5 basic math operations
import java.util.Scanner;

public class ex225 {
    public static void main(String[] args) {
        // Create a Scanner object to read input from user
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user to enter two numbers as strings
        System.out.print("Enter the first number: ");
        String strNum1 = scanner.nextLine();
        
        System.out.print("Enter the second number: ");
        String strNum2 = scanner.nextLine();
        
        // Convert the strings to double using Double.parseDouble
        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);
        
        // Calculate sum, difference, and product
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        
        // Check the divisor before performing division
        if (num2 != 0) {
            double quotient = num1 / num2;
            System.out.println("The quotient is: " + quotient);
        } else {
            System.out.println("Division by zero is not allowed.");
        }
        
        // Print the results
        System.out.println("The sum is: " + sum);
        System.out.println("The difference is: " + difference);
        System.out.println("The product is: " + product);
    }
}

