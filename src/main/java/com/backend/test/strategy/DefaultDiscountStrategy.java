package com.backend.test.strategy;

public class DefaultDiscountStrategy implements DiscountStrategy {

    @Override
    public double calculateDiscount(double billAmount) {
        return billAmount;
    }
}
