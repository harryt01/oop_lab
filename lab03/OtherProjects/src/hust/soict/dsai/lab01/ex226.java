//ex 2.2.6 solving equations
import java.util.Scanner;

public class ex226 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1: first-degree equation ax + b = 0
        System.out.println("solving first-degree equation ax + b = 0");
        System.out.print("enter a: ");
        double a1 = scanner.nextDouble();
        System.out.print("enter b: ");
        double b1 = scanner.nextDouble();
        
        if (a1 == 0) {
            if (b1 == 0) {
                System.out.println("the equation has infinite solutions.");
            } else {
                System.out.println("the equation has no solution.");
            }
        } else {
            double x = -b1 / a1;
            System.out.println("the solution is: x = " + x);
        }
        System.out.println();

        //2: system of first-degree equations with 2 variables
        System.out.println("solving a system of two first-degree equations:");
        System.out.println("a11 * x1 + a12 * x2 = b1");
        System.out.println("a21 * x1 + a22 * x2 = b2");

        System.out.print("enter a11: ");
        double a11 = scanner.nextDouble();
        System.out.print("enter a12: ");
        double a12 = scanner.nextDouble();
        System.out.print("enter b1: ");
        double b1_sys = scanner.nextDouble();
        System.out.print("enter a21: ");
        double a21 = scanner.nextDouble();
        System.out.print("enter a22: ");
        double a22 = scanner.nextDouble();
        System.out.print("enter b2: ");
        double b2_sys = scanner.nextDouble();

        // Calculating determinants
        double d = a11 * a22 - a21 * a12;
        double d1 = b1_sys * a22 - b2_sys * a12;
        double d2 = a11 * b2_sys - a21 * b1_sys;

        if (d == 0) {
            if (d1 == 0 && d2 == 0) {
                System.out.println("the system has infinite solutions.");
            } else {
                System.out.println("the system has no solution.");
            }
        } else {
            double x1 = d1 / d;
            double x2 = d2 / d;
            System.out.println("the solution is: x1 = " + x1 + ", x2 = " + x2);
        }
        System.out.println();

        //3: Solving a second-degree equation ax^2 + bx + c = 0
        System.out.println("solving the second-degree equation ax^2 + bx + c = 0");
        System.out.print("enter a: ");
        double a_quad = scanner.nextDouble();
        System.out.print("enter b: ");
        double b_quad = scanner.nextDouble();
        System.out.print("enter c: ");
        double c_quad = scanner.nextDouble();

        if (a_quad == 0) {
            System.out.println("this is not a quadratic equation.");
        } else {
            double discriminant = b_quad * b_quad - 4 * a_quad * c_quad;

            if (discriminant > 0) {
                double x1 = (-b_quad + Math.sqrt(discriminant)) / (2 * a_quad);
                double x2 = (-b_quad - Math.sqrt(discriminant)) / (2 * a_quad);
                System.out.println("the equation has two distinct real roots: x1 = " + x1 + ", x2 = " + x2);
            } else if (discriminant == 0) {
                double x = -b_quad / (2 * a_quad);
                System.out.println("the equation has a double root: x = " + x);
            } else {
                System.out.println("the equation has no real roots.");
            }
        }
        scanner.close();
    }
}