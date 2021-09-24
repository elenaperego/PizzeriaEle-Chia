package Mappers;

import com.PizzaAPI.PizzaAPI.Pizza.Pizza;

import java.sql.Connection;

public class TempMain {
    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Connection conn = ConnectionImpl.getConnection();
        Pizza pizza = new Pizza(0, "margherita", true);
        PizzaDataMapper mapper = new PizzaDataMapperImpl(conn, true);
        mapper.delete(pizza);
        System.out.println("pizza :"+pizza+", is inserted");
    }
}
