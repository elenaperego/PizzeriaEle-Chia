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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TempMain {

    static AtomicInteger counter = new AtomicInteger();

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        Connection conn = ConnectionImpl.getConnection();
        DessertDataMapper mapper = new DessertDataMapper(conn, false);
        Dessert d1 = new Dessert(counter.getAndIncrement(), "tiramisù", 5);
        Dessert d2 = new Dessert(counter.getAndIncrement(), "cheesecake", 7);
        mapper.insert(d1);
        mapper.insert(d2);


    }
}
