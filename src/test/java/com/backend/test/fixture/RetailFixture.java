package com.backend.test.fixture;


import com.backend.test.constant.UserType;
import com.backend.test.entity.Item;
import com.backend.test.entity.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class RetailFixture {

    public static User employeeUser = new User(1L, "employee", UserType.EMPLOYEE, LocalDateTime.now());
    public static User affiliateUser = new User(1L, "affiliate", UserType.AFFILIATE, LocalDateTime.now());
    public static User loyaltyUser = new User(1L, "loyalty", UserType.CUSTOMER, LocalDateTime.now().minusYears(2).minusDays(1));

    public static User testUser = new User(1L, "loyalty", UserType.CUSTOMER, LocalDateTime.now());

    public static List<Item> getSampleItems() {
        return Arrays.asList(
                new Item("Milk", "Grocery", 1000.0),
                new Item("Bread", "Grocery", 1000.0),
                new Item("Laptop", "Electronics", 5000.0)
        );
    }

    public static List<Item> getGroceryItems() {
        return Arrays.asList(
                new Item("Strawberries", "Grocery", 5000.0)
        );
    }

    public static List<Item> getNonGroceryItems() {
        return Arrays.asList(
                new Item("TV", "Electronics", 50.0)
        );
    }

}
