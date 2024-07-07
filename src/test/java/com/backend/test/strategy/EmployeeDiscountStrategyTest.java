package com.backend.test.strategy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeDiscountStrategyTest {

    private EmployeeDiscountStrategy employeeDiscountStrategy;

    @BeforeEach
    void beforeEach() {
        employeeDiscountStrategy = new EmployeeDiscountStrategy();
    }

    @Test
    void shouldApplyEmployeeDiscountOnBill(){

        double billAmount = 100.0;
        double expectedDiscount = 30.0;

        double actualDiscount = employeeDiscountStrategy.calculateDiscount(billAmount);

        assertEquals(expectedDiscount, actualDiscount,"Discount should be 30% of the bill amount");

    }
}
