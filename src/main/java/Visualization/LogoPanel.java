package Visualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoPanel extends JPanel implements ActionListener {

    JLabel logo = new JLabel("            CHIA & ELE PIZZERIA");
    JButton pizzaLogo = new JButton();
    JButton vespaLogo = new JButton();

    ImageIcon pizzaIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Pizza.png"));
    ImageIcon vespaIcon = new ImageIcon(ImageLoader.loadImage("src/main/java/Visualization/Resources/Vespa.png"));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    LogoPanel() {
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
        vespaLogo.setBorderPainted(false);
        vespaLogo.addActionListener(this);

        this.add(vespaLogo, BorderLayout.EAST);
        this.add(logo, BorderLayout.CENTER);
        this.add(pizzaLogo, BorderLayout.WEST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pizzaLogo) {
            OrderFrame f = new OrderFrame();
            f.setVisible(true);

        } else if (e.getSource() == vespaLogo) {
            StatusFrame f = new StatusFrame();
            f.setVisible(true);
        }
    }
}
