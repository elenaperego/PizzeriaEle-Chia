package Mappers;

import Classes.Pizza.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class PizzaDataMapper implements DataMapper {

    Connection conn;
    public PizzaDataMapper(Connection conn){
        this.conn = conn;
    }
    @Override
    public Optional<Pizza> find(int id) {
        Pizza p = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT name, isVegeterian FROM pizzas WHERE pizzaId = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                p = new Pizza(id, rs.getString(1), rs.getBoolean(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(p);
    }

    @Override
    public void update(Object object) {
        try{
            Pizza pizzaToBeUpdated = (Pizza) object;
            PreparedStatement pstmt = conn.prepareStatement("UPDATE pizzas SET name = ?, isVegeterian = ? WHERE pizzaId = ?;");
            pstmt.setString(1, pizzaToBeUpdated.getName());
            pstmt.setBoolean(2, pizzaToBeUpdated.isVegeterian());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(Object object) {
        try{
            Pizza pizzaToBeInserted = (Pizza) object;
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO pizzas (name, isVegeterian) VALUES (?, ?);");
            pstmt.setString(1, pizzaToBeInserted.getName());
            pstmt.setBoolean(2, pizzaToBeInserted.isVegeterian());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Object object) {
        try{
            Pizza pizzaToBeDeleted = (Pizza) object;
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM pizzas WHERE pizzaId = ?;");
            pstmt.setLong(1, pizzaToBeDeleted.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Pizza> getAllPizzas(){
        ArrayList<Pizza> pizzasList = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pizzas;");

            while(rs.next()){
                pizzasList.add(new Pizza(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pizzasList;
    }
}
