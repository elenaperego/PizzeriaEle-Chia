package Mappers;

import Classes.Pizza.Pizza;
import Classes.PizzaTopping.PizzaTopping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzaToppingDataMapper implements DataMapper {

    Connection conn;

    public PizzaToppingDataMapper(Connection conn) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.conn = conn;

    }
    @Override
    public Optional<PizzaTopping> find(int id) {
        PizzaTopping p = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT pizzaId, name, price FROM pizzas WHERE pizzaToppingId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                p = new PizzaTopping(id, rs.getInt(1), rs.getString(2), rs.getDouble(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(p);
    }

    @Override
    public void update(Object object) {
        try{
            PizzaTopping pizzaToppingToBeUpdated = (PizzaTopping) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE pizzaToppings SET pizzaId = ?, name = ?, price = ? WHERE pizzaToppingId = ?;");
            pstmt.setInt(1, pizzaToppingToBeUpdated.getPizzaId());
            pstmt.setString(2, pizzaToppingToBeUpdated.getName());
            pstmt.setDouble(3, pizzaToppingToBeUpdated.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(Object object) {
        try{
            PizzaTopping pizzaToppingToBeInserted = (PizzaTopping) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO pizzaToppings (pizzaId, name, price) VALUES (?, ?, ?);");
            pstmt.setInt(1, pizzaToppingToBeInserted.getPizzaId());
            pstmt.setString(2, pizzaToppingToBeInserted.getName());
            pstmt.setDouble(3, pizzaToppingToBeInserted.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) {
        try{
            PizzaTopping pizzaToppingToBeDeleted = (PizzaTopping) object;
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM pizzaToppings WHERE pizzaToppingId = ?;");
            pstmt.setLong(1, pizzaToppingToBeDeleted.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList<PizzaTopping> getPizzaToppings(){
        ArrayList<PizzaTopping> pizzaToppingsList = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pizzaToppings;");

            while(rs.next()){
                pizzaToppingsList.add(new PizzaTopping(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pizzaToppingsList;
    }
}
