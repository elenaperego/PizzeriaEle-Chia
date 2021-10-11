package Visualization;

import Classes.Customer.Customer;
import Classes.DiscountCode.DiscountCode;
import Classes.MenuItem;
import Classes.Order.Order;
import Classes.Pizza.Pizza;
import Mappers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class OrderFrame implements ActionListener {

    Connection conn = ConnectionImpl.getConnection();
    Order newOrder = null;
    double finalPrice = 0;
    long codeId = 0;
    int orderCount = 0;        // Should these two counts remain updated when the application closes or
    int customerCount = 0;     // is it possible to restart them whenever we launch the application again?
    double profit = 0;

    CustomerFrame customerFrame;
    JFrame orderFrame = new JFrame();
    JPanel orderPanel = new JPanel();
    JLabel orderLabel = new JLabel("ORDER DETAILS:");
    JLabel orderSummaryLabel = new JLabel("      ORDER SUMMARY");
    JComboBox<Object> summary = new JComboBox<>();
    JButton priceButton = new JButton();
    JLabel numberPriceLabel = new JLabel();
    JLabel codeLabel = new JLabel("      INSERT CODE HERE");
    JTextField codeBox = new JTextField();
    JButton codeButton = new JButton(" CONFIRM CODE");
    JButton confirmButton = new JButton();
    ImageIcon priceIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Euro.png"));
    ImageIcon nextIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/NextIcon.png"));
    ArrayList<MenuItem> orderSummary;
    java.util.Date orderSubmittedTime;

    public OrderFrame(CustomerFrame customerFrame) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.customerFrame = customerFrame;

        orderFrame.setBackground(Color.GREEN);
        orderFrame.setSize(600, 600);
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
        //priceLabel.setFont(new Font("Serif", Font.BOLD, 17));
        numberPriceLabel.setFont(new Font("Serif", Font.BOLD, 17));
        codeLabel.setFont(new Font("Serif", Font.BOLD, 17));
        codeBox.setPreferredSize(new Dimension(200, 70));
        confirmButton.setPreferredSize(new Dimension(100, 70));

        priceButton.setIcon(priceIcon);
        priceButton.setBackground(Color.LIGHT_GRAY);
        priceButton.setBorderPainted(false);

        confirmButton.setIcon(nextIcon);
        confirmButton.setBackground(Color.LIGHT_GRAY);
        confirmButton.setBorderPainted(false);

        priceButton.addActionListener(this);
        summary.addActionListener(this);
        codeBox.addActionListener(this);
        codeButton.addActionListener(this);
        confirmButton.addActionListener(this);

        //getFinalPrice(orderSummary);
        numberPriceLabel.setText("" + finalPrice);

        orderPanel.add(orderLabel);
        orderPanel.add(new Label());
        orderPanel.add(orderSummaryLabel);
        orderPanel.add(summary);
        orderPanel.add(priceButton);
        orderPanel.add(numberPriceLabel);
        orderPanel.add(codeLabel);
        orderPanel.add(new Label());
        orderPanel.add(codeBox);
        orderPanel.add(codeButton);
        orderPanel.add(new Label());
        orderPanel.add(confirmButton);

        this.orderFrame.add(orderPanel);
    }

    /**
     * This method calculates the final price of the order based on the order summary (including the profit and the 9% VAT)
     * @param summary is the order summary
     */
    public void getFinalPrice(ArrayList<MenuItem> summary) {
        for (MenuItem menuItem : summary) {
            finalPrice += menuItem.getPrice();
        }
        finalPrice *= 1.9;          // Here the 9 % VAT is added
        finalPrice += profit;       // Here the profit is added
    }

    /**
     * CHECK IF IT WORKS
     * This method checks how many pizzas are ordered
     * @param summary is the order summary
     * @return number of pizzas in this order
     */
    public int checkNumberofPizzas(ArrayList<MenuItem> summary){
        PizzaDataMapper mapper = new PizzaDataMapper(conn);
        ArrayList<Pizza> pizzas = mapper.getAllPizzas();
        int count = 0;
        for(MenuItem item: summary){
            for(Pizza pizza: pizzas){
                if(item.getName().equals(pizza.getName())){
                    profit += pizza.getPrice() * 0.40;
                    count++;
                }
            }
        }
        return count;
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
                    summary.addItem(menu.getObject(i).getObject().getName());
                    orderSummary.add(menu.getObject(i).getObject());
                }
            }
            getFinalPrice(orderSummary);    // This is the price without the discount applied

            int pizzaCount = checkNumberofPizzas(orderSummary);     // Number of pizzas ordered by customer

            // Update number of pizzas ordered by customer and add it back to the database
            CustomerDataMapper mapper = new CustomerDataMapper(conn);
            Customer customer = customerFrame.getCustomer();
            Customer updatedCustomer = new Customer(customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getAddressStreet(), customer.getAddressCode(), customer.getOrderedPizzas() + pizzaCount);
            mapper.update(updatedCustomer);

            // If the number of ordered pizzas is a multiple of 10 a code is given
            if (updatedCustomer.getOrderedPizzas() % 10 == 0) {
                DiscountCodeDataMapper codeMapper = new DiscountCodeDataMapper(conn);
                // create new code here!

                JOptionPane.showMessageDialog(null, "Congratulation, this is your discount code: ");
            }

        // Compare the code inserted by the user with the ones already in the database
        } else if (e.getSource() == codeButton) {
            DiscountCodeDataMapper codeMapper = new DiscountCodeDataMapper(conn);
            Optional<DiscountCode> discountCode = codeMapper.find(Long.parseLong(codeBox.getText()));
            if(discountCode.isPresent()){
                if(discountCode.get().isUsed()){
                    System.out.println("already used");
                    JOptionPane.showMessageDialog(null, "Error: code already used!");

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

        // Calculate final price and apply discount if code is valid
        } else if (e.getSource() == priceButton) {
            // Apply discount if the customer has a valid code
            if(true){
                finalPrice = finalPrice - (finalPrice*0.1);     // 10% discount
            }


        // Add all the elements to the new order and insert it to the database
        // Close the current frame and open the status one
        } else if (e.getSource() == confirmButton) {

            // CHECK WHETHER THE FOLLOWING METHOD WORKS
            if (atLeastOnePizza(orderSummary)) {
                newOrder = new Order(orderCount++, customerCount++, "ordered", codeId, finalPrice, null);
                OrderDataMapper orderMapper = new OrderDataMapper(conn);
                orderSubmittedTime = new java.util.Date();
                newOrder.setEstimatedDeliveryTime(getDeliveryTime());
                orderMapper.insert(newOrder);
                orderFrame.dispose();
                StatusFrame statusFrame = null;
                try {
                    statusFrame = new StatusFrame(this);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                statusFrame.getFrame().setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Error: there should be at least one pizza in the order!");
            }
        }
    }

    public Order getNewOrder() { return newOrder; }

    public JFrame getFrame() { return this.orderFrame; }

    /**
     * This method checks whether there is at least one pizza in the order summary
     * @return true if there is at least one pizza, false otherwise
     */
    public boolean atLeastOnePizza(ArrayList<MenuItem> summary) {
        PizzaDataMapper mapper = new PizzaDataMapper(conn);
        ArrayList<Pizza> pizzas = mapper.getAllPizzas();

        boolean atLeastOne = false;

        loop:
        for (Pizza p : pizzas) {
            for (MenuItem m : summary) {
                if (p.getName().equals(m.getName())) {
                    atLeastOne = true;
                    break loop;
                }
            }
        }
        return atLeastOne;
    }

    /**
     * @return estimated delivery time
     */
    public java.util.Date getDeliveryTime(){
        Calendar c = Calendar.getInstance();
        c.setTime(orderSubmittedTime);
        long timeInSecs = c.getTimeInMillis();
        return new java.util.Date(timeInSecs + (20 * 60 * 1000));
    }

    /**
     * @param now is the current time
     * @param n is the number of minutes passed after the orderSubmittedTime
     * @return true is n minutes have passed, false otherwise
     */
    public boolean isNminutesPassed(java.util.Date now, int n){
        Calendar c = Calendar.getInstance();
        c.setTime(orderSubmittedTime);
        long timeInSecs = c.getTimeInMillis();
        java.util.Date after5minutes = new java.util.Date(timeInSecs + (n * 60 * 1000));
        if(now.after(after5minutes))
           return true;
        else
            return false;
    }
}
