package Mappers;

import Classes.Customer.Customer;
import Classes.DeliveryPerson.DeliveryPerson;
import Classes.Dessert.Dessert;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class TempMain {

    static AtomicInteger counter = new AtomicInteger();

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        Connection conn = ConnectionImpl.getConnection();
        DessertDataMapper mapper = new DessertDataMapper(conn);
        Dessert d1 = new Dessert(counter.getAndIncrement(), "tiramisù", 5);
        Dessert d2 = new Dessert(counter.getAndIncrement(), "cheesecake", 7);
        mapper.insert(d1);
        mapper.insert(d2);


    }
}
