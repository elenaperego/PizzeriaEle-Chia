package Mappers;

import Classes.Customer.Customer;
import Classes.Dessert.Dessert;
import Classes.Drink.Drink;
import Classes.Pizza.Pizza;

import java.sql.*;
import java.util.Optional;

public class DessertDataMapper implements DataMapper{

 Connection conn;

    public DessertDataMapper(Connection conn, boolean dropTable) {
        this.conn = conn;
       Statement stmt;

       try{
           stmt = conn.createStatement();

       if(dropTable)
        stmt.executeUpdate("DROP TABLE IF EXISTS desserts");

        stmt.executeUpdate("CREATE TABLE desserts ("
           + "dessertId INT NOT NULL AUTO_INCREMENT, "
           + "name VARCHAR(64), "
           + "price DOUBLE, "
           + "PRIMARY KEY (dessertId))");

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
    }

    @Override
    public Optional find(int id) {
        Dessert d = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT name, price FROM desserts WHERE dessertId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                d = new Dessert((int) id, rs.getString(0), rs.getDouble(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(d);
    }

    @Override
    public void insert(Object object) {
        try{
            Dessert dessertToBeInserted = (Dessert) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO desserts (name, price) VALUES (?, ?);");
            pstmt.setString(1, dessertToBeInserted.getName());
            pstmt.setDouble(2, dessertToBeInserted.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {
        try{
            Dessert dessertToBeUpdated = (Dessert) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE desserts SET name = ?, price = ? WHERE dessertId = ?;");
            pstmt.setString(1, dessertToBeUpdated.getName());
            pstmt.setDouble(2, dessertToBeUpdated.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) {
        try{
            Dessert dessertToBeDeleted = (Dessert) object;
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM desserts WHERE dessertId = ?;");
            pstmt.setInt(1, dessertToBeDeleted.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
