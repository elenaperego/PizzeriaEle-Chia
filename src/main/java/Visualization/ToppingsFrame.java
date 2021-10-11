package Visualization;

import Classes.Pizza.Pizza;
import Classes.PizzaTopping.PizzaTopping;
import Mappers.ConnectionImpl;
import Mappers.PizzaToppingDataMapper;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

import static javax.swing.BoxLayout.Y_AXIS;

public class ToppingsFrame {

    Connection conn = ConnectionImpl.getConnection();
    Pizza pizza;
    ArrayList<PizzaTopping> allToppings = new PizzaToppingDataMapper(conn).getPizzaToppings();
    ImageIcon vegIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Veg.png"));
    ArrayList<PizzaTopping> toppings = new ArrayList<>();
    JFrame toppingsFrame = new JFrame();
    JPanel toppingsPanel = new JPanel();

    ToppingsFrame(Pizza pizza) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        this.pizza = pizza;

        getToppings();

        toppingsFrame.setLayout(new GridLayout(1, 2));
        toppingsFrame.setBackground(Color.ORANGE);
        toppingsFrame.setSize(300, 200);
        toppingsFrame.setTitle("Toppings");
        toppingsFrame.add(toppingsPanel);

        // Only if pizza is vegetarian add this icon!
        if (this.pizza.isVegeterian()) {
            toppingsFrame.add(new JLabel(vegIcon));
        }

        toppingsFrame.setLocationRelativeTo(null);
        toppingsFrame.setVisible(false);
    }

    /**
     * Add all the toppings to a frame bases on the pizza they belong to
     */
    public void getToppings() {
        toppingsPanel.setLayout(new BoxLayout(toppingsPanel, Y_AXIS));
        toppingsPanel.add(new JLabel("  Toppings: "));
        toppingsPanel.add(new JLabel(" "));

        for (int i = 0; i < allToppings.size(); i++) {
            PizzaTopping newTopping = allToppings.get(i);

            if (newTopping.getPizzaId() == this.pizza.getId()) {
                this.toppings.add(newTopping);
                JLabel toppingLabel = new JLabel("  " + newTopping.getName());
                toppingsPanel.add(toppingLabel);
            }
        }
    }

    public JFrame getFrame() { return this.toppingsFrame; }
}
