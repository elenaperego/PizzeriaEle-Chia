package Visualization;

import Classes.DeliveryPerson.DeliveryPerson;
import Classes.Order.Order;
import Mappers.ConnectionImpl;
import Mappers.DeliveryPersonOrderDataMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CustomerFrame implements ActionListener {

    Connection conn = ConnectionImpl.getConnection();
    JFrame customerFrame = new JFrame();
    Order order;
    int customerId = 0;
    JLabel customerLabel = new JLabel(" CUSTOMER " + customerId);
    JLabel nameLabel = new JLabel(" NAME: ");
    JLabel phoneLabel = new JLabel(" PHONE NUMBER: ");
    JLabel addressLabel = new JLabel(" ADDRESS: ");
    JLabel postalCodeLabel = new JLabel(" POSTAL CODE: ");
    JTextField nameBox = new JTextField();
    JTextField phoneBox = new JTextField();
    JTextField addressBox = new JTextField();
    JTextField postalCodeBox = new JTextField();
    JButton nextButton = new JButton(" NEXT ");

    public CustomerFrame(Order order) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.order = order;
        customerFrame.setBackground(Color.GREEN);
        customerFrame.setSize(500, 500);
        customerFrame.setTitle("Customer");
        customerFrame.setLayout(new GridLayout(6, 2));

        customerFrame.add(customerLabel);
        customerFrame.add(nameLabel);
        customerFrame.add(nameBox);
        customerFrame.add(phoneLabel);
        customerFrame.add(phoneBox);
        customerFrame.add(addressLabel);
        customerFrame.add(addressBox);
        customerFrame.add(postalCodeLabel);
        customerFrame.add(postalCodeBox);
        customerFrame.add(nextButton);

        customerFrame.setLocationRelativeTo(null);
        customerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        customerFrame.setVisible(false);
    }

    public JFrame getFrame() { return this.customerFrame; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            // Get information from the TextFields in here!!
            // Check text fields and return error if null!

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
                deliveryPerson = new DeliveryPerson(rs.getInt(1), rs.getBoolean(2), areacode, rs.getBoolean(3));
            }
        }
        if(Objects.isNull(deliveryPerson)){
            System.out.println("there is no available delivery person, wait");
        }
        DeliveryPersonOrderDataMapper mapper = new DeliveryPersonOrderDataMapper(conn);
        mapper.insert(order.getId(), deliveryPerson.getDeliveryPersonId());
    }
}
