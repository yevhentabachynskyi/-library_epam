package epam.library.db;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    @Test
    public void getConnectionTest() {
        Connection connection = DatabaseConnection.getConnection();
        assertEquals(connection != null, true);
    }

}