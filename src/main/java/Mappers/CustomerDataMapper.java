package Mappers;

import Classes.Customer.Customer;
import Classes.Pizza.Pizza;

import java.sql.*;
import java.util.Optional;

public class CustomerDataMapper implements DataMapper{

    Connection conn;

    public CustomerDataMapper(Connection conn, boolean dropTable) {
        this.conn = conn;
            Statement stmt;
            try{
                stmt = conn.createStatement();

                if(dropTable)
                    stmt.executeUpdate("DROP TABLE IF EXISTS customers");

                stmt.executeUpdate("CREATE TABLE customers ("
                        + "customerId INT NOT NULL AUTO_INCREMENT, "
                        + "name VARCHAR(64), "
                        + "phoneNumber BIGINT, "
                        + "addressStreet VARCHAR(64), "
                        + "addressCode INT, "
                        + "PRIMARY KEY (customerId))");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    @Override
    public Optional find(int id) {
        Customer c = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT name, phoneNumber, addressStreet, addressCode FROM customers WHERE customerId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                c = new Customer((int) id, rs.getString(0), rs.getString(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(c);
    }

    @Override
    public void insert(Object object) {
        try{
            Customer customerToBeInserted = (Customer) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers (name, phoneNumber, addressStreet, addressCode) VALUES (?, ?, ?, ?);");
            pstmt.setString(1, customerToBeInserted.getName());
            pstmt.setString(2, customerToBeInserted.getPhoneNumber());
            pstmt.setString(3, customerToBeInserted.getAddressStreet());
            pstmt.setInt(4, customerToBeInserted.getAddressCode());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {
        try{
            Customer customerToBeUpdated = (Customer) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE customers SET name = ?, phoneNumber = ?, addressStreet = ?, addressCode = ? WHERE customerId = ?;");
            pstmt.setString(1, customerToBeUpdated.getName());
            pstmt.setString(2, customerToBeUpdated.getPhoneNumber());
            pstmt.setString(3, customerToBeUpdated.getAddressStreet());
            pstmt.setInt(4, customerToBeUpdated.getAddressCode());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) {
        try{
            Customer customerToBeDeleted = (Customer) object;
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM customers WHERE customerId = ?;");
            pstmt.setInt(1, customerToBeDeleted.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
