package com.backend.test.strategy;

public class AffiliateDiscountStrategy implements DiscountStrategy {

    @Override
    public double calculateDiscount(double billAmount) {
        return billAmount * 0.10;
    }
}
