package com.backend.test;

import com.backend.test.entity.User;
import com.backend.test.strategy.AffiliateDiscountStrategy;
import com.backend.test.strategy.CustomerDiscountStrategy;
import com.backend.test.strategy.DefaultDiscountStrategy;
import com.backend.test.strategy.DiscountStrategy;
import com.backend.test.strategy.EmployeeDiscountStrategy;

public class DiscountContext {
    private DiscountStrategy discountStrategy;
    public DiscountStrategy getDiscountStrategy(User user) {


        if (user.isAffiliate()) {
            discountStrategy = new AffiliateDiscountStrategy();
        } else if (user.isEmployee()) {
            discountStrategy = new EmployeeDiscountStrategy();
        } else if (user.isCustomerForOverTwoYears()) {
            discountStrategy = new CustomerDiscountStrategy();
        } else {
            discountStrategy = new DefaultDiscountStrategy();
        }

        return discountStrategy;
    }

}
