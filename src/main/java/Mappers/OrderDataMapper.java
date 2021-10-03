package Mappers;

import Classes.Order.Order;
import Classes.Pizza.Pizza;

import java.sql.*;
import java.util.Optional;

public class OrderDataMapper implements DataMapper{

    Connection conn;

    public OrderDataMapper(Connection conn, boolean dropTable) {
        this.conn = conn;
        Statement stmt;
        try{
            stmt = conn.createStatement();

            if(dropTable)
                stmt.executeUpdate("DROP TABLE IF EXISTS orders");

            stmt.executeUpdate("CREATE TABLE orders ("
                    + "orderId INT NOT NULL AUTO_INCREMENT, "
                    + "orderStatus VARCHAR(64), "
                    + "customerId INT, "
                    + "codeId INT, "
                    + "estimatedDeliveryTime DATE, "
                    + "totalPrice INT, "
                    + "PRIMARY KEY (orderId))");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional find(int id) {
        Order o = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT customerId, orderStatus, codeId, estimatedDeliveryTime, totalPrice FROM orders WHERE orderId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                o = new Order((int) id, rs.getInt(0), rs.getString(1), rs.getInt(2), rs.getDate(3), rs.getDouble(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(o);
    }

    @Override
    public void insert(Object object) {
        try{
            Order orderToBeInserted = (Order) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO orders (customerId, orderStatus, codeId, estimatedDeliveryTime, totalPrice) VALUES (?, ?, ?, ?, ?);");
            pstmt.setInt(1, orderToBeInserted.getCustomerId());
            pstmt.setString(2, orderToBeInserted.getStatus());
            pstmt.setInt(3, orderToBeInserted.getCodeId());
            pstmt.setDate(4, new java.sql.Date(orderToBeInserted.getEstimatedDeliveryTime().getTime()));
            pstmt.setDouble(5, orderToBeInserted.getTotalPrice());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {
        try{
            Order orderToBeUpdated = (Order) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE orders SET customerId = ?, orderStatus = ?, codeId = ?, estimatedDeliveryTime = ?, totalPrice = ? WHERE orderId = ?;");
            pstmt.setInt(1, orderToBeUpdated.getCustomerId());
            pstmt.setString(2, orderToBeUpdated.getStatus());
            pstmt.setInt(3, orderToBeUpdated.getCodeId());
            pstmt.setDate(4, (Date) orderToBeUpdated.getEstimatedDeliveryTime());
            pstmt.setDouble(5, orderToBeUpdated.getTotalPrice());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) {
        try{
            Order orderToBeDeleted = (Order) object;
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM orders WHERE orderId = ?;");
            pstmt.setInt(1, orderToBeDeleted.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

