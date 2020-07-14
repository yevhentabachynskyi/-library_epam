package epam.library.dao;

import epam.library.exception.BooksOverException;
import epam.library.model.Book;
import epam.library.model.Giving;
import epam.library.model.Reader;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class GivingDaoImpTest {
    private GivingDao givingDaoMock = mock(GivingDaoImp.class);
    private Giving giving = mock(Giving.class);
    private Book book = new Book("Title Test", "Author Test","Genre Test", 2000, 2);
    private Reader reader = new Reader("Reader", "City","380674888845");
    Map<Long, Map<Giving, Book>> giveMap = new HashMap<>();

    @Test
    public void giveBookTest() throws BooksOverException {
        when(givingDaoMock.giveBook(giving)).thenReturn(true);
        doThrow(new BooksOverException()).when(givingDaoMock).giveBook(giving);
    }

    @Test
    public void returnBookTest() {
        when(givingDaoMock.returnBook(book.getId())).thenReturn(true);
    }

    @Test
    public void returnListTest() {
       when(givingDaoMock.returnList(reader.getId())).thenReturn(giveMap);
    }

    @Test
    public void allGivingListTest() {
        when(givingDaoMock.allGivingList(reader.getId())).thenReturn(giveMap);
    }
}