package Mappers;

public class DatabaseDetails {
    public static String getURL() {
        return URL;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    private static String URL = "jdbc:mysql://localhost:3306/chiaelepizzeria";
    private static String username = "root";
    private static String password = "Elena030801";
}
