package Mappers;

import Classes.Order.Order;
import Classes.Pizza.Pizza;

import java.sql.*;
import java.util.Optional;

public class OrderDataMapper implements DataMapper{

    Connection conn;

    public OrderDataMapper(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Order> find(int id) {
        Order o = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT orderStatus, customerId, codeId, estimatedDeliveryTime, totalPrice FROM orders WHERE orderId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                o = new Order(id, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5));
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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO orders (orderStatus, customerId, codeId, estimatedDeliveryTime, totalPrice) VALUES (?, ?, ?, ?, ?);");
            pstmt.setString(1, orderToBeInserted.getStatus());
            pstmt.setLong(2, orderToBeInserted.getCustomerId());
            pstmt.setString(3, orderToBeInserted.getCodeId());
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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE orders SET orderStatus = ?, customerId = ?, codeId = ?, estimatedDeliveryTime = ?, totalPrice = ? WHERE orderId = ?;");
            pstmt.setString(1, orderToBeUpdated.getStatus());
            pstmt.setLong(2, orderToBeUpdated.getCustomerId());
            pstmt.setString(3, orderToBeUpdated.getCodeId());
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
            pstmt.setLong(1, orderToBeDeleted.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
