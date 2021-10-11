package Visualization;

import Classes.MenuItem;
import Classes.Pizza.Pizza;
import Mappers.ConnectionImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObjectPanel implements ActionListener {

    MenuItem item;
    JPanel objectPanel = new JPanel();
    JCheckBox checkBox = new JCheckBox();
    JButton nameButton = new JButton();
    JLabel priceLabel = new JLabel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public ObjectPanel(MenuItem item) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.item = item;

        nameButton.setText(item.getName());

        priceLabel.setText("" + item.getPrice() + " €");

        nameButton.addActionListener(this);
        checkBox.addActionListener(this);

        nameButton.setBackground(Color.LIGHT_GRAY);
        priceLabel.setBackground(Color.LIGHT_GRAY);

        objectPanel.setBackground(Color.LIGHT_GRAY);
        objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.X_AXIS));
        objectPanel.setPreferredSize(new Dimension(screenSize.width, 300));
        objectPanel.add(checkBox);
        objectPanel.add(nameButton);
        objectPanel.add(priceLabel);
    }

    public JPanel getPanel() { return this.objectPanel; }

    public MenuItem getObject() { return this.item; }

    public JCheckBox getCheckBox() { return this.checkBox; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nameButton) {
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
