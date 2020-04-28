package epam.library.dao;

import epam.library.db.Database;
import epam.library.model.Author;
import epam.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImp implements BookDao {
    private Connection connection = Database.getConnection();

    @Override
    public boolean addBook(Book book, Author author) throws SQLException {
        String sqlAuthor = "INSERT INTO AUTHOR(NAME) VALUES (?)";
        String sqlBook = "INSERT INTO BOOK (TITLE, NAME_AUTHOR, GENRE, PUBLISH_YEAR, NUMBER) VALUES(?,?,?,?,?)";

        PreparedStatement statementA = connection.prepareStatement(sqlAuthor);
        statementA.setString(1, author.getName());

        PreparedStatement statementB = connection.prepareStatement(sqlBook);
        statementB.setString(1, book.getTitle());
        statementB.setString(2, book.getAuthorName());
        statementB.setString(3, book.getGenre());
        statementB.setInt(4, book.getPublishYear());
        statementB.setInt(5, book.getNumber());


        boolean rowInserted = statementB.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE BOOK SET TITLE = ?, NAME_AUTHOR = ?, GENRE = ?, PUBLISH_YEAR = ?, NUMBER = ? WHERE ID = ?";


        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthorName());
        statement.setString(3, book.getGenre());
        statement.setInt(4, book.getPublishYear());
        statement.setInt(5, book.getNumber());
        statement.setLong(6, book.getId());


        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();

        return rowUpdated;
    }

    @Override
    public Book editBook(long id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM BOOK /*JOIN AUTHOR A on BOOK.NAME_AUTHOR = A.ID*/ WHERE BOOK.ID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("TITLE");
            String author = resultSet.getString("NAME_AUTHOR");
            String genre = resultSet.getString("GENRE");
            int year = resultSet.getInt("PUBLISH_YEAR");
            int num = resultSet.getInt("NUMBER");
            book = new Book(id, name, author, genre, year, num);
        }

        return book;
    }

    @Override
    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM BOOK where ID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, book.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }

    @Override
    public Book findBookByName(String name) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM BOOK WHERE TITLE LIKE ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + name + "%");

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            long id = resultSet.getLong("ID");
            String title = resultSet.getString("TITLE");
            String author = resultSet.getString("NAME_AUTHOR");
            String genre = resultSet.getString("GENRE");
            int year = resultSet.getInt("PUBLISH_YEAR");
            int num = resultSet.getInt("NUMBER");

            book = new Book(id, title, author, genre, year, num);
            System.out.println(title);
        }

        return book;
    }

    @Override
    public List<Book> listAllBooks() {
        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM BOOK/* JOIN AUTHOR A on BOOK.NAME_AUTHOR = A.ID*/";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String title = resultSet.getString("TITLE");
                String author = resultSet.getString("NAME_AUTHOR");
                String genre = resultSet.getString("GENRE");
                int year = resultSet.getInt("PUBLISH_YEAR");
                int num = resultSet.getInt("NUMBER");

                Book book = new Book(id, title, author, genre, year, num);
                listBook.add(book);
                /*System.out.println(name + " - " + author);*/

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listBook;
    }
}
