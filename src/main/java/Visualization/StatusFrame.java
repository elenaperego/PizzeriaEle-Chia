package Visualization;

import Classes.Order.Order;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class StatusFrame extends JFrame {

    Connection conn;
    Order order;
    JLabel orderLabel = new JLabel("STATUS ORDER " + order.getId());

    StatusFrame(Connection conn, Order order){
        this.conn = conn;
        this.order = order;

        this.setBackground(Color.YELLOW);
        this.setSize(500, 500);
        this.setTitle("Status");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(false);
    }

    public void updateStatus() {}
}
