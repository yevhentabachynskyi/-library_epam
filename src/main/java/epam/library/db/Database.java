package epam.library.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        return connection;
    }
}
