package Visualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel implements ActionListener {

    JPanel menuPanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(menuPanel);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    MenuPanel() {
        menuPanel.setBackground(Color.LIGHT_GRAY);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addPanel("Margherita", 5.50);
        addPanel("Quattro Formaggi", 10);
        addPanel("San Daniele", 7);
        addPanel("Primavera", 8.5);
        addPanel("Boscaiola", 10);
        addPanel("Diavola", 7);
        addPanel("Quattro Stagioni", 10);
        addPanel("Bimbo", 5);
        addPanel("Elena", 12);
        addPanel("Chiara", 12);
        addPanel("Tiramisù", 8);
        addPanel("Cheesecake", 9);
        addPanel("Water", 1.5);
        addPanel("Wine", 10);
        addPanel("Beer", 3);
        addPanel("Coke", 2);
    }

    public void addPanel(String text, double price) {
        JPanel panel = new JPanel();
        JCheckBox checkBox = new JCheckBox();
        JButton nameLabel = new JButton(text);
        JLabel priceLabel = new JLabel("" + price + " €");

        nameLabel.addActionListener(this);

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setPreferredSize(new Dimension(screenSize.width, 300));
        panel.add(checkBox);
        panel.add(nameLabel);
        panel.add(priceLabel);
        menuPanel.add(panel);
        menuPanel.add(Box.createRigidArea(new Dimension(screenSize.width, 30)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IngredientsFrame ingredients = new IngredientsFrame();
        ingredients.setVisible(true);
    }

    public JScrollPane getMenuPanel() { return scrollPane; }
}
