package Visualization;

import javax.swing.*;
import java.awt.*;

public class Launch {

    public static void main (String[] args){

        JFrame frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(new BorderLayout());

        frame.add(new LogoPanel(), BorderLayout.NORTH);
        frame.add(new MenuPanel().getMenuPanel(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
