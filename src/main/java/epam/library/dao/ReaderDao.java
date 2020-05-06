package epam.library.dao;

import epam.library.model.Reader;

import java.sql.SQLException;
import java.util.List;

public interface ReaderDao {
    boolean addReader(Reader reader);

    boolean updateReader(Reader reader);

    Reader editReader(long id);

    boolean deleteReader(Reader reader);

    Reader findReaderByName(String name);

    List<Reader> listAllReaders();
}
