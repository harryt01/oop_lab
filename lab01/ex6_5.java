//ex 6.5: array sorting
import java.util.Arrays;
import java.util.Scanner;

public class ex6_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for input or use a predefined array
        System.out.println("do you want to enter the array values? (y/n)");
        String userInput = scanner.nextLine();

        int[] array;
        if (userInput.equalsIgnoreCase("y")) {
            // Let the user enter the array elements
            System.out.print("enter the number of array elements: ");
            int n = scanner.nextInt();
            array = new int[n];
            System.out.println("enter the elements of the array:");
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }
        } else {
            System.out.println("using predefined array: 1789, 2035, 1899, 1456, 2013\n");
            // Use a predefined constant array
            array = new int[]{1789, 2035, 1899, 1456, 2013};  // Example constant array
        }

        // Sort the array
        Arrays.sort(array);

        // Calculate sum and average
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        double average = (double) sum / array.length;

        // Display the sorted array, sum, and average
        System.out.println("sorted array: " + Arrays.toString(array));
        System.out.println("sum of array elements: " + sum);
        System.out.println("average of array elements: " + average);

        scanner.close();
    }
}
