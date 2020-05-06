package epam.library.dao;

import epam.library.db.Database;
import epam.library.model.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImp implements ReaderDao {
    private Connection connection = Database.getConnection();
    final static Logger logger = LogManager.getLogger(ReaderDaoImp.class);

    @Override
    public boolean addReader(Reader reader) {
        String sql = "INSERT INTO READER (NAME, ADDRESS, PHONE) VALUES(?,?,?)";
        boolean rowInserted = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, reader.getName());
            preparedStatement.setString(2, reader.getAddress());
            preparedStatement.setInt(3, reader.getPhone());
            rowInserted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Add Reader Dao Error " + e.getMessage());
        }

        return rowInserted;

    }

    @Override
    public boolean updateReader(Reader reader) {
        String sql = "UPDATE READER SET NAME = ?, ADDRESS = ?, PHONE = ? WHERE ID = ?";
        boolean rowUpdated = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, reader.getName());
            statement.setString(2, reader.getAddress());
            statement.setInt(3, reader.getPhone());
            statement.setLong(4, reader.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Update Reader Dao Error " + e.getMessage());
        }

        return rowUpdated;
    }

    @Override
    public Reader editReader(long id) {
        Reader reader = null;
        String sql = "SELECT * FROM reader WHERE id = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String address = resultSet.getString("ADDRESS");
                int phone = resultSet.getInt("PHONE");

                reader = new Reader(id, name, address, phone);
            }

        } catch (SQLException e) {
            logger.error("Edit Reader Dao Error " + e.getMessage());
        }

        return reader;
    }

    @Override
    public boolean deleteReader(Reader reader) {
        String sql = "DELETE FROM READER where ID = ?";
        boolean rowDeleted = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, reader.getId());
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Delete Reader Dao Error " + e.getMessage());
        }

        return rowDeleted;
    }

    @Override
    public Reader findReaderByName(String name) {
        Reader reader = null;
        String sql = "SELECT * FROM READER WHERE name LIKE ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nameReader = resultSet.getString("NAME");
                String address = resultSet.getString("ADDRESS");
                int phone = resultSet.getInt("PHONE");
                reader = new Reader(id, nameReader, address, phone);
            }
        } catch (SQLException e) {
            logger.error("Find Reader Dao Error " + e.getMessage());
        }

        return reader;
    }

    @Override
    public List<Reader> listAllReaders() {
        List<Reader> listReader = new ArrayList<>();

        String sql = "SELECT * FROM READER";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                String address = resultSet.getString("ADDRESS");
                int phone = resultSet.getInt("PHONE");

                Reader reader = new Reader(id, name, address, phone);
                listReader.add(reader);
            }
        } catch (SQLException e) {
            logger.error("List All Reader Dao Error " + e.getMessage());
        }


        return listReader;
    }
}
