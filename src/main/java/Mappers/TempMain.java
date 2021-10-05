package Mappers;

import Classes.Customer.Customer;
import Classes.DeliveryPerson.DeliveryPerson;
import Classes.Dessert.Dessert;
import Classes.DiscountCode.DiscountCode;
import Classes.Drink.Drink;
import Classes.Order.Order;
import Classes.Pizza.Pizza;
import Classes.PizzaTopping.PizzaTopping;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TempMain {

    static AtomicInteger counter =new AtomicInteger();;

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        Connection conn = ConnectionImpl.getConnection();
        PizzaToppingDataMapper mapper = new PizzaToppingDataMapper(conn, true);
        PizzaDataMapper mapper1 = new PizzaDataMapper(conn, true);
        PizzaTopping p = new PizzaTopping(counter.getAndIncrement(), 0,"pomodoro", 2);
        //mapper.insert(p);
        mapper1.delete(new Pizza(3, "margherita", true));
        ArrayList<PizzaTopping> pizzas = mapper.getPizzaToppings();

        for (PizzaTopping p1: pizzas) {
            System.out.println(p1.getName());
        }
    }
}
