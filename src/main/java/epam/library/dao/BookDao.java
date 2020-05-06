package epam.library.dao;

import epam.library.model.Author;
import epam.library.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    boolean addBook(Book book, Author author);

    boolean updateBook(Book book);

    Book editBook(long id);

    boolean deleteBook(Book book);

    Book findBookByName(String name);

    List<Book> listAllBooks();
}
