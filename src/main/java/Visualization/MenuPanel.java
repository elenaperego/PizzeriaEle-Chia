package Visualization;

import Classes.Dessert.Dessert;
import Classes.Drink.Drink;
import Classes.MenuItem;
import Classes.Pizza.Pizza;
import Mappers.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

public class MenuPanel {

    Connection conn = ConnectionImpl.getConnection();
    JPanel menuPanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(menuPanel);
    ArrayList<ObjectPanel> menu = new ArrayList();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    MenuPanel() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        menuPanel.setBackground(Color.LIGHT_GRAY);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        initializeMenu();
    }

    public void initializeMenu() {
        ArrayList<Pizza> pizzaMenu = new PizzaDataMapper(conn).getAllPizzas();
        ArrayList<Dessert> dessertMenu = new DessertDataMapper(conn).getAllDesserts();
        ArrayList<Drink> drinkMenu = new DrinkDataMapper(conn).getAllDrinks();

        // Loop through all the pizzas and add them to the main panel
        for (Pizza pizza : pizzaMenu) {
            addPanel(pizza);
        }

        // Loop through all the pizzas and add them to the main panel
        for (Dessert dessert : dessertMenu) {
            addPanel(dessert);
        }

        // Loop through all the pizzas and add them to the main panel
        for (Drink drink : drinkMenu) {
            addPanel(drink);
        }
    }

    public void addPanel(MenuItem o) {
        ObjectPanel panel = null;
        try {
            panel = new ObjectPanel(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        menuPanel.add(panel.getPanel());
        menuPanel.add(Box.createRigidArea(new Dimension(screenSize.width, 30)));
        menu.add(panel);
    }

    public ArrayList<ObjectPanel> getMenu() { return this.menu; }

    public ObjectPanel getObject(int i) { return this.menu.get(i); }

    public JScrollPane getMenuPanel() { return this.scrollPane; }
}
