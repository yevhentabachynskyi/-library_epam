package epam.library.dao;

import epam.library.exception.BooksOverException;
import epam.library.model.Book;
import epam.library.model.Giving;
import epam.library.model.Reader;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;


public interface GivingDao {
    boolean giveBook (Giving giving) throws BooksOverException;
    boolean returnBook(Long idBook);
    Map<Long, Map<Giving,Book>> returnList(long idReader);
    Map<Long, Map<Giving,Book>> allGivingList(long idReader);

}
