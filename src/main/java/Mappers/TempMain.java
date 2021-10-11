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

        s.execute("CREATE TABLE orders ("
                + "orderId INT NOT NULL AUTO_INCREMENT, "
                + "orderStatus VARCHAR(64), "
                + "customerId TINYINT, "
                + "codeId VARCHAR(64), "
                + "estimatedDeliveryTime DATE, "
                + "totalPrice INT, "
                + "PRIMARY KEY (orderId))");
    }
}
