package Mappers;

public class DatabaseDetails {
    private final String URL = "jdbc:mysql://localhost:3306/chiaelepizzeria";

    public static String getURL() {
        return URL;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    private final String username = "root";
    private final String password = "Elena030801";
}
