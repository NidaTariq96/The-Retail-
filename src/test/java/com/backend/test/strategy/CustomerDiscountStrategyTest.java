package com.backend.test.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomerDiscountStrategyTest {


    private CustomerDiscountStrategy customerDiscountStrategy;

    @BeforeEach
    void beforeEach() {
        customerDiscountStrategy = new CustomerDiscountStrategy();
    }

    @Test
    void shouldApplyCustomerDiscountOnBill(){

        double billAmount = 100.0;
        double expectedDiscount = 5.0;

        double actualDiscount = customerDiscountStrategy.calculateDiscount(billAmount);

        assertEquals(expectedDiscount, actualDiscount,"Discount should be 5% of the bill amount");

    }
}
