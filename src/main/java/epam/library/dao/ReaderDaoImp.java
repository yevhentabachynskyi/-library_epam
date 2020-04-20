package epam.library.dao;

import epam.library.db.Database;
import epam.library.model.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImp implements ReaderDao {
    private Connection connection = Database.getConnection();


    @Override
    public boolean addReader(Reader reader) throws SQLException {
        String sql = "INSERT INTO READER (NAME, ADDRESS, PHONE) VALUES(?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, reader.getName());
        preparedStatement.setString(2, reader.getAddress());
        preparedStatement.setInt(3, reader.getPhone());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        //System.out.println("add " + reader.getName());


        return rowInserted;

    }

    @Override
    public boolean updateReader(Reader reader) throws SQLException {
        String sql = "UPDATE READER SET NAME = ?, ADDRESS = ?, PHONE = ? WHERE ID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, reader.getName());
        statement.setString(2, reader.getAddress());
        statement.setInt(3, reader.getPhone());
        statement.setLong(4, reader.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();

        return rowUpdated;
    }

    @Override
    public Reader editReader(long id) throws SQLException {
        Reader reader = null;
        String sql = "SELECT * FROM reader WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("NAME");
            String address = resultSet.getString("ADDRESS");
            int phone = resultSet.getInt("PHONE");

            reader = new Reader(id, name, address, phone);
        }

        return reader;
    }

    @Override
    public boolean deleteReader(Reader reader) throws SQLException {
        String sql = "DELETE FROM READER where ID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, reader.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }

    @Override
    public Reader findReaderByName(String name) throws SQLException {
        Reader reader = null;
        String sql = "SELECT * FROM READER WHERE name = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String address = resultSet.getString("ADDRESS");
            int phone = resultSet.getInt("PHONE");
            reader = new Reader(id, name, address, phone);
            System.out.println(reader.getName() + " " + reader.getAddress());
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
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String address = resultSet.getString("ADDRESS");
                int phone = resultSet.getInt("PHONE");

                Reader reader = new Reader(id, name, address, phone);
                listReader.add(reader);
               // System.out.println(name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listReader;
    }
}
