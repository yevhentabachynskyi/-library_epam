package epam.library.dao;

import epam.library.model.Author;
import epam.library.model.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthorDaoImpTest {

    private AuthorDaoImp authorDaoMock = mock(AuthorDaoImp.class);
    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private Author author = new Author("Author Test");

    @Test
    public void findAuthorByNameTest() {
        when(authorDaoMock.findAuthorByName(author.getName())).thenReturn(author);
        author = authorDaoMock.findAuthorByName(author.getName());
        verify(authorDaoMock).findAuthorByName(author.getName());
        assertEquals(author, authorDaoMock.findAuthorByName(author.getName()));
        assertEquals(new AuthorDaoImp() != null, true);
    }

    @Test
    public void listAllAuthorsTest() {
        authors.add(author);
        when(authorDaoMock.listAllAuthors()).thenReturn(authors);
        authors = authorDaoMock.listAllAuthors();
        verify(authorDaoMock).listAllAuthors();
        assertEquals(authors, authorDaoMock.listAllAuthors());
        assertEquals(new AuthorDaoImp() != null, true);
    }

    @Test
    public void listAllBooksByAuthorTest() {
        when(authorDaoMock.listAllBooksByAuthor(author.getName())).thenReturn(author.getBookList());
        books = authorDaoMock.listAllBooksByAuthor(author.getName());
        verify(authorDaoMock).listAllBooksByAuthor(author.getName());
        assertEquals(books, authorDaoMock.listAllBooksByAuthor(author.getName()));
    }
}