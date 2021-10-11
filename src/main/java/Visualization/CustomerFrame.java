package Visualization;

import Classes.Customer.Customer;
import Classes.DeliveryPerson.DeliveryPerson;
import Classes.MenuItem;
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

    Timer timer;
    ArrayList<MenuItem> orderSummary;
    JFrame customerFrame = new JFrame();
    Customer customer;
    static int customerCount;
    DeliveryPerson deliveryPerson;
    JLabel customerLabel = new JLabel(" CUSTOMER DETAILS: ");
    JLabel nameLabel = new JLabel(" NAME: ");
    JLabel phoneLabel = new JLabel(" PHONE NUMBER: ");
    JLabel addressLabel = new JLabel(" ADDRESS: ");
    JLabel postalCodeLabel = new JLabel(" POSTAL CODE: ");
    JTextField nameBox = new JTextField(20);
    JTextField phoneBox = new JTextField(10);
    JTextField addressBox = new JTextField(20);
    JTextField postalCodeBox = new JTextField(1);
    JButton nextButton = new JButton();
    ImageIcon nextIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/NextIcon.png"));
    static AtomicInteger counter = new AtomicInteger();

    public CustomerFrame(ArrayList<MenuItem> summary) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.orderSummary = summary;
        customerFrame.setBackground(Color.GREEN);
        customerFrame.setSize(600, 600);
        customerFrame.setTitle("Customer");
        customerFrame.setLayout(new GridLayout(6, 2));

        customerLabel.setFont(new Font("Serif", Font.BOLD, 17));
        customerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 17));
        phoneLabel.setFont(new Font("Serif", Font.BOLD, 17));
        addressLabel.setFont(new Font("Serif", Font.BOLD, 17));
        postalCodeLabel.setFont(new Font("Serif", Font.BOLD, 17));

        nextButton.setIcon(nextIcon);
        nextButton.setBackground(Color.LIGHT_GRAY);
        nextButton.setBorderPainted(false);

        nameBox.addActionListener(this);
        phoneBox.addActionListener(this);
        addressBox.addActionListener(this);
        postalCodeBox.addActionListener(this);
        nextButton.addActionListener(this);

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

    public Customer getCustomer() { return this.customer; }

    public ArrayList<MenuItem> getOrderSummary() { return this.orderSummary; }

    public DeliveryPerson getDeliveryPerson() { return this.deliveryPerson; }

    @Override
    // Handbreak to make video smaller
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nextButton) {
            // If either one of the boxes is empty return an error!
            if (nameBox.getText().isEmpty() || phoneBox.getText().isEmpty() || addressBox.getText().isEmpty() || postalCodeBox.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: empty box!!");

            // If postal code is less than 7 digits it is not accepted
            } else if (postalCodeBox.getText().length() < 6) {
                JOptionPane.showMessageDialog(null, "Error: postal code not valid!");

            // No person is available (CHECK IF IT WORKS!!)
            } else {
                try {
                    if(!areDeliveryPersonsAvailable(postalCodeBox.getText())) {
                        JOptionPane.showMessageDialog(null, "Error: no delivery person available, wait!");
                        customerFrame.dispose();

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

                        if (!exists) {
                            // Since customer is new the ordered pizzas are equal to 0
                            Customer newCustomer = new Customer(++customerCount, nameBox.getText(), phoneBox.getText(), addressBox.getText(), postalCodeBox.getText(), 0);
                            mapper.insert(newCustomer);
                            this.customer = newCustomer;
                            System.out.println("customerframe, customercount: "+customerCount);
                        }

                        OrderFrame orderFrame = null;
                        try {
                            orderFrame = new OrderFrame(this);
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        } catch (InstantiationException instantiationException) {
                            instantiationException.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }

                        // Check whether this method works having introduced the new else if
                        // Code format: 1234 AB = 7 digits
                        try {
                            selectDeliveryPerson(postalCodeBox.getText());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                        availableAgain();

                        customerFrame.dispose();
                        orderFrame.getFrame().setVisible(true);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    /**
     * @param postalCode is the postal code to be checked for the delivery persons
     * @return true if there are delivery persons available for a given postalCode
     */
    public boolean areDeliveryPersonsAvailable(String postalCode) throws SQLException{
        String postalCodeTrimmed = postalCode.substring(0, 4);      // Check error from method above!
        int code = Integer.parseInt(postalCodeTrimmed);
        PreparedStatement pstmt = conn.prepareStatement("SELECT areacode FROM areacodes WHERE postalcode = ?");
        pstmt.setInt(1, code);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int areacode = rs.getInt(1);
        System.out.println("areacode: "+ areacode);
        PreparedStatement pstmt1 = conn.prepareStatement("SELECT deliveryPersonId, isGirl, isAvailable FROM deliverypersons WHERE areaCode = ?");
        pstmt1.setInt(1, areacode);
        ResultSet rs1 = pstmt1.executeQuery();
        while(rs1.next()){
            if(rs1.getBoolean(3)){
                deliveryPerson = new DeliveryPerson(rs1.getInt(1), rs1.getBoolean(2), areacode, false);
                break;
            }
        }
        if(Objects.isNull(deliveryPerson)){
            return false;

        } else {
            return true;
        }




        /*
        DeliveryPersonMapper mapper = new DeliveryPersonMapper(conn);
        ArrayList<DeliveryPerson> deliveryPeople = mapper.getDeliveryPersons();

        String postalCodeTrimmed = postalCode.substring(0, 4);
        int code = Integer.parseInt(postalCodeTrimmed);

        boolean isAvailable = false;

        for (DeliveryPerson p : deliveryPeople) {
            if (p.getAreaCode() == code) {
                if(p.isAvailable()) {
                    isAvailable = true;
                    break;
                }
            }
        }
        return isAvailable;

         */
    }

    /**
     * A delivery person is selected for a given order
     * @param postalCode is the postal code associated with this order
     * @throws SQLException
     */
    public void selectDeliveryPerson(String postalCode) throws SQLException {
        if(areDeliveryPersonsAvailable(postalCode)){
            DeliveryPersonMapper mapper = new DeliveryPersonMapper(conn);
            mapper.update(deliveryPerson);
        } else {
            JOptionPane.showMessageDialog(null, "There is no available delivery person, wait!");
        }
    }

    /**
     * This method sets a delivery person to be available again after 30 minutes
     */
    public void availableAgain() {
        timer = new Timer();
        TimerTask task = new TimerTask() {

            public void run() {
                DeliveryPersonMapper mapper = new DeliveryPersonMapper(conn);
                deliveryPerson.setAvailable(true);
                mapper.update(deliveryPerson);
                timer.cancel();
            }
        };
        long delay = 60000; //1 min in milliseconds (should be 30 min)
        timer.schedule(task, delay);
    }

}