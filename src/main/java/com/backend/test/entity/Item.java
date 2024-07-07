package com.backend.test.entity;


public class Item {
    private String name;

    private String type;

    private Double price;

    public Item(String name, String type, Double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public boolean isNotGrocery() {
        return !this.type.equalsIgnoreCase("grocery");
    }

}
