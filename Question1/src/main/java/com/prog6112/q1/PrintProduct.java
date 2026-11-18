package com.prog6112.q1;

/** Prints each section of the mattress sales report. */
public class PrintProduct {
    private final IProduct product;

    /** Receive the report calculations through the required interface. */
    public PrintProduct(IProduct product) {
        this.product = product;
    }

    /** Print the sum of all recorded sales. */
    public void printTotalProductSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    /** Print the average in the same whole-number style as the sample. */
    public void printAverageProductSales() {
        System.out.println("AVERAGE PRODUCT SALES: " + (int) product.GetAverageProductSold());
    }

    /** Print the best-selling product and its sample-style bracket value. */
    public void printMostPopularProductSale() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }

    /** Print the report heading and all three results. */
    public void printReport() {
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println();
        System.out.println("*********************************************");
        printTotalProductSales();
        printAverageProductSales();
        printMostPopularProductSale();
    }
}
