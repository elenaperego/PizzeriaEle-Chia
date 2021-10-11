package Mappers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class TempMain {

    static AtomicInteger counter = new AtomicInteger();

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
        Connection conn = ConnectionImpl.getConnection();
        Statement s = conn.createStatement();

        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 1, isAvailable = 1 WHERE deliveryPersonId = 1;");
        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 1, isAvailable = 1 WHERE deliveryPersonId = 2;");
        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 1, isAvailable = 1 WHERE deliveryPersonId = 3;");
        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 2, isAvailable = 1 WHERE deliveryPersonId = 4;");
        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 2, isAvailable = 1 WHERE deliveryPersonId = 5;");
        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 2, isAvailable = 1 WHERE deliveryPersonId = 6;");
        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 3, isAvailable = 1 WHERE deliveryPersonId = 7;");
        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 3, isAvailable = 1 WHERE deliveryPersonId = 8;");
        s.execute("UPDATE deliveryPersons SET isGirl = 1, areaCode = 3, isAvailable = 1 WHERE deliveryPersonId = 9;");
    }
}
