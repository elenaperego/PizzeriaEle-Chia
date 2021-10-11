package Visualization;

import javax.swing.*;
import java.awt.*;

public class Launch {

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        JFrame frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(new BorderLayout());

        MenuPanel menuPanel = new MenuPanel();

        frame.add(menuPanel.getMenuPanel(), BorderLayout.CENTER);
        frame.add(new LogoPanel(menuPanel), BorderLayout.NORTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
