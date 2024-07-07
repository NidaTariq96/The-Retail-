package com.backend.test.strategy;

public class CustomerDiscountStrategy implements DiscountStrategy {

    @Override
    public double calculateDiscount(double billAmount) {
        return billAmount * 0.05;
    }
}
