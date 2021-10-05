package Classes.Pizza;

import Classes.MenuItem;

public class Pizza implements MenuItem {

    private final int id;
    private final String name;
    private final boolean vegetarian;
    private final double price = 0;

    public Pizza(int id, String name, boolean vegetarian) {
        this.id = id;
        this.name = name;
        this.vegetarian = vegetarian;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    public boolean isVegeterian() { return vegetarian; }
}