package Mappers;

import Classes.DeliveryPerson.DeliveryPerson;

import java.sql.*;
import java.util.Optional;

public class DeliveryPersonMapper implements DataMapper{
    Connection conn;
    public DeliveryPersonMapper(Connection conn, boolean exists){
        this.conn = conn;
        Statement stmt;
        try{
            stmt = conn.createStatement();

            if(!exists) {

                stmt.executeUpdate("CREATE TABLE deliveryPersons ("
                        + "deliveryPersonId INT NOT NULL AUTO_INCREMENT, "
                        + "isGirl TINYINT, "
                        + "areaCode INT, PRIMARY KEY (deliveryPersonId))");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public Optional<DeliveryPerson> find(int id) {
        DeliveryPerson d = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT isGirl, areaCode FROM deliveryPersons WHERE deliveryPersonId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                d = new DeliveryPerson(id, rs.getBoolean(1), rs.getInt(2));
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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE deliveryPersons SET isGirl = ?, areaCode = ? WHERE deliveryPersonId = ?;");
            pstmt.setBoolean(1, deliveryPersonToBeUpdated.isGirl());
            pstmt.setInt(2, deliveryPersonToBeUpdated.getAreaCode());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(Object object) {
        try{
            DeliveryPerson deliveryPersonToBeInserted = (DeliveryPerson) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO deliveryPersons (isGirl, areaCode) VALUES (?, ?);");
            pstmt.setBoolean(1, deliveryPersonToBeInserted.isGirl());
            pstmt.setInt(2, deliveryPersonToBeInserted.getAreaCode());
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

}
