package com.prog6112.q1;

/** Stores mattress sales and calculates the values needed for the report. */
public class Product implements IProduct {
    private final String[] productNames;
    private final int[][] monthlySales;

    /** Validate and copy the supplied product names and monthly sales. */
    public Product(String[] productNames, int[][] monthlySales) {
        if (productNames == null || monthlySales == null
                || productNames.length == 0 || productNames.length != monthlySales.length) {
            throw new IllegalArgumentException("Product names and sales must match.");
        }
        this.productNames = productNames.clone();
        this.monthlySales = new int[monthlySales.length][];
        for (int i = 0; i < monthlySales.length; i++) {
            if (monthlySales[i] == null || monthlySales[i].length == 0) {
                throw new IllegalArgumentException("Each product must have monthly sales.");
            }
            this.monthlySales[i] = monthlySales[i].clone();
            for (int sale : this.monthlySales[i]) {
                if (sale < 0) {
                    throw new IllegalArgumentException("Sales cannot be negative.");
                }
            }
        }
    }

    // Add all product sales over all months.
    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int[] productSales : monthlySales) {
            for (int sale : productSales) {
                total += sale;
            }
        }
        return total;
    }

    // Average across every product-month value, as shown in the sample.
    @Override
    public double GetAverageProductSold() {
        int count = 0;
        for (int[] productSales : monthlySales) {
            count += productSales.length;
        }
        return (double) GetTotalProductsSold() / count;
    }

    // Find the product with the largest combined sales for all months.
    @Override
    public String GetMostPopularProduct() {
        int bestIndex = 0;
        int bestTotal = -1;
        for (int i = 0; i < monthlySales.length; i++) {
            int total = getProductTotal(i);
            if (total > bestTotal) {
                bestTotal = total;
                bestIndex = i;
            }
        }
        return productNames[bestIndex] + "(" + getHighestMonthSale(bestIndex) + ")";
    }

    /** Return the total units sold for one product across its months. */
    private int getProductTotal(int index) {
        int total = 0;
        for (int sale : monthlySales[index]) {
            total += sale;
        }
        return total;
    }

    // The sample displays the winning product's highest monthly value in brackets.
    private int getHighestMonthSale(int index) {
        int highest = 0;
        for (int sale : monthlySales[index]) {
            highest = Math.max(highest, sale);
        }
        return highest;
    }
}
