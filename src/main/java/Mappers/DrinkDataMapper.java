package Mappers;


import Classes.Customer.Customer;
import Classes.Drink.Drink;
import Classes.Pizza.Pizza;

import java.sql.*;
import java.util.Optional;

public class DrinkDataMapper implements DataMapper{

    Connection conn;

    public DrinkDataMapper(Connection conn, boolean dropTable) {
            this.conn = conn;
            Statement stmt;

            try{
                stmt = conn.createStatement();

                if(dropTable)
                    stmt.executeUpdate("DROP TABLE IF EXISTS drinks");

                stmt.executeUpdate("CREATE TABLE drinks ("
                        + "drinkId INT NOT NULL AUTO_INCREMENT, "
                        + "name VARCHAR(64), "
                        + "price DOUBLE, "
                        + "PRIMARY KEY (drinkId))");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    @Override
    public Optional find(int id) {
        Drink d = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT name, price FROM drinks WHERE drinkId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                d = new Drink(id, rs.getString(0), rs.getDouble(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(d);
    }

    @Override
    public void insert(Object object) {
        try{
            Drink drinkToBeInserted = (Drink) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO drinks (name, price) VALUES (?, ?);");
            pstmt.setString(1, drinkToBeInserted.getName());
            pstmt.setDouble(2, drinkToBeInserted.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {
        try{
            Drink drinkToBeUpdated = (Drink) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE drinks SET name = ?, price = ? WHERE drinkId = ?;");
            pstmt.setString(1, drinkToBeUpdated.getName());
            pstmt.setDouble(2, drinkToBeUpdated.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) {
        try{
            Drink drinkToBeDeleted = (Drink) object;
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM drinks WHERE drinkId = ?;");
            pstmt.setInt(1, drinkToBeDeleted.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
