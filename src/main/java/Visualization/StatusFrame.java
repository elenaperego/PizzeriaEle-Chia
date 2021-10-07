package Visualization;

import Classes.Order.Order;
import Mappers.ConnectionImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class StatusFrame {

    //Connection conn = ConnectionImpl.getConnection();
    Order order;
    int orderId;
    JFrame statusFrame = new JFrame();
    JLabel orderLabel = new JLabel("STATUS ORDER " + orderId);
    JLabel estimatedDeliveryTimeLabel = new JLabel(" ESTIMATED DELIVERY TIME ");
    JLabel timeLabel = new JLabel();
    JLabel summaryLabel = new JLabel(" ORDER SUMMARY ");
    JLabel statusLabel = new JLabel(" STATUS ");
    JLabel status = new JLabel(" ordered ");
    JComboBox<Object> summary = new JComboBox<>();
    JButton cancelButton = new JButton(" CANCEL ORDER ");

    StatusFrame(Order order) /*throws IllegalAccessException, InstantiationException, ClassNotFoundException*/ {
        this.order = order;
        //this.orderId = order.getId();

        statusFrame.setBackground(Color.YELLOW);
        statusFrame.setSize(500, 500);
        statusFrame.setTitle("Status");

        statusFrame.setLayout(new GridLayout(5, 2));

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
    }

    public JFrame getFrame() { return this.statusFrame; }

    public void updateStatus() {}
}
