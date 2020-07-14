package epam.library.dao;

import epam.library.model.Author;
import epam.library.model.Book;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Fields;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class BookDaoImpTest {
    private BookDao bookDaoMock = mock(BookDaoImp.class);
    private Book book = new Book("Title Test", "Author Test","Genre Test", 2000, 2);;
    private Author author  = new Author(book.getAuthorName());;
    private List<Book> books = new ArrayList<>();


    @Test
    public void addBookTest() {
        when(bookDaoMock.addBook(book, author)).thenReturn(true);
    }

    @Test
    public void updateBookTest() {
        when(bookDaoMock.updateBook((Book) isNotNull())).thenReturn(anyBoolean());
        when(bookDaoMock.updateBook(book)).thenReturn(true);
    }

    @Test
    public void editBookTest() {
        when(bookDaoMock.editBook(book.getId())).thenReturn(book);
        book = bookDaoMock.editBook(book.getId());
        verify(bookDaoMock).editBook(book.getId());
        assertEquals(book, bookDaoMock.editBook(book.getId()));
    }

    @Test
    public void deleteBookTest() {
        when(bookDaoMock.deleteBook((Book) isNotNull())).thenReturn(anyBoolean());
        when(bookDaoMock.deleteBook(book)).thenReturn(true);
    }

    @Test
    public void findBookByNameTest() {
        when(bookDaoMock.findBookByName(book.getTitle())).thenReturn(book);
        book = bookDaoMock.findBookByName(book.getTitle());
        verify(bookDaoMock).findBookByName(book.getTitle());
        assertEquals(book, bookDaoMock.findBookByName(book.getTitle()));
    }

    @Test
    public void listAllBooksTest() {
        books.add(book);
        when(bookDaoMock.listAllBooks()).thenReturn(books);
        books = bookDaoMock.listAllBooks();
        verify(bookDaoMock).listAllBooks();
        assertEquals(books, bookDaoMock.listAllBooks());
    }
}