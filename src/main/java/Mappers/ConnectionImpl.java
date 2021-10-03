package Mappers;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImpl {
    private static String URL = "jdbc:mysql://localhost:3306/chiaelepizzeria";
    private static String username = "root";
    private static String password = "Elena030801";

    public static Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException ex) {
            System.out.println("SQLException: "+ex.getMessage());
            System.out.println("SQLState: "+ex.getSQLState());
            System.out.println("VendorError: "+ex.getErrorCode());
        }
        return connection;
    }

}
