package epam.library.dao;

import epam.library.model.Author;
import epam.library.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    boolean addBook(Book book, Author author) throws SQLException;

    boolean updateBook(Book book) throws SQLException;

    Book editBook(long id) throws SQLException;

    boolean deleteBook(Book book) throws SQLException;

    Book findBookByName(String name) throws SQLException;

    List<Book> listAllBooks();
}
