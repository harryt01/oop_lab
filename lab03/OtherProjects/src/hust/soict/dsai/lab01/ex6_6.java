//ex 6.6: adding 2 matrices
import java.util.Scanner;

public class ex6_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for input or use predefined matrices
        System.out.println("enter the matrices manually? (y/n)");
        String userInput = scanner.nextLine();

        int rows, cols;
        int[][] matrix1, matrix2, sumMatrix;

        if (userInput.equalsIgnoreCase("y")) {
            // Ask for matrix size
            System.out.print("enter the number of rows: ");
            rows = scanner.nextInt();
            System.out.print("enter the number of columns: ");
            cols = scanner.nextInt();

            // Initialize matrices
            matrix1 = new int[rows][cols];
            matrix2 = new int[rows][cols];
            sumMatrix = new int[rows][cols];

            // Input values for the first matrix
            System.out.println("enter values for the first matrix:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print("element [" + i + "][" + j + "]: ");
                    matrix1[i][j] = scanner.nextInt();
                }
            }

            // Input values for the second matrix
            System.out.println("enter values for the second matrix:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print("element [" + i + "][" + j + "]: ");
                    matrix2[i][j] = scanner.nextInt();
                }
            }
        } else {
            // Use predefined constant matrices
            System.out.println("using predefined matrices:");
            System.err.println("matrix1:\n1 2 3\n4 5 6\n7 8 9\nmatrix2:\n9 8 7\n6 5 4\n3 2 1");
            matrix1 = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };
            matrix2 = new int[][] {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
            };
            rows = matrix1.length;
            cols = matrix1[0].length;
            sumMatrix = new int[rows][cols];
        }

        // Add the two matrices
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        // Display the sum matrix
        System.out.println("the result matrix is:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(sumMatrix[i][j] + "\t");
            }
            System.out.println();
        }

        scanner.close();
    }
}

