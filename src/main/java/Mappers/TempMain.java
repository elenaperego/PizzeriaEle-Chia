package Mappers;

import Classes.Customer.Customer;
import Classes.DeliveryPerson.DeliveryPerson;
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
        DataMapper mapper = new DeliveryPersonMapper(conn, true);
        DeliveryPerson c = new DeliveryPerson(counter.getAndIncrement(), true, 6211);
        mapper.insert(c);


    }
}
