package Mappers;

import Classes.Customer.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TempMain {

    static AtomicInteger counter = new AtomicInteger();

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        Connection conn = ConnectionImpl.getConnection();
        Statement s = conn.createStatement();

        s.execute("CREATE TABLE customers ("
                + "customerId INT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(64), "
                + "phoneNumber BIGINT, "
                + "addressStreet VARCHAR(64), "
                + "addressCode INT, "
                + "orderedPizzas INT, "
                + "PRIMARY KEY (customerId))");
    }
}
