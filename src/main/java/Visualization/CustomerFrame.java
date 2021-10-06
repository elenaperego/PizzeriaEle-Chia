package Visualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerFrame implements ActionListener {

    JFrame customerFrame = new JFrame();
    int customerId = 0;
    JLabel customerLabel = new JLabel(" CUSTOMER " + customerId);
    JLabel nameLabel = new JLabel(" NAME: ");
    JLabel phoneLabel = new JLabel(" PHONE NUMEBER");
    JLabel addressLabel = new JLabel();
    JLabel postalCodeLabel = new JLabel();
    JTextField nameBox = new JTextField();
    JTextField phoneBox = new JTextField();
    JTextField addressBox = new JTextField();
    JTextField postalCodeBox = new JTextField();
    JButton nextButton = new JButton();

    public CustomerFrame() {
        customerFrame.setBackground(Color.GREEN);
        customerFrame.setSize(500, 500);
        customerFrame.setTitle("Customer");
        customerFrame.setLayout(new GridLayout(6, 2));

        customerFrame.add(customerLabel);
        customerFrame.add(nameLabel);
        customerFrame.add()


        customerFrame.setLocationRelativeTo(null);
        customerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        customerFrame.setVisible(false);
    }

    public JFrame getCustomerFrame() { return this.customerFrame; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            // Get information from the TextFields in here!!
            // Check text fields and return error if null!
        }
    }
}
