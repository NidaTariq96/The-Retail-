package com.backend.test.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DefaultDiscountStrategyTest {


    private DefaultDiscountStrategy defaultDiscountStrategy;

    @BeforeEach
    void beforeEach() {
        defaultDiscountStrategy = new DefaultDiscountStrategy();
    }

    @Test
    void shouldApplyDefaultDiscountOnBillThenReturnOriginalBillAmount(){

        double billAmount = 100.0;
        double expectedDiscount = 100.0;

        double actualDiscount = defaultDiscountStrategy.calculateDiscount(billAmount);

        assertEquals(expectedDiscount, actualDiscount,"Return original bill amount");

    }
}
