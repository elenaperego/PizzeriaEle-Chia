package Visualization;

import Classes.Order.Order;
import Mappers.ConnectionImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

public class StatusFrame implements ActionListener {

    Connection conn = ConnectionImpl.getConnection();
    OrderFrame orderFrame;
    Order order;
    int orderId;
    JFrame statusFrame = new JFrame();
    JLabel orderLabel = new JLabel("    ORDER STATUS:");
    JLabel estimatedDeliveryTimeLabel = new JLabel("    ESTIMATED DELIVERY TIME:");
    JLabel timeLabel = new JLabel();
    JLabel summaryLabel = new JLabel("    ORDER SUMMARY");
    JLabel statusLabel = new JLabel("    STATUS");
    JLabel status = new JLabel(" in process");    // cancelled, in process, out for delivery, delivered
    JComboBox<Object> summary = new JComboBox<>();
    JButton cancelButton = new JButton(" CANCEL ORDER ");

    StatusFrame(OrderFrame orderFrame) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.orderFrame = orderFrame;
        this.order = orderFrame.getNewOrder();
        this.orderId = order.getId();

        GridLayout layout = new GridLayout(5, 2);
        layout.setHgap(15);
        layout.setVgap(15);

        statusFrame.setLayout(layout);
        statusFrame.setBackground(Color.YELLOW);
        statusFrame.setSize(600, 600);
        statusFrame.setTitle("Status");

        orderLabel.setFont(new Font("Serif", Font.BOLD, 17));
        estimatedDeliveryTimeLabel.setFont(new Font("Serif", Font.BOLD, 17));
        timeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        timeLabel.setText("   " + orderFrame.getDeliveryTime().toString());
        summaryLabel.setFont(new Font("Serif", Font.BOLD, 17));
        statusLabel.setFont(new Font("Serif", Font.BOLD, 17));
        status.setFont(new Font("Serif", Font.BOLD, 17));

        // Add action listener to the different components
        cancelButton.addActionListener(this);

        statusFrame.add(orderLabel);
        statusFrame.add(new JLabel(""));
        statusFrame.add(estimatedDeliveryTimeLabel);
        statusFrame.add(timeLabel);
        statusFrame.add(summaryLabel);
        statusFrame.add(summary);
        statusFrame.add(statusLabel);
        statusFrame.add(status);
        statusFrame.add(new JLabel());
        statusFrame.add(cancelButton);

        statusFrame.setLocationRelativeTo(null);
        statusFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        statusFrame.setVisible(false);

        //orderCancellationAvailable();
    }

    public JFrame getFrame() { return this.statusFrame; }

    // Check whether 5 minutes are passed
    // If they are passed, set the cancel button not visible
    public void orderCancellationAvailable() {
        Date date = new Date();

        while (!orderFrame.isNminutesPassed(date, 5)) {
            date = new Date();
        }
        this.status.setText(" out for delivery");    // After 5 minutes order should be out for delivery
        this.cancelButton.setVisible(false);
    }

    // Update status to delivered after 15 minutes
    public void updateStatus() {
        Date date = new Date();

        while (!orderFrame.isNminutesPassed(date, 15)) {
            date = new Date();
        }
        this.status.setText(" delivered");      // After 15 minutes the order should be delivered
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            this.order = null;
            this.status.setText(" cancelled");      // Make sure the label changes when the order is cancelled
            JOptionPane.showMessageDialog(null, "Order has been cancelled!");
        }
    }
}
