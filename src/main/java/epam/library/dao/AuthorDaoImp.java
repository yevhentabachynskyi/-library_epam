package epam.library.dao;

import epam.library.db.Database;
import epam.library.model.Author;
import epam.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImp implements AuthorDao {
    private Connection connection = Database.getConnection();

    @Override
    public Author findAuthorByName(String name) throws SQLException {
        Author author = null;
        String sql = "SELECT * FROM AUTHOR WHERE name like ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + name + "%");

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String nameAuthor = resultSet.getString("NAME");
            author = new Author(id, nameAuthor);
            System.out.println(author.getName());
        }

        return author;
    }

    @Override
    public List<Author> listAllAuthors() {
        List<Author> listAuthors = new ArrayList<>();

        String sql = "SELECT * FROM AUTHOR";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                Author author = new Author(id, name);
                listAuthors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listAuthors;
    }

    @Override
    public List<Book> listAllBooksByAuthor(String name) throws SQLException {
        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM BOOK WHERE NAME_AUTHOR = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            long id = resultSet.getLong("ID");
            String title = resultSet.getString("TITLE");
            String author = resultSet.getString("NAME_AUTHOR");
            String genre = resultSet.getString("GENRE");
            int year = resultSet.getInt("PUBLISH_YEAR");
            int num = resultSet.getInt("NUMBER");

            Book book = new Book(id, title, author, genre, year, num);
            listBook.add(book);
            System.out.println(title + " - " + author);

        }

        return listBook;
    }
}
