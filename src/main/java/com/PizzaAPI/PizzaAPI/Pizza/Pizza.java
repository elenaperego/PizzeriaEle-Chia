package com.PizzaAPI.PizzaAPI.Pizza;

import java.util.ArrayList;

public class Pizza {

    private final long id;
    private final String name;
    private final boolean vegetarian;

    public Pizza(long id, String name, boolean vegetarian) {
        this.id = id;
        this.name = name;
        this.vegetarian = vegetarian;
    }

    public long getId() {
        return id;
    }

    public boolean isVegeterian() {
        return vegetarian;
    }

    public String getName() { return name; }
}