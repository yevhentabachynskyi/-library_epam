package epam.library.dao;

import epam.library.db.DatabaseConnection;
import epam.library.model.Author;
import epam.library.model.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImp implements BookDao {
    private Connection connection = DatabaseConnection.getConnection();
    final static Logger logger = LogManager.getLogger(BookDaoImp.class);


    @Override
    public boolean addBook(Book book, Author author) {
        boolean rowInserted = false;
        String sqlAuthor = "INSERT INTO AUTHOR(NAME) VALUES (?)";
        PreparedStatement statementA = null;
        PreparedStatement statementB = null;
        try {
            try {
                statementA = connection.prepareStatement(sqlAuthor);
                statementA.setString(1, author.getName());
                rowInserted = statementA.executeUpdate() > 0;
            } catch (JdbcSQLIntegrityConstraintViolationException e) {
                rowInserted = true;
                logger.error(e.getMessage());
            }

            String sqlBook = "INSERT INTO BOOK (TITLE, NAME_AUTHOR, GENRE, PUBLISH_YEAR, NUMBER) VALUES(?,?,?,?,?)";
            statementB = connection.prepareStatement(sqlBook);
            statementB.setString(1, book.getTitle());
            statementB.setString(2, book.getAuthorName());
            statementB.setString(3, book.getGenre());
            statementB.setInt(4, book.getPublishYear());
            statementB.setInt(5, book.getNumber());
            rowInserted = statementB.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Add Book Dao Error " + e.getMessage());
        }


        return rowInserted;
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "UPDATE BOOK SET TITLE = ?, NAME_AUTHOR = ?, GENRE = ?, PUBLISH_YEAR = ?, NUMBER = ? WHERE ID = ?";

        boolean rowUpdated = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthorName());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getPublishYear());
            statement.setInt(5, book.getNumber());
            statement.setLong(6, book.getId());

            rowUpdated = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            logger.error("Update Book Dao Error " + e.getMessage());
        }

        return rowUpdated;
    }

    @Override
    public Book editBook(long id) {
        Book book = null;
        String sql = "SELECT * FROM BOOK WHERE BOOK.ID = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
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
        } catch (SQLException e) {
            logger.error("Edit Book Dao Error " + e.getMessage());
        }

        return book;
    }

    @Override
    public boolean deleteBook(Book book) {
        String sql = "DELETE FROM BOOK where ID = ?";
        boolean rowDeleted = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, book.getId());
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Delete Book Dao Error " + e.getMessage());
        }

        return rowDeleted;
    }

    @Override
    public Book findBookByName(String name) {
        Book book = null;
        String sql = "SELECT * FROM BOOK WHERE TITLE LIKE ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String title = resultSet.getString("TITLE");
                String author = resultSet.getString("NAME_AUTHOR");
                String genre = resultSet.getString("GENRE");
                int year = resultSet.getInt("PUBLISH_YEAR");
                int num = resultSet.getInt("NUMBER");

                book = new Book(id, title, author, genre, year, num);
            }
        } catch (SQLException e) {
            logger.error("Find Book Dao Error " + e.getMessage());
        }


        return book;
    }

    @Override
    public List<Book> listAllBooks() {
        List<Book> listBook = new ArrayList<>();
        String sql = "SELECT * FROM BOOK";
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
            }
        } catch (SQLException e) {
            logger.error("List Book Dao Error " + e.getMessage());
        }


        return listBook;
    }
}
