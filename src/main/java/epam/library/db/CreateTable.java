package epam.library.db;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.h2.tools.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTable {
    final static Logger logger = LogManager.getLogger(CreateTable.class);

    public void createDB() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        Statement st = conn.createStatement();
        PreparedStatement preparedStatement = conn.prepareStatement("SHOW TABLES FROM PUBLIC;");
        ResultSet rs = preparedStatement.getResultSet();
        DatabaseMetaData dbm = conn.getMetaData();
        rs = dbm.getTables(null, "PUBLIC", "BOOK", null);
        if (rs.next()) {
            logger.info("Table BOOK exists");
        } else {
            logger.info("Table BOOK does not exist");
            st.execute("create table  BOOK\n" +
                    "(\n" +
                    "    ID           BIGINT auto_increment,\n" +
                    "    TITLE        VARCHAR(50) not null,\n" +
                    "    NAME_AUTHOR  VARCHAR(50) not null,\n" +
                    "    GENRE        ENUM('NOVEL', 'FANTASY', 'HISTORY', 'DRAMA', 'LYRICS') not null,\n" +
                    "    PUBLISH_YEAR INT         not null,\n" +
                    "    NUMBER       INT         not null,\n" +
                    "    constraint BOOK_PK\n" +
                    "        primary key (ID)\n" +
                    ");");
        }
        rs = dbm.getTables(null, "PUBLIC", "AUTHOR", null);
        if (rs.next()) {
            logger.info("Table AUTHOR exists");

        } else {
            logger.info("Table AUTHOR does not exist");
            st.execute("create table AUTHOR\n" +
                    "(\n" +
                    "    ID   BIGINT auto_increment,\n" +
                    "    NAME VARCHAR(50) unique not null,\n" +
                    "    constraint AUTHOR_PK\n" +
                    "        primary key (ID)\n" +
                    ");");

        }
        rs = dbm.getTables(null, "PUBLIC", "READER", null);
        if (rs.next()) {
            logger.info("Table READER exists");

        } else {
            logger.info("Table READER does not exist");
            st.execute("create table READER\n" +
                    "(\n" +
                    "    ID         BIGINT auto_increment,\n" +
                    "    NAME        VARCHAR(50) not null,\n" +
                    "    ADDRESS VARCHAR(50) not null,\n" +
                    "    PHONE      DECIMAL  not null,\n" +
                    "    constraint READER_PK\n" +
                    "        primary key (ID)\n" +
                    ");");
        }
        rs = dbm.getTables(null, "PUBLIC", "GIVING", null);
        if (rs.next()) {
            logger.info("Table GIVING exists");

        } else {
            logger.info("Table GIVING does not exist");
            st.execute("create table GIVING\n" +
                    "(\n" +
                    "    ID         BIGINT auto_increment,\n" +
                    "    ID_BOOK       BIGINT  not null,\n" +
                    "    ID_READER BIGINT not null,\n" +
                    "    DATE_GIVE    DATE,\n" +
                    "    DATE_RETURN    DATE,\n" +
                    "    constraint GIVING_PK\n" +
                    "        primary key (ID)\n" +
                    ");");
        }
    }
}

