package com.backend.test.entity;

import com.backend.test.constant.UserType;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String name;
    private UserType type;
    private LocalDateTime createdAt;

    public User(Long id, String name, UserType type, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
    }

    public boolean isEmployee() {
        return this.type == UserType.EMPLOYEE;
    }

    public boolean isAffiliate() {
        return this.type == UserType.AFFILIATE;
    }

    public boolean isCustomerForOverTwoYears() {
        return this.type == UserType.CUSTOMER && createdAt.isBefore(LocalDateTime.now().minusYears(2));
    }


}
