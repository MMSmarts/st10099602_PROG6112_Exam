package com.prog6112.q2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/** Tests the VAT and total sales figures in the paper. */
public class ProductCalculationsTest {
    /** Verify the expected calculation for the sample price. */
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        assertEquals(525.0, new ProductCalculations(3500).GetVat(), 0.001);
    }

    /** Verify the expected calculation for the sample price. */
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        assertEquals(4200.0, new ProductCalculations(3500).GetTotal(), 0.001);
    }
}
