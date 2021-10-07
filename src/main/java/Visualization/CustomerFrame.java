package Visualization;

import Classes.Customer.Customer;
import Classes.DeliveryPerson.DeliveryPerson;
import Classes.Order.Order;
import Mappers.ConnectionImpl;
import Mappers.CustomerDataMapper;
import Mappers.DeliveryPersonMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerFrame implements ActionListener {

    Connection conn = ConnectionImpl.getConnection();
    JFrame customerFrame = new JFrame();
    Order order;
    Customer customer;
    DeliveryPerson deliveryPerson;
    JLabel customerLabel = new JLabel(" CUSTOMER DETAILS: ");
    JLabel nameLabel = new JLabel(" NAME: ");
    JLabel phoneLabel = new JLabel(" PHONE NUMBER: ");
    JLabel addressLabel = new JLabel(" ADDRESS: ");
    JLabel postalCodeLabel = new JLabel(" POSTAL CODE: ");
    JTextField nameBox = new JTextField();
    JTextField phoneBox = new JTextField();
    JTextField addressBox = new JTextField();
    JTextField postalCodeBox = new JTextField();
    JButton nextButton = new JButton(" NEXT ");
    static AtomicInteger counter = new AtomicInteger();

    public CustomerFrame(Order order) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.order = order;
        customerFrame.setBackground(Color.GREEN);
        customerFrame.setSize(500, 500);
        customerFrame.setTitle("Customer");
        customerFrame.setLayout(new GridLayout(6, 2));

        customerFrame.add(customerLabel);
        customerFrame.add(new JLabel(""));
        customerFrame.add(nameLabel);
        customerFrame.add(nameBox);
        customerFrame.add(phoneLabel);
        customerFrame.add(phoneBox);
        customerFrame.add(addressLabel);
        customerFrame.add(addressBox);
        customerFrame.add(postalCodeLabel);
        customerFrame.add(postalCodeBox);
        customerFrame.add(new JLabel(""));
        customerFrame.add(nextButton);

        customerFrame.setLocationRelativeTo(null);
        customerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        customerFrame.setVisible(false);
    }

    public JFrame getFrame() { return this.customerFrame; }

    @Override
    public void actionPerformed(ActionEvent e) {
        // FIX THIS METHOD !!!!!!!!!!!!
        if (e.getSource() == nextButton) {
            if (nameBox.getText() == null || phoneBox.getText() == null || addressBox.getText() == null || postalCodeBox.getText() == null) {
                // SHOW ERROR IF AT LEAST ONE BOX IS NULL
                System.out.println("Error: Empty Box!!!");

            // IF NO BOX IS NULL, check whether the customer has already ordered before by checking his/her name
            // If the name already exists get that customer, otherwise create a new customer!!
            } else {
                CustomerDataMapper mapper = new CustomerDataMapper(conn);
                ArrayList<Customer> allCustomers = mapper.getAllCustomers();

                boolean exists = false;
                for (Customer c : allCustomers) {
                    if (nameBox.getText().equals(c.getName()) && phoneBox.getText().equals(c.getPhoneNumber())) {
                        this.customer = c;  // Customer already exists
                        exists = true;
                        break;
                    }
                }

                if(!exists) {
                    Customer newCustomer = new Customer(counter.getAndIncrement(), nameBox.getText(), phoneBox.getText(), addressBox.getText(), Integer.parseInt(postalCodeBox.getText()));
                    mapper.insert(newCustomer);
                    this.customer = newCustomer;
                }
            }

            OrderFrame orderFrame = null;
            try {
                orderFrame = new OrderFrame();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (InstantiationException instantiationException) {
                instantiationException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            try {
                selectDeliveryPerson(postalCodeBox.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            availableAgain();
            customerFrame.dispose();
            orderFrame.getFrame().setVisible(true);
        }
    }

    public void selectDeliveryPerson(String postalCode) throws SQLException {
        String postalCodeTrimmed = postalCode.substring(0, 5);
        int code = Integer.parseInt(postalCodeTrimmed);
        PreparedStatement pstmt = conn.prepareStatement("SELECT areacode FROM areacodes WHERE postalcode = ?");
        pstmt.setInt(1, code);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int areacode = rs.getInt(1);
        PreparedStatement pstmt1 = conn.prepareStatement("SELECT deliveryPersonId, isGirl, isAvailable FROM deliverypersons WHERE areaCode = ?");
        pstmt.setInt(1, areacode);
        ResultSet rs1 = pstmt1.executeQuery();
        DeliveryPerson deliveryPerson = null;
        while(rs.next()){
            if(rs.getBoolean(3)){
                deliveryPerson = new DeliveryPerson(rs.getInt(1), rs.getBoolean(2), areacode, false);
                break;
            }
        }
        if(Objects.isNull(deliveryPerson)){
            System.out.println("there is no available delivery person, wait");
            // ADD ERROR FRAME IF DELIVERY PERSON NOT AVAILABLE
        } else {
            DeliveryPersonMapper mapper = new DeliveryPersonMapper(conn);
            mapper.insert(deliveryPerson);
        }
    }

    public void availableAgain() {
        TimerTask task = new TimerTask() {
            public void run() {
                DeliveryPersonMapper mapper = new DeliveryPersonMapper(conn);
                deliveryPerson.setAvailable(true);
                mapper.update(deliveryPerson);
            }
        };
        Timer timer = new Timer();
        long delay = 1800000; //30 min
        timer.schedule(task, delay);
    }
}
