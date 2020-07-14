package epam.library.dao;

import epam.library.db.DatabaseConnection;
import epam.library.exception.BooksOverException;
import epam.library.model.Book;
import epam.library.model.Giving;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;;
import java.util.Map;


public class GivingDaoImp implements GivingDao {
    private Connection connection = DatabaseConnection.getConnection();
    final static Logger logger = LogManager.getLogger(GivingDaoImp.class);

    @Override
    public boolean giveBook(Giving giving) throws BooksOverException {
        boolean rowInserted = false;
        Book book = null;
        String sqlBook = "SELECT * FROM BOOK WHERE BOOK.ID = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlBook);
            statement.setLong(1, giving.getIdBook());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("TITLE");
                String author = resultSet.getString("NAME_AUTHOR");
                String genre = resultSet.getString("GENRE");
                int year = resultSet.getInt("PUBLISH_YEAR");
                int num = resultSet.getInt("NUMBER");
                book = new Book(giving.getIdBook(), name, author, genre, year, num);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        if (book.getNumber() == 0) {
            rowInserted = false;
            throw new BooksOverException();
        } else {
            String sql = "INSERT INTO GIVING (ID_BOOK, ID_READER, DATE_GIVE) VALUES(?,?,?);" +
                    "UPDATE BOOK SET NUMBER = NUMBER-1 where ID = " + giving.getIdBook();
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, giving.getIdBook());
                preparedStatement.setLong(2, giving.getIdReader());
                preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
                rowInserted = preparedStatement.executeUpdate() > 0;
            } catch (SQLException e) {
                logger.error("Give Book Error " + e.getMessage());
            }
        }
        return rowInserted;
    }

    @Override
    public boolean returnBook(Long idBook) {
        String sql = "UPDATE GIVING SET DATE_RETURN = ? WHERE ID_BOOK = " + idBook + ";" +
                "UPDATE BOOK SET NUMBER = NUMBER+1 where ID = " + idBook;

        PreparedStatement preparedStatement = null;
        boolean rowInserted = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
            rowInserted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Return Book Error " + e.getMessage());
        }

        return rowInserted;
    }

    @Override
    public Map<Long, Map<Giving, Book>> returnList(long idReader) {
        Map<Long, Map<Giving, Book>> giveMap = new HashMap<>();
        HashMap<Giving, Book> giveBooks = new HashMap<>();
        Giving giving = null;
        Book book = null;
        String sqlBook = "SELECT * FROM BOOK A JOIN GIVING B on A.ID = B.ID_BOOK WHERE ID_READER = ? AND DATE_RETURN IS NULL; ";

        PreparedStatement statementReader = null;
        try {
            statementReader = connection.prepareStatement(sqlBook);
            statementReader.setLong(1, idReader);
            ResultSet resultSet = statementReader.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String title = resultSet.getString("TITLE");
                String author = resultSet.getString("NAME_AUTHOR");
                String genre = resultSet.getString("GENRE");
                int year = resultSet.getInt("PUBLISH_YEAR");
                int num = resultSet.getInt("NUMBER");
                Date dateGive = resultSet.getDate("DATE_GIVE");
                Date dateReturn = resultSet.getDate("DATE_RETURN");

                book = new Book(id, title, author, genre, year, num);
                giving = new Giving(id, idReader, dateGive, dateReturn);
                giveBooks.put(giving, book);
            }
            giveMap.put(idReader, giveBooks);
        } catch (SQLException e) {
            logger.error("Return List Read Book Error " + e.getMessage());
        }

        return giveMap;
    }

    @Override
    public Map<Long, Map<Giving, Book>> allGivingList(long idReader) {
        Map<Long, Map<Giving, Book>> giveMap = new HashMap<>();
        HashMap<Giving, Book> giveBooks = new HashMap<>();
        Giving giving = null;
        Book book = null;
        String sqlBook = "SELECT * FROM BOOK A JOIN GIVING B on A.ID = B.ID_BOOK WHERE ID_READER = ?; ";

        PreparedStatement statementReader = null;
        try {
            statementReader = connection.prepareStatement(sqlBook);
            statementReader.setLong(1, idReader);
            ResultSet resultSet = statementReader.executeQuery();
            while (resultSet.next()) {
                long idGive = resultSet.getLong("ID_GIVE");
                long idBook = resultSet.getLong("ID");
                String title = resultSet.getString("TITLE");
                String author = resultSet.getString("NAME_AUTHOR");
                String genre = resultSet.getString("GENRE");
                int year = resultSet.getInt("PUBLISH_YEAR");
                int num = resultSet.getInt("NUMBER");
                Date dateGive = resultSet.getDate("DATE_GIVE");
                Date dateReturn = resultSet.getDate("DATE_RETURN");

                book = new Book(idBook, title, author, genre, year, num);
                giving = new Giving(idGive, idBook, idReader, dateGive, dateReturn);
                giveBooks.put(giving, book);
            }
            giveMap.put(idReader, giveBooks);
        } catch (SQLException e) {
            logger.error("All Giving List Error " + e.getMessage());
        }

        return giveMap;
    }
}
