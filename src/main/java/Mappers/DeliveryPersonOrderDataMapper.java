package Mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeliveryPersonOrderDataMapper {
    Connection conn;

    public DeliveryPersonOrderDataMapper(Connection conn, boolean exists) {
        this.conn = conn;
        Statement stmt;

        try{
            stmt = conn.createStatement();

            if(!exists) {

                stmt.executeUpdate("CREATE TABLE deliveryPersonOrders ("
                        + "orderId INT NOT NULL, "
                        + "deliveryPersonId INT NOT NULL, "
                        + "PRIMARY KEY (orderId, deliveryPersonId))");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(int orderId, int deliveryPersonId) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO deliveryPersonOrders (orderId, deliveryPersonId) VALUES (?, ?);");
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, deliveryPersonId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int orderId) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM deliveryPersonOrders WHERE orderId = ?;");
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
