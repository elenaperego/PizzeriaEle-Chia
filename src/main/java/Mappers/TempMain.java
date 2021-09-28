package Mappers;

import Classes.Pizza.Pizza;

import java.sql.Connection;

public class TempMain {
    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Connection conn = ConnectionImpl.getConnection();
        Pizza pizza = new Pizza(0, "margherita", true);
        DataMapper mapper = new PizzaDataMapper(conn, true);
        mapper.insert(pizza);
        System.out.println("pizza :"+pizza+", is inserted");
    }
}
