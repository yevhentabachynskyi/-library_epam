package epam.library.dao;

import epam.library.db.Database;
import epam.library.model.Author;
import epam.library.model.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImp implements AuthorDao {
    private Connection connection = Database.getConnection();
    final static Logger logger = LogManager.getLogger(AuthorDaoImp.class);

    @Override
    public Author findAuthorByName(String name)  {
        Author author = null;
        String sql = "SELECT * FROM AUTHOR WHERE name like ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nameAuthor = resultSet.getString("NAME");
                author = new Author(id, nameAuthor);
                System.out.println(author.getName());
            }
        } catch (SQLException e) {
            logger.error("Find Author Dao Error " + e.getMessage());
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
                Author author = new Author(id, name, listAllBooksByAuthor(name));
                listAuthors.add(author);
            }
        } catch (SQLException e) {
            logger.error("List All Author Dao Error " + e.getMessage());
        }


        return listAuthors;
    }

    @Override
    public List<Book> listAllBooksByAuthor(String name){
        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM BOOK WHERE NAME_AUTHOR = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
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
        } catch (SQLException e) {
            logger.error("List Book By Author Dao Error " + e.getMessage());
        }

        return listBook;
    }
}
