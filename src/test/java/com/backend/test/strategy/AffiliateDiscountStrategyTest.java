package com.backend.test.strategy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AffiliateDiscountStrategyTest {


    private AffiliateDiscountStrategy affiliateDiscountStrategy;

    @BeforeEach
    void beforeEach() {
        affiliateDiscountStrategy = new AffiliateDiscountStrategy();
    }

    @Test
    void shouldApplyAffiliateDiscountOnBill(){

        double billAmount = 100.0;
        double expectedDiscount = 10.0;

        double actualDiscount = affiliateDiscountStrategy.calculateDiscount(billAmount);

        assertEquals(expectedDiscount, actualDiscount,"Discount should be 10% of the bill amount");

    }
}
