package Visualization;

import Classes.DiscountCode.DiscountCode;
import Classes.MenuItem;
import Classes.Order.Order;
import Classes.Pizza.Pizza;
import Mappers.ConnectionImpl;
import Mappers.DiscountCodeDataMapper;
import Mappers.OrderDataMapper;
import Mappers.PizzaDataMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

public class OrderFrame implements ActionListener {

    Connection conn = ConnectionImpl.getConnection();

    Order newOrder = null;
    double finalPrice = 0;
    long codeId = 0;
    int orderCount = 0;        // Should these two counts remain updated when the application closes or
    int customerCount = 0;     // is it possible to restart them whenever we launch the application again?
    Date estimatedDeliveryTime = null;

    JFrame orderFrame = new JFrame();
    JPanel orderPanel = new JPanel();
    JLabel orderLabel = new JLabel("ORDER NUMBER: " + orderCount);
    JLabel orderSummaryLabel = new JLabel("      ORDER SUMMARY");
    JComboBox<Object> summary = new JComboBox<>();
    JLabel priceLabel = new JLabel(" PRICE: ");
    JLabel numberPriceLabel = new JLabel();
    JLabel codeLabel = new JLabel("     INSERT CODE HERE");
    JTextField codeBox = new JTextField();
    JButton codeButton = new JButton(" CONFIRM CODE");
    JButton confirmButton = new JButton("CONFIRM ORDER");
    ArrayList<MenuItem> orderSummary;

    public OrderFrame() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        orderFrame.setBackground(Color.GREEN);
        orderFrame.setSize(500, 500);
        orderFrame.setTitle("Order");
        orderFrame.setLocationRelativeTo(null);
        orderFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addPanel();
        orderFrame.setVisible(false);
    }

    public void addPanel() {
        orderPanel.setSize(500, 500);
        orderPanel.setLayout(new GridLayout(6, 2));

        orderLabel.setFont(new Font("Serif", Font.BOLD, 17));
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderSummaryLabel.setFont(new Font("Serif", Font.BOLD, 17));
        codeLabel.setFont(new Font("Serif", Font.BOLD, 17));
        codeBox.setPreferredSize(new Dimension(200, 70));
        confirmButton.setPreferredSize(new Dimension(100, 70));

        //getFinalPrice(orderSummary);
        numberPriceLabel.setText("" + finalPrice);

        orderPanel.add(orderLabel);
        orderPanel.add(orderSummaryLabel);
        orderPanel.add(summary);
        orderPanel.add(priceLabel);
        orderPanel.add(numberPriceLabel);
        orderPanel.add(codeLabel);
        orderPanel.add(codeBox);
        orderPanel.add(codeButton);
        orderPanel.add(confirmButton);

        this.orderFrame.add(orderPanel);
    }

    public JFrame getErrorFrame() {
        JFrame errorFrame = new JFrame();
        JLabel errorLabel = new JLabel(" CODE NOT ACCEPTED!!!!!!!!");

        errorFrame.setBackground(Color.RED);
        errorFrame.setSize(100, 50);
        errorFrame.setTitle("Error");
        errorFrame.add(errorLabel);
        errorFrame.setLocationRelativeTo(null);
        errorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        errorFrame.setVisible(false);
        return errorFrame;
    }

    public void getFinalPrice(ArrayList<MenuItem> summary) {
        for (int i = 0; i < summary.size(); i++) {
            finalPrice += summary.get(i).getPrice();
        }
        finalPrice *= 1.9; // Here the 9 % VAT is added
        if(checkNumberofPizzas(summary)){
            finalPrice = finalPrice - (finalPrice*0.1);
        }
    }

    public boolean checkNumberofPizzas(ArrayList<MenuItem> summary){
        PizzaDataMapper mapper = new PizzaDataMapper(conn, true);
        ArrayList<Pizza> pizzas = mapper.getAllPizzas();
        int count = 0;
        for(MenuItem item: summary){
            for(Pizza pizza: pizzas){
                if(item.getName() == pizza.getName()){
                    count++;
                }
            }
        }
        if(count >= 10)
            return true;
        else
            return false;
    }

    public void getEstimatedDeliveryTime() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MenuPanel menu = null;
        try {
            menu = new MenuPanel();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        // Add pizzas to order summary if selected in the menu panel
        // Sum price of all the objects and include profits??????
        if (e.getSource() == summary) {
            orderSummary = new ArrayList<>();
            for (int i = 0; i < menu.menu.size(); i++) {
                if (menu.getObject(i).getCheckBox().isSelected()) {
                    summary.addItem(menu.getObject(i).getObject());
                    orderSummary.add(menu.getObject(i).getObject());
                }
            }
            getFinalPrice(orderSummary);

        // Compare the code inserted by the user with the ones already in the database
        } else if (e.getSource() == codeBox) {
            DiscountCodeDataMapper codeMapper = new DiscountCodeDataMapper(conn, true);
            Optional<DiscountCode> discountCode = codeMapper.find(Long.parseLong(codeBox.getText()));
            if(discountCode.isPresent()){
                if(discountCode.get().isUsed()){
                    System.out.println("already used");
                } else {
                    System.out.println("not used yet");
                    discountCode.get().setUsed(false);
                    codeMapper.update(discountCode.get());
                }
            } else {
                System.out.println("new discount code");
                codeMapper.insert(new DiscountCode(0, Long.parseLong(codeBox.getText()), true));
                // id = 0 perchè tanto il metodo neanche lo guarda perchè lo fa da solo
            }

        // Add all the elements to the new order and close the current frame
        } else if (e.getSource() == confirmButton) {
            newOrder = new Order(orderCount++, customerCount++, "ordered", codeId, finalPrice, estimatedDeliveryTime);
            OrderDataMapper orderMapper = new OrderDataMapper(conn, true);
            orderMapper.insert(newOrder);
            orderFrame.dispose();
        }
    }

    public Order getNewOrder() { return newOrder; }

    public JFrame getFrame() { return this.orderFrame; }
}
