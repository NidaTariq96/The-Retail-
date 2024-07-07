package com.backend.test;

import com.backend.test.entity.User;
import com.backend.test.fixture.RetailFixture;
import com.backend.test.strategy.AffiliateDiscountStrategy;
import com.backend.test.strategy.CustomerDiscountStrategy;
import com.backend.test.strategy.DefaultDiscountStrategy;
import com.backend.test.strategy.DiscountStrategy;
import com.backend.test.strategy.EmployeeDiscountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DiscountContextTest {

    private DiscountContext discountContext;

    @BeforeEach
    void beforeEach() {
        discountContext = new DiscountContext();
    }

    @Nested
    class GetDiscountStrategy {

        @Test
        void shouldReturnEmployeeDicsountStrategyWhenUserIsAnEmployee() {
            User user = RetailFixture.employeeUser;
            DiscountStrategy strategy = discountContext.getDiscountStrategy(user);
            assertTrue(strategy instanceof EmployeeDiscountStrategy, "Expected EmployeeDiscountStrategy");
        }

        @Test
        void testGetDiscountStrategyForAffiliate() {
            User user = RetailFixture.affiliateUser;
            DiscountStrategy strategy = discountContext.getDiscountStrategy(user);
            assertTrue(strategy instanceof AffiliateDiscountStrategy, "Expected AffiliateDiscountStrategy");
        }

        @Test
        void testGetDiscountStrategyForLongTermCustomer() {
            User user = RetailFixture.loyaltyUser;
            DiscountStrategy strategy = discountContext.getDiscountStrategy(user);
            assertTrue(strategy instanceof CustomerDiscountStrategy, "Expected CustomerDiscountStrategy");
        }

        @Test
        void testGetDiscountStrategyForNewCustomer() {
            User user = RetailFixture.testUser;
            DiscountStrategy strategy = discountContext.getDiscountStrategy(user);
            assertTrue(strategy instanceof DefaultDiscountStrategy, "Expected DefaultDiscountStrategy");
        }

    }

}
