package com.prog6112.q1;

/** Required sales report calculation contract. */
public interface IProduct {
    int GetTotalProductsSold();
    double GetAverageProductSold();
    String GetMostPopularProduct();
}
