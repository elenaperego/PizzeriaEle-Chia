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
    private final double price;
    PizzaToppingDataMapper toppingsMapper = new PizzaToppingDataMapper(conn);
    private ArrayList<PizzaTopping> allToppings = toppingsMapper.getPizzaToppings();

    public Pizza(int id, String name, boolean vegetarian) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.id = id;
        this.name = name;
        this.vegetarian = vegetarian;
        this.price = calculatePrice();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() {
        return price;
    }

    public double calculatePrice(){
        double amount = 0;
        for (PizzaTopping t : allToppings) {
            if (t.getPizzaId() == this.id) {
                amount += t.getPrice();
            }
        }
        amount *= 1.9;          // Here the 9 % VAT is added
        amount = Math.round(amount);
        //System.out.println(name + ": "+amount);
        return amount;
    }

    public boolean isVegeterian() { return vegetarian; }
}