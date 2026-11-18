package com.prog6112.q1;

import java.util.Scanner;

/** Collects three months of mattress sales and prints the sales report. */
public class MattressSalesReport {
    /** Read and validate each product-month sales figure before reporting. */
    public static void main(String[] args) {
        String[] products = {
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
        };
        int[][] sales = new int[products.length][3];

        Scanner scanner = new Scanner(System.in);
        // Outer loop selects a product; inner loop captures each of its three months.
        for (int product = 0; product < products.length; product++) {
            for (int month = 0; month < sales[product].length; month++) {
                System.out.print("Enter number of sales for month " + (month + 1)
                        + " for " + products[product] + " mattress: ");
                // Reject non-integer input so invalid data cannot enter the array.
                while (!scanner.hasNextInt()) {
                    System.out.print("Enter a valid whole number: ");
                    scanner.next();
                }
                int numberSold = scanner.nextInt();
                // Retry the same month if a negative quantity was entered.
                if (numberSold < 0) {
                    System.out.println("Sales cannot be negative.");
                    month--;
                } else {
                    sales[product][month] = numberSold;
                }
            }
            System.out.println();
        }

        Product product = new Product(products, sales);
        new PrintProduct(product).printReport();
    }
}
