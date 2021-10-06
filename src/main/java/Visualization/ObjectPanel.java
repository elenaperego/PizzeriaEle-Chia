package Visualization;

import Classes.MenuItem;
import Classes.Pizza.Pizza;
import Mappers.ConnectionImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ObjectPanel implements ActionListener {

    MenuItem item;
    JPanel objectPanel = new JPanel();
    JCheckBox checkBox = new JCheckBox();
    JButton nameLabel = new JButton();
    JLabel priceLabel = new JLabel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public ObjectPanel(MenuItem item) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.item = item;

        nameLabel.setText(item.getName());
        priceLabel.setText("" + item.getPrice() + " €");

        nameLabel.addActionListener(this);
        nameLabel.setBackground(Color.LIGHT_GRAY);
        priceLabel.setBackground(Color.LIGHT_GRAY);

        objectPanel.setBackground(Color.LIGHT_GRAY);
        objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.X_AXIS));
        objectPanel.setPreferredSize(new Dimension(screenSize.width, 300));
        objectPanel.add(checkBox);
        objectPanel.add(nameLabel);
        objectPanel.add(priceLabel);
    }

    public JPanel getPanel() { return this.objectPanel; }

    public MenuItem getObject() { return this.item; }

    public JCheckBox getCheckBox() { return this.checkBox; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nameLabel) {
            ToppingsFrame toppings = null;
            try {
                toppings = new ToppingsFrame((Pizza) item);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (InstantiationException instantiationException) {
                instantiationException.printStackTrace();
            }
            toppings.getFrame().setVisible(true);
        }
    }
}
