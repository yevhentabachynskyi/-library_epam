package epam.library.model;

import java.util.Date;

public class Book {
    private long id;
    private String title;
    private String authorName;
    private String genre;
    private int publishYear;
    private int number;

    public Book(long id, String title, String authorName, String genre, int publishYear, int number) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.genre = genre;
        this.publishYear = publishYear;
        this.number = number;
    }

    public Book(String title, String authorName, String genre, int publishYear, int number) {
        this.title = title;
        this.authorName = authorName;
        this.genre = genre;
        this.publishYear = publishYear;
        this.number = number;
    }

    public Book(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
