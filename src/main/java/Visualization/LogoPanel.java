package Visualization;

import Classes.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogoPanel extends JPanel implements ActionListener {

    MenuPanel menu;
    JLabel logo = new JLabel("            CHIA & ELE PIZZERIA");
    JButton pizzaLogo = new JButton();
    JButton vespaLogo = new JButton();
    ArrayList<MenuItem> orderSummary = new ArrayList<>();

    ImageIcon pizzaIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Pizza.png"));
    ImageIcon vespaIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Vespa.png"));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    LogoPanel(MenuPanel menu) {
        this.menu = menu;

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
        CustomerFrame customerFrame = null;
        try {
            customerFrame = new CustomerFrame(orderSummary);
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        if (e.getSource() == pizzaLogo) {
            customerFrame.getFrame().setVisible(true);

        // Get items from menu panel and confirm order
        } else if (e.getSource() == vespaLogo) {
            for (ObjectPanel o : menu.getMenu()) {
                if (o.getCheckBox().isSelected()) {
                    orderSummary.add(o.getObject());
                }
            }
            JOptionPane.showMessageDialog(null, "Order placed: press pizza button to confirm it!");
        }
    }
}
