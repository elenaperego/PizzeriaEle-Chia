package com.PizzaAPI.PizzaAPI.Pizza;

import java.util.ArrayList;

public class Pizza {

    private final long id;
    private final String name;
    private final double price;
    private final boolean vegetarian;
    private final ArrayList<String> toppings;

    public Pizza(long id, String name, double price, boolean vegetarian, ArrayList<String> toppings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vegetarian = vegetarian;
        this.toppings = toppings;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegeterian() {
        return vegetarian;
    }

    public ArrayList<String> getToppings(){ return toppings; }
}