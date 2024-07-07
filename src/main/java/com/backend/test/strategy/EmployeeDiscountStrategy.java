package com.backend.test.strategy;

public class EmployeeDiscountStrategy implements DiscountStrategy {

    @Override
    public double calculateDiscount(double billAmount) {
        return billAmount * 0.30;
    }

}