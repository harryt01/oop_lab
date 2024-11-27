//ex 6.4: numbers of days in a month

import java.util.Scanner;

public class ex6_4 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String monthInput;
        int year;
        
        // Loop until the user enters a valid month and year
        while (true) {
            // Prompt for month input
            System.out.print("enter a month (full name, abbreviation, or number\ne.g: january, jan, jan., 1): ");
            monthInput = scanner.nextLine().trim();
            
            // Prompt for year input
            System.out.print("enter a year: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
            } else {
                System.out.println("invalid input! please enter a valid year.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }
            
            // Check if the year is non-negative
            if (year < 0) {
                System.out.println("the year must be a non-negative number. please enter again.");
                continue;
            }

            // Determine the number of days in the entered month and year
            int days = getDaysInMonth(monthInput, year);
            
            // If the month input is valid, break the loop
            if (days != -1) {
                System.out.println("the month " + monthInput + " in the year " + year + " has " + days + " days.");
                break;
            } else {
                System.out.println("invalid month. please enter again.");
            }
        }
        
        scanner.close();
    }
    
    // Function to determine if a year is a leap year
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    // Function to get the number of days in a given month and year
    public static int getDaysInMonth(String monthInput, int year) {
        // Normalize month input
        String month = monthInput.toLowerCase();
        
        // Mapping month names, abbreviations, and numbers to days in common and leap years
        switch (month) {
            case "january": case "jan.": case "jan": case "1":
                return 31;
            case "february": case "feb.": case "feb": case "2":
                return isLeapYear(year) ? 29 : 28;
            case "march": case "mar.": case "mar": case "3":
                return 31;
            case "april": case "apr.": case "apr": case "4":
                return 30;
            case "may": case "may.": case "5":
                return 31;
            case "june": case "jun.": case "jun": case "6":
                return 30;
            case "july": case "jul.": case "jul": case "7":
                return 31;
            case "august": case "aug.": case "aug": case "8":
                return 31;
            case "september": case "sep.": case "sep": case "9":
                return 30;
            case "october": case "oct.": case "oct": case "10":
                return 31;
            case "november": case "nov.": case "nov": case "11":
                return 30;
            case "december": case "dec.": case "dec": case "12":
                return 31;
            default:
                return -1; // Invalid month input
        }
    }
}
