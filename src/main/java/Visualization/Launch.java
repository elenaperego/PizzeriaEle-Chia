package Visualization;

import Mappers.ConnectionImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class Launch {

    public static void main (String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        Connection conn = ConnectionImpl.getConnection();

        JFrame frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(new BorderLayout());

        frame.add(new LogoPanel(conn), BorderLayout.NORTH);
        frame.add(new MenuPanel(conn).getMenuPanel(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
