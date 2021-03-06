package Mappers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DessertOrderDataMapper{
    Connection conn;

    public DessertOrderDataMapper(Connection conn) {
        this.conn = conn;
    }

    public void insert(int orderId, int dessertId) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO dessertOrders (orderId, dessertId) VALUES (?, ?);");
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, dessertId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int orderId) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM dessertOrders WHERE orderId = ?;");
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
