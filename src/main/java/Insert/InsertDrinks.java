package Insert;

import Classes.DeliveryPerson.DeliveryPerson;
import Classes.Drink.Drink;
import Mappers.ConnectionImpl;
import Mappers.DataMapper;
import Mappers.DeliveryPersonMapper;
import Mappers.DrinkDataMapper;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicInteger;

public class InsertDrinks {
    static AtomicInteger counter = new AtomicInteger();
    Connection conn = ConnectionImpl.getConnection();

    public InsertDrinks() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    public void insert() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        DataMapper mapper = new DrinkDataMapper(conn);

        Drink d1 = new Drink(counter.getAndIncrement(), "water", 1);
        Drink d2 = new Drink(counter.getAndIncrement(), "wine", 5);
        Drink d3 = new Drink(counter.getAndIncrement(), "beer", 3);
        Drink d4 = new Drink(counter.getAndIncrement(), "coke", 2);

        mapper.insert(d1);
        mapper.insert(d2);
        mapper.insert(d3);
        mapper.insert(d4);
    }
}
