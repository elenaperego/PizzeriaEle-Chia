package Classes.Drink;

import Classes.MenuItem;

public class Drink implements MenuItem {
    private final int drinkId;
    private final String drinkName;
    private final double drinkPrice;

    public Drink(int id, String name, double price) {
        this.drinkId = id;
        this.drinkName = name;
        this.drinkPrice = price;
    }

    @Override
    public int getId() {
        return this.drinkId;
    }

    @Override
    public String getName() { return this.drinkName; }

    @Override
    public double getPrice() { return this.drinkPrice; }
}
