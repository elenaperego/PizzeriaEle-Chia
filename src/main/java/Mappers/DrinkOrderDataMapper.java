package Mappers;

import Classes.Drink.Drink;

import java.sql.*;
import java.util.Optional;

public class DrinkOrderDataMapper {
    Connection conn;

    public DrinkOrderDataMapper(Connection conn, boolean exists) {
        this.conn = conn;
        Statement stmt;

        try{
            stmt = conn.createStatement();

            if(!exists) {

                stmt.executeUpdate("CREATE TABLE drinkOrders ("
                        + "orderId INT NOT NULL, "
                        + "drinkId INT NOT NULL, "
                        + "PRIMARY KEY (orderId, drinkId))");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(int orderId, int drinkId) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO drinkOrders (orderId, drinkId) VALUES (?, ?);");
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, drinkId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int orderId) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM drinkOrders WHERE orderId = ?;");
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
