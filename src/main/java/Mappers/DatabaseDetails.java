package Mappers;

public class DatabaseDetails {
    private final static String URL = "jdbc:mysql://localhost:3306/chiaelepizzeria";
    private final static String username = "newuser";
    private final static String password = "password";

    public static String getURL() {
        return URL;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}