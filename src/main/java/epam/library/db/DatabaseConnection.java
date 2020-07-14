package epam.library.db;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/library;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    final static Logger logger = LogManager.getLogger(DatabaseConnection.class);

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER).newInstance();
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                logger.info("Connection to database OK");
            } catch (Exception ex) {
                logger.error("Connection fail " + ex.getMessage());
            }
        }

        return connection;
    }

}
