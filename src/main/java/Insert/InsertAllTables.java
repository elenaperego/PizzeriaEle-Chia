package Insert;

import Mappers.ConnectionImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class InsertAllTables {
    static AtomicInteger counter = new AtomicInteger();
    Connection conn = ConnectionImpl.getConnection();

    public InsertAllTables() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }

    public void insert() throws SQLException {
        Statement s = conn.createStatement();
        s.execute("CREATE TABLE customers ("
                + "customerId INT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(64), "
                + "phoneNumber BIGINT, "
                + "addressStreet VARCHAR(64), "
                + "addressCode INT, "
                + "PRIMARY KEY (customerId))");
        s.execute("CREATE TABLE deliveryPersonOrders ("
                + "orderId INT NOT NULL, "
                + "deliveryPersonId INT NOT NULL, "
                + "PRIMARY KEY (orderId, deliveryPersonId))");
        s.execute("CREATE TABLE dessertOrders ("
                + "orderId INT NOT NULL, "
                + "dessertId INT NOT NULL, "
                + "PRIMARY KEY (orderId, dessertId))");
        s.execute("CREATE TABLE discountCodes ("
                + "discountCodeId INT NOT NULL AUTO_INCREMENT, "
                + "discountCode BIGINT, "
                + "isUsed TINYINT, "
                + "PRIMARY KEY (discountCodeId))");
        s.execute("CREATE TABLE drinkOrders ("
                + "orderId INT NOT NULL, "
                + "drinkId INT NOT NULL, "
                + "PRIMARY KEY (orderId, drinkId))");
        s.execute("CREATE TABLE orders ("
                + "orderId INT NOT NULL AUTO_INCREMENT, "
                + "orderStatus VARCHAR(64), "
                + "customerId TINYINT, "
                + "codeId INT, "
                + "estimatedDeliveryTime DATE, "
                + "totalPrice INT, "
                + "PRIMARY KEY (orderId))");
        s.execute("CREATE TABLE pizzaOrders ("
                + "orderId INT NOT NULL, "
                + "pizzaId INT NOT NULL, "
                + "PRIMARY KEY (orderId, pizzaId))");

    }
}
