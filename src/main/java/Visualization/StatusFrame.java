package Visualization;

import Classes.DeliveryPerson.DeliveryPerson;
import Classes.Order.Order;
import Mappers.ConnectionImpl;
import Mappers.OrderDataMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;

public class StatusFrame implements ActionListener {

    Connection conn = ConnectionImpl.getConnection();
    OrderFrame orderFrame;
    Order order;
    DeliveryPerson deliveryPerson;
    int orderId;
    JFrame statusFrame = new JFrame();
    JLabel orderLabel = new JLabel("    ORDER STATUS:");
    JLabel estimatedDeliveryTimeLabel = new JLabel("    ESTIMATED DELIVERY TIME:");
    JLabel timeLabel = new JLabel();
    JLabel deliveryPersonLabel = new JLabel("    DELIVERY PERSON ID:");
    JLabel personIdLabel = new JLabel();
    JLabel statusLabel = new JLabel("    STATUS");
    JLabel status = new JLabel(" in process");    // cancelled, in process, out for delivery, delivered
    JButton cancelButton = new JButton(" CANCEL ORDER ");

    StatusFrame(OrderFrame orderFrame) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.orderFrame = orderFrame;
        this.order = orderFrame.getNewOrder();
        this.orderId = order.getId();
        this.deliveryPerson = orderFrame.customerFrame.getDeliveryPerson();

        GridLayout layout = new GridLayout(5, 2);
        layout.setHgap(15);
        layout.setVgap(15);

        statusFrame.setLayout(layout);
        statusFrame.setBackground(Color.YELLOW);
        statusFrame.setSize(600, 600);
        statusFrame.setTitle("Status");

        personIdLabel.setText("" + deliveryPerson.getDeliveryPersonId());

        orderLabel.setFont(new Font("Serif", Font.BOLD, 17));
        estimatedDeliveryTimeLabel.setFont(new Font("Serif", Font.BOLD, 17));
        timeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        timeLabel.setText("   " + orderFrame.getDeliveryTime().toString());
        deliveryPersonLabel.setFont(new Font("Serif", Font.BOLD, 17));
        personIdLabel.setFont(new Font("Serif", Font.BOLD, 17));
        statusLabel.setFont(new Font("Serif", Font.BOLD, 17));
        status.setFont(new Font("Serif", Font.BOLD, 17));

        // Add action listener to the different components
        cancelButton.addActionListener(this);

        statusFrame.add(orderLabel);
        statusFrame.add(new JLabel(""));
        statusFrame.add(estimatedDeliveryTimeLabel);
        statusFrame.add(timeLabel);
        statusFrame.add(deliveryPersonLabel);
        statusFrame.add(personIdLabel);
        statusFrame.add(statusLabel);
        statusFrame.add(status);
        statusFrame.add(new JLabel());
        statusFrame.add(cancelButton);

        orderCancellationAvailable();
        updateStatus();

        statusFrame.setLocationRelativeTo(null);
        statusFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        statusFrame.setVisible(false);
    }

    public JFrame getFrame() { return this.statusFrame; }

    // Check whether 5 minutes are passed
    // If they are passed, set the cancel button not visible
    public void orderCancellationAvailable() {
        TimerTask task = new TimerTask() {
            public void run() {
                status.setText(" out for delivery");    // After 5 minutes order should be out for delivery
                cancelButton.setVisible(false);
            }
        };
        java.util.Timer timer = new Timer();
        long delay = 60000/2; //30 sec in milliseconds (should be 5)
        timer.schedule(task, delay);
    }

    // Update status to delivered after 15 minutes
    public void updateStatus() {
        TimerTask task = new TimerTask() {
            public void run() {
                status.setText(" delivered");
            }
        };
        java.util.Timer timer = new Timer();
        long delay = 120000/2; //1 min in milliseconds (should be 15)
        timer.schedule(task, delay);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            OrderDataMapper mapper = new OrderDataMapper(conn);
            mapper.delete(order);
            this.order = null;
            this.status.setText(" cancelled");      // Make sure the label changes when the order is cancelled
            JOptionPane.showMessageDialog(null, "Order has been cancelled!");
        }
    }
}
