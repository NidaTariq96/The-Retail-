package com.backend.test;


import com.backend.test.entity.User;
import com.backend.test.exception.ItemNotFoundException;
import com.backend.test.exception.UserNotFoundException;
import com.backend.test.fixture.RetailFixture;
import com.backend.test.strategy.EmployeeDiscountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BillManagerTest {

    @Mock
    private DiscountContext discountContext;

    private BillManager billManager;


    @BeforeEach
    void beforeEach() {
        billManager = new BillManager(discountContext);
    }

    @Nested
    class DiscountApplied {

        @Test
        void shouldOnlyApplyFlatDiscountsWhenAllItemsAreGroceryItems() throws UserNotFoundException, ItemNotFoundException {

            double finalAmount = billManager.applyDiscount(RetailFixture.employeeUser, RetailFixture.getGroceryItems());

            assertEquals(4750.0, finalAmount);
            verify(discountContext, times(0)).getDiscountStrategy(any());
        }

        @Test
        void shouldOnlyApplyPercentageDiscountsWhenAllItemsAreNonGroceryItemsAndBillAmountIsLessThanHundred() throws UserNotFoundException, ItemNotFoundException {

            User user = RetailFixture.employeeUser;
            when(discountContext.getDiscountStrategy(user)).thenReturn(new EmployeeDiscountStrategy());

            double finalAmount = billManager.applyDiscount(user, RetailFixture.getNonGroceryItems());


            assertEquals(35.0, finalAmount);
            verify(discountContext, times(1)).getDiscountStrategy(any());
        }

        @Test
        void shouldApplyFlatAndPercentageDiscountWhenAllTypesOfItemAreThere() throws UserNotFoundException, ItemNotFoundException {

            User user = RetailFixture.employeeUser;

            when(discountContext.getDiscountStrategy(user)).thenReturn(new EmployeeDiscountStrategy());

            double finalAmount = billManager.applyDiscount(user, RetailFixture.getSampleItems());

            assertEquals(5225.0, finalAmount);

            verify(discountContext).getDiscountStrategy(user);
        }

    }

    @Nested
    class MissingRequestParams {

        @Test
        void shouldThrowUserNotFoundExceptionWhenUserIsNull() {

            assertThrows(UserNotFoundException.class, () -> {
                billManager.applyDiscount(null, RetailFixture.getGroceryItems());
            });
        }

        @Test
        void shouldThrowItemNotFoundExceptionWhenItemIsMissing() {

            assertThrows(ItemNotFoundException.class, () -> {
                billManager.applyDiscount(RetailFixture.employeeUser, null);
            });

        }
    }
}