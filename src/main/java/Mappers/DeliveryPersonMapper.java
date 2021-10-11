package Mappers;

import Classes.DeliveryPerson.DeliveryPerson;
import Classes.PizzaTopping.PizzaTopping;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DeliveryPersonMapper implements DataMapper{
    Connection conn;
    public DeliveryPersonMapper(Connection conn){
        this.conn = conn;
    }
    @Override
    public Optional<DeliveryPerson> find(int id) {
        DeliveryPerson d = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT isGirl, areaCode, isAvailable FROM deliveryPersons WHERE deliveryPersonId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                d = new DeliveryPerson(id, rs.getBoolean(1), rs.getInt(2), rs.getBoolean(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(d);
    }

    @Override
    public void update(Object object) {
        try{
            DeliveryPerson deliveryPersonToBeUpdated = (DeliveryPerson) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE deliveryPersons SET isGirl = ?, areaCode = ?, isAvailable = ? WHERE deliveryPersonId = ?;");
            pstmt.setBoolean(1, deliveryPersonToBeUpdated.isGirl());
            pstmt.setInt(2, deliveryPersonToBeUpdated.getAreaCode());
            pstmt.setBoolean(3, deliveryPersonToBeUpdated.isAvailable());
            pstmt.setInt(4, deliveryPersonToBeUpdated.getDeliveryPersonId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(Object object) {
        try{
            DeliveryPerson deliveryPersonToBeInserted = (DeliveryPerson) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO deliveryPersons (isGirl, areaCode, isAvailable) VALUES (?, ?, ?);");
            pstmt.setBoolean(1, deliveryPersonToBeInserted.isGirl());
            pstmt.setInt(2, deliveryPersonToBeInserted.getAreaCode());
            pstmt.setBoolean(3, deliveryPersonToBeInserted.isAvailable());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) {
        try{
            DeliveryPerson deliveryPersonToBeDeleted = (DeliveryPerson) object;
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM deliveryPersons WHERE deliveryPersonId = ?;");
            pstmt.setInt(1, deliveryPersonToBeDeleted.getDeliveryPersonId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<DeliveryPerson> getDeliveryPersons(){
        ArrayList<DeliveryPerson> deliveryPeopleList = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM deliveryPersons;");

            while(rs.next()){
                deliveryPeopleList.add(new DeliveryPerson(rs.getInt(1), rs.getBoolean(2), rs.getInt(3), rs.getBoolean(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deliveryPeopleList;
    }


}
