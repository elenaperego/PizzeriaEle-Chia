package Classes.Pizza;

import Classes.MenuItem;
import Classes.PizzaTopping.PizzaTopping;
import Mappers.ConnectionImpl;
import Mappers.PizzaToppingDataMapper;

import java.sql.Connection;
import java.util.ArrayList;

public class Pizza implements MenuItem {

    Connection conn = ConnectionImpl.getConnection();

    private final int id;
    private final String name;
    private final boolean vegetarian;
    private double price = 0;
    PizzaToppingDataMapper toppingsMapper = new PizzaToppingDataMapper(conn);
    private ArrayList<PizzaTopping> allToppings = toppingsMapper.getPizzaToppings();

    public Pizza(int id, String name, boolean vegetarian) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.id = id;
        this.name = name;
        this.vegetarian = vegetarian;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() {
        for (PizzaTopping t : allToppings) {
            if (t.getPizzaId() == this.id) {
                price += t.getPrice();
            }
        }
        return price;
    }

    public boolean isVegeterian() { return vegetarian; }
}