package epam.library.db;

import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateTableTest {
    private CreateTable createTable = spy(new CreateTable());
    @Test
    public void createTableTest() throws SQLException {
        doNothing().when(createTable).createDB();
    }
}