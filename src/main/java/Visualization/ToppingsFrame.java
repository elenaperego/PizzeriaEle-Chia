package Visualization;

import Classes.Pizza.Pizza;
import Classes.PizzaTopping.PizzaTopping;
import Mappers.PizzaToppingDataMapper;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

public class ToppingsFrame {

    Connection conn;
    Pizza pizza;
    ArrayList<PizzaTopping> allToppings = new PizzaToppingDataMapper(conn, true).getPizzaToppings();
    ImageIcon vegIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Veg.png"));
    ArrayList<PizzaTopping> toppings = new ArrayList<>();
    JFrame toppingsFrame = new JFrame();
    JLabel toppingsLabel = new JLabel();

    ToppingsFrame(Connection conn, Pizza pizza) {
        this.conn = conn;
        this.pizza = pizza;

        getToppings();

        toppingsFrame.setLayout(new GridLayout(1, 2));
        toppingsFrame.setBackground(Color.ORANGE);
        toppingsFrame.setSize(300, 200);
        toppingsFrame.setTitle("Toppings");
        toppingsFrame.add(toppingsLabel, 1);

        // Only if pizza is vegetarian add this icon!
        if (this.pizza.isVegeterian()) {
            toppingsFrame.add(new JLabel(vegIcon), 2);
        }

        toppingsFrame.setLocationRelativeTo(null);
        toppingsFrame.setVisible(false);
    }

    public void getToppings() {
        String toppingsString = "";

        for (int i = 0; i < allToppings.size(); i++) {
            PizzaTopping newTopping = allToppings.get(i);

            if (newTopping.getPizzaId() == this.pizza.getId()) {
                this.toppings.add(newTopping);
                toppingsString += newTopping.getName() + "\n";
            }
        }
        toppingsLabel.setText(toppingsString);
    }

    public JFrame getFrame() { return this.toppingsFrame; }
}
