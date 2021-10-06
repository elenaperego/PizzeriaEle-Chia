package Mappers;

import Classes.Dessert.Dessert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DessertDataMapper implements DataMapper{

 Connection conn;

    public DessertDataMapper(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional find(int id) {
        Dessert d = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT name, price FROM desserts WHERE dessertId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                d = new Dessert(id, rs.getString(1), rs.getDouble(2));
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

    public ArrayList<Dessert> getAllDesserts(){
        ArrayList<Dessert> dessertsList = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM desserts;");

            while(rs.next()){
                dessertsList.add(new Dessert(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dessertsList;
    }
}
