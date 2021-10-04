package Mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PizzaOrderDataMapper {
    Connection conn;

    public PizzaOrderDataMapper(Connection conn, boolean dropTable) {
        this.conn = conn;
        Statement stmt;

        try{
            stmt = conn.createStatement();

            if(dropTable)
                stmt.executeUpdate("DROP TABLE IF EXISTS pizzaOrders");

            stmt.executeUpdate("CREATE TABLE pizzaOrders ("
                    + "orderId INT NOT NULL, "
                    + "pizzaId INT NOT NULL, "
                    + "PRIMARY KEY (orderId, pizzaId))");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(int orderId, int pizzaId) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO pizzaOrders (orderId, pizzaId) VALUES (?, ?);");
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, pizzaId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int orderId) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM pizzaOrders WHERE orderId = ?;");
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
