package epam.library.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class Giving {
    private long idGive;
    private long idBook;
    private long idReader;
    private Date dateGive;
    private Date dateReturn;

    public Giving(long idGive, long idBook, long idReader, Date dateGive, Date dateReturn) {
        this.idGive = idGive;
        this.idBook = idBook;
        this.idReader = idReader;
        this.dateGive = dateGive;
        this.dateReturn = dateReturn;
    }

    public Giving(long idBook, long idReader, Date dateGive, Date dateReturn) {
        this.idBook = idBook;
        this.idReader = idReader;
        this.dateGive = dateGive;
        this.dateReturn = dateReturn;

    }
    public Giving(long idBook, long idReader) {
        this.idBook = idBook;
        this.idReader = idReader;
    }

    public long getIdGive() {
        return idGive;
    }

    public void setIdGive(long idGive) {
        this.idGive = idGive;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public long getIdReader() {
        return idReader;
    }

    public void setIdReader(long idReader) {
        this.idReader = idReader;
    }

    public Date getDateGive() {
        return dateGive;
    }

    public void setDateGive(Date dateGive) {
        this.dateGive = dateGive;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }
}
