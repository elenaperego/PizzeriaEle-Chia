package Mappers;

public class DatabaseDetails {
    private static final String URL = "jdbc:mysql://localhost:3306/chiaelepizzeria";
    private static final String username = "root";
    private static final String password = "Elena030801";

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
