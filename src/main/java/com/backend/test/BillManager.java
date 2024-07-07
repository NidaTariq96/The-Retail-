package com.backend.test;

import com.backend.test.entity.Item;
import com.backend.test.entity.User;
import com.backend.test.exception.ItemNotFoundException;
import com.backend.test.exception.UserNotFoundException;
import com.backend.test.strategy.DiscountStrategy;

import java.util.List;
import java.util.stream.Collectors;


public class BillManager {

    private final DiscountContext discountContext;

    public BillManager(DiscountContext discountContext) {
        this.discountContext = discountContext;
    }

    public double applyDiscount(User user, List<Item> items) throws ItemNotFoundException, UserNotFoundException {

            if (items == null || items.isEmpty()) {
                throw new ItemNotFoundException();
            }

            if(user == null) {
                throw new UserNotFoundException();
            }

            double totalItemsPrice = calculateTotalPrice(items);

            double nonGroceryItemsTotalPrice = calculateTotalPrice(getNonGroceryItems(items));

            double percentageDiscountNonGrocery = 0;

            if(nonGroceryItemsTotalPrice  > 0.0) {
                percentageDiscountNonGrocery = getPercentageDiscount(user, nonGroceryItemsTotalPrice);
            }

            double totalPriceAfterPercentageDiscount = totalItemsPrice - percentageDiscountNonGrocery;

            double flatDiscount = applyAdditionalFlatDiscount(totalPriceAfterPercentageDiscount);

            return totalPriceAfterPercentageDiscount - flatDiscount;

    }

    private double calculateTotalPrice(List<Item> items) {
        return items.stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

    private double getPercentageDiscount(User user, double nonGroceryItemsTotalPrice) {
        DiscountStrategy discountStrategy = discountContext.getDiscountStrategy(user);
        return discountStrategy.calculateDiscount(nonGroceryItemsTotalPrice);

    }

    private List<Item> getNonGroceryItems(List<Item> items) {
        return items.stream()
                .filter(Item::isNotGrocery)
                .collect(Collectors.toList());
    }

    private double applyAdditionalFlatDiscount(double discountAmount) {
        return ((int) (discountAmount / 100)) * 5.0;
    }

}
