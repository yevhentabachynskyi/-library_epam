package epam.library.dao;

import epam.library.model.Reader;

import java.sql.SQLException;
import java.util.List;

public interface ReaderDao {
        boolean addReader(Reader reader) throws SQLException;

        boolean updateReader(Reader reader) throws SQLException;

        Reader editReader(long id) throws SQLException;

        boolean deleteReader(Reader reader) throws SQLException;

        Reader findReaderById(Long id);

        List<Reader> listAllReaders();
}
