package epam.library.dao;

import epam.library.model.Author;
import epam.library.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDao {

    Author findAuthorByName(String name) throws SQLException;

    List<Author> listAllAuthors();

    List<Book> listAllBooksByAuthor();

}
