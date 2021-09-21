package Mappers;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImpl {
    private static String URL = "jdbc:mysql://localhost:3306/java_demo?useSSL=false";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() {
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
