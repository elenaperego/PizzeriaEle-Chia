package Visualization;

import Classes.Order.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoPanel extends JPanel implements ActionListener {

    //Connection conn = ConnectionImpl.getConnection();

    JLabel logo = new JLabel("            CHIA & ELE PIZZERIA");
    JButton pizzaLogo = new JButton();
    JButton vespaLogo = new JButton();

    ImageIcon pizzaIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Pizza.png"));
    ImageIcon vespaIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Vespa.png"));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    LogoPanel() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(screenSize.width/2, screenSize.height/6));
        this.setLayout(new BorderLayout());

        logo.setFont(new Font(Font.SERIF, Font.BOLD, 50));

        pizzaLogo.setIcon(pizzaIcon);
        pizzaLogo.setPreferredSize(new Dimension(screenSize.width/5, screenSize.height/6));
        pizzaLogo.setBackground(Color.RED);
        pizzaLogo.setBorderPainted(false);
        pizzaLogo.addActionListener(this);

        vespaLogo.setIcon(vespaIcon);
        vespaLogo.setPreferredSize(new Dimension(screenSize.width/5, screenSize.height/6));
        vespaLogo.setBackground(Color.RED);
        vespaLogo.setBorderPainted(false);
        vespaLogo.addActionListener(this);

        this.add(vespaLogo, BorderLayout.EAST);
        this.add(logo, BorderLayout.CENTER);
        this.add(pizzaLogo, BorderLayout.WEST);
    }

    @Override
    // Change the structures of the frames that show up when the buttons are pressed!!!!!!
    public void actionPerformed(ActionEvent e) {
        /*
        Order order = null;
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
        order = orderFrame.getNewOrder(); // Check whether order is correct

        StatusFrame statusFrame = null;
        try {
            statusFrame = new StatusFrame(order);
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }*/

        CustomerFrame customerFrame = null;
        try {
            customerFrame = new CustomerFrame();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        if (e.getSource() == pizzaLogo) {
            customerFrame.getFrame().setVisible(true);

        } else if (e.getSource() == vespaLogo) {
            //statusFrame.getFrame().setVisible(true);
        }
    }
}
