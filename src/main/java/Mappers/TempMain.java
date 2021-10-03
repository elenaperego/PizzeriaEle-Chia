package Mappers;

import Classes.Customer.Customer;
import Classes.Dessert.Dessert;
import Classes.Drink.Drink;
import Classes.Order.Order;
import Classes.Pizza.Pizza;
import Classes.PizzaTopping.PizzaTopping;

import java.sql.Connection;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TempMain {

    static AtomicInteger counter =new AtomicInteger();;

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Connection conn = ConnectionImpl.getConnection();
        DataMapper mapper = new CustomerDataMapper(conn, true);
        DataMapper mapper1 = new DessertDataMapper(conn, true);
        DataMapper mapper2 = new DrinkDataMapper(conn, true);
        DataMapper mapper3 = new OrderDataMapper(conn, true);
        Customer c = new Customer(counter.getAndIncrement(), "ele", "3459906734", "Mariastraat", 6211);
        Dessert d = new Dessert(counter.getAndIncrement(), "brownie", 4.0);
        Drink d1 = new Drink(counter.getAndIncrement(), "water", 1.0);
        Order o = new Order(counter.getAndIncrement(), 1, "done", 99, new Date(), 10);
        mapper.insert(c);
        mapper1.insert(d);
        mapper2.insert(d1);
        mapper3.insert(o);


    }
}
