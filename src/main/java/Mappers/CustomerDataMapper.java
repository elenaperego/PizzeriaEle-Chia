package Mappers;

import Classes.Customer.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerDataMapper implements DataMapper{

    Connection conn;

    public CustomerDataMapper(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Customer> find(int id) {
        Customer c = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT name, phoneNumber, addressStreet, addressCode, orderedPizzas FROM customers WHERE customerId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                c = new Customer(id, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers (name, phoneNumber, addressStreet, addressCode, orderedPizzas) VALUES (?, ?, ?, ?, ?);");
            pstmt.setString(1, customerToBeInserted.getName());
            pstmt.setString(2, customerToBeInserted.getPhoneNumber());
            pstmt.setString(3, customerToBeInserted.getAddressStreet());
            pstmt.setString(4, customerToBeInserted.getAddressCode());
            pstmt.setInt(5, customerToBeInserted.getOrderedPizzas());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {
        try{
            Customer customerToBeUpdated = (Customer) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE customers SET name = ?, phoneNumber = ?, addressStreet = ?, addressCode = ?, orderedPizzas = ? WHERE customerId = ?;");
            pstmt.setString(1, customerToBeUpdated.getName());
            pstmt.setString(2, customerToBeUpdated.getPhoneNumber());
            pstmt.setString(3, customerToBeUpdated.getAddressStreet());
            pstmt.setString(4, customerToBeUpdated.getAddressCode());
            pstmt.setInt(5, customerToBeUpdated.getOrderedPizzas());
            pstmt.setInt(6, customerToBeUpdated.getId());
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

    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> customersList = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers;");

            while(rs.next()){
                customersList.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customersList;
    }
}
