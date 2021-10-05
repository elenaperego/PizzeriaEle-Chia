package Classes.Dessert;

import Classes.MenuItem;

public class Dessert implements MenuItem {
    private final int dessertId;
    private final String dessertName;
    private final double dessertPrice;

    public Dessert(int id, String name, double price) {
        this.dessertId = id;
        this.dessertName = name;
        this.dessertPrice = price;
    }

    @Override
    public int getId() {
        return dessertId;
    }

    @Override
    public String getName() { return dessertName; }

    @Override
    public double getPrice() { return dessertPrice; }
}
