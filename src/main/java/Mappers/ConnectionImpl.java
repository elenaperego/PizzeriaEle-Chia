package Mappers;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImpl {


    public static Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DatabaseDetails.getURL(), DatabaseDetails.getUsername(), DatabaseDetails.getPassword());
        } catch (SQLException ex) {
            System.out.println("SQLException: "+ex.getMessage());
            System.out.println("SQLState: "+ex.getSQLState());
            System.out.println("VendorError: "+ex.getErrorCode());
        }
        return connection;
    }

}
