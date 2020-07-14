package epam.library.dao;

import epam.library.model.Author;
import epam.library.model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public interface AuthorDao  {

    Author findAuthorByName(String name);

    List<Author> listAllAuthors();

    List<Book> listAllBooksByAuthor(String name);

}
