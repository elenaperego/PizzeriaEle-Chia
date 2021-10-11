package Mappers;
import Classes.Drink.Drink;

import java.sql.*;
import java.util.Optional;

public class DrinkOrderDataMapper {
    Connection conn;

    public DrinkOrderDataMapper(Connection conn) {
        this.conn = conn;
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
