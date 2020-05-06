package epam.library.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class Giving {
    private long id;
    private long idBook;
    private long idReader;
    private Date dateGive;
    private Date dateReturn;


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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
