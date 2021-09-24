package Mappers;

import com.PizzaAPI.PizzaAPI.Pizza.Pizza;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzaDataMapperImpl implements PizzaDataMapper {

    Connection conn;
    public PizzaDataMapperImpl(Connection conn, boolean dropTable){
        this.conn = conn;
        Statement stmt;
        try{
            stmt = conn.createStatement();

            if(dropTable)
                stmt.executeUpdate("DROP TABLE IF EXISTS pizzas");

            stmt.executeUpdate("CREATE TABLE pizzas ("
                + "pizzaId INT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(64), isVegeterian TINYINT, PRIMARY KEY (pizzaID))");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public Optional<Pizza> find(int pizzaId) {
        Pizza p = null;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT name, isVegeterian FROM pizzas WHERE pizzaID = ?");
            pstmt.setInt(1, pizzaId);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                p = new Pizza(pizzaId, rs.getString(0), rs.getBoolean(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(p);
    }

    @Override
    public void update(Pizza pizzaToBeUpdated) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("UPDATE pizzas SET name = ?, isVegeterian = ? WHERE pizzaId = ?;");
            pstmt.setString(1, pizzaToBeUpdated.getName());
            pstmt.setBoolean(2, pizzaToBeUpdated.isVegeterian());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(Pizza pizzaToBeInserted) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO pizzas (name, isVegeterian) VALUES (?, ?);");
            pstmt.setString(1, pizzaToBeInserted.getName());
            pstmt.setBoolean(2, pizzaToBeInserted.isVegeterian());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Pizza pizzaToBeDeleted) {
        try{
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM pizzas WHERE pizzaId = ?;");
            //pstmt.set ??
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Pizza> getPizzas(){
        List<Pizza> pizzasList = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pizzas;");

            while(rs.next()){
                pizzasList.add(new Pizza(rs.getInt(0), rs.getString(1), rs.getBoolean(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pizzasList;
    }
}
