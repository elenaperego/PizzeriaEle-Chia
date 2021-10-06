package Insert;

import Classes.DeliveryPerson.DeliveryPerson;
import Classes.PizzaTopping.PizzaTopping;
import Mappers.ConnectionImpl;
import Mappers.DataMapper;
import Mappers.DeliveryPersonMapper;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicInteger;

public class InsertDeliveryPersons {
    static AtomicInteger counter = new AtomicInteger();
    Connection conn = ConnectionImpl.getConnection();

    public InsertDeliveryPersons() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    public void insert() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        DataMapper mapper = new DeliveryPersonMapper(conn);

        DeliveryPerson d1 = new DeliveryPerson(counter.getAndIncrement(), true, 1, true);
        DeliveryPerson d2 = new DeliveryPerson(counter.getAndIncrement(), true, 1, true);
        DeliveryPerson d3 = new DeliveryPerson(counter.getAndIncrement(), true, 1, true);
        DeliveryPerson d4 = new DeliveryPerson(counter.getAndIncrement(), true, 2, true);
        DeliveryPerson d5 = new DeliveryPerson(counter.getAndIncrement(), true, 2, true);
        DeliveryPerson d6 = new DeliveryPerson(counter.getAndIncrement(), true, 2, true);
        DeliveryPerson d7 = new DeliveryPerson(counter.getAndIncrement(), true, 3, true);
        DeliveryPerson d8 = new DeliveryPerson(counter.getAndIncrement(), true, 3, true);
        DeliveryPerson d9 = new DeliveryPerson(counter.getAndIncrement(), true, 3, true);

        mapper.insert(d1);
        mapper.insert(d2);
        mapper.insert(d3);
        mapper.insert(d4);
        mapper.insert(d5);
        mapper.insert(d6);
        mapper.insert(d7);
        mapper.insert(d8);
        mapper.insert(d9);
    }
}
