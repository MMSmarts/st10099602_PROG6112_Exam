package com.prog6112.q2;

/** Calculates VAT, commission and the total amount for one sale. */
public class ProductCalculations implements IProductsCalculations {
    private final double price;

    /** Verify the price before using it in calculations. */
    public ProductCalculations(double price) {
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Invalid product price.");
        }
        this.price = price;
    }

    /** Calculate the value required by the interface. */
    @Override
    public double GetVat() {
        return price * 0.15;
    }

    /** Calculate the value required by the interface. */
    @Override
    public double GetCommission() {
        return price * 0.05;
    }

    /** Calculate the value required by the interface. */
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}
