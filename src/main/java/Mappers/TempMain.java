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
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TempMain {

    static AtomicInteger counter =new AtomicInteger();;

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Connection conn = ConnectionImpl.getConnection();
        DataMapper mapper = new DiscountCodeDataMapper(conn, true);
        DiscountCode c = new DiscountCode(counter.getAndIncrement(), true);
        mapper.insert(c);


    }
}
