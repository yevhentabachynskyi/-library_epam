package epam.library.dao;

import epam.library.db.DatabaseConnection;
import epam.library.model.Reader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class  ReaderDaoImpTest {
    ReaderDaoImp readerDaoMock = mock(ReaderDaoImp.class);
    private Reader reader  = reader = new Reader("Reader", "City","380674888845");
    private List<Reader> readers = new ArrayList<>();


    @Test
    public void addReader() {
        when(readerDaoMock.addReader((Reader) isNotNull())).thenReturn(anyBoolean());
        when(readerDaoMock.addReader(reader)).thenReturn(true);
    }

    @Test
    public void updateReader() {
        when(readerDaoMock.updateReader((Reader) isNotNull())).thenReturn(anyBoolean());
        when(readerDaoMock.updateReader(reader)).thenReturn(true);
    }

    @Test
    public void editReader() {
        when(readerDaoMock.editReader(reader.getId())).thenReturn(reader);
        reader = readerDaoMock.editReader(reader.getId());
        assertEquals(reader, readerDaoMock.editReader(reader.getId()));
    }

    @Test
    public void deleteReader() {
        when(readerDaoMock.deleteReader((Reader) isNotNull())).thenReturn(anyBoolean());
        when(readerDaoMock.deleteReader(reader)).thenReturn(true);
    }

    @Test
    public void findReaderByName() {
        when(readerDaoMock.findReaderByName(reader.getName())).thenReturn(reader);
        reader = readerDaoMock.findReaderByName(reader.getName());
        assertEquals(reader, readerDaoMock.findReaderByName(reader.getName()));
    }

    @Test
    public void listAllReaders() {
        readers.add(reader);
        when(readerDaoMock.listAllReaders()).thenReturn(readers);
        readers = readerDaoMock.listAllReaders();
        assertEquals(readers, readerDaoMock.listAllReaders());
    }
}