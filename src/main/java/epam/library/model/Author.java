package epam.library.model;

import java.util.List;

public class Author {
    private long id;
    private String name;
    private List<Book> bookList;

    public Author() {
    }

    public Author(long id, String name, List<Book> bookList) {
        this.id = id;
        this.name = name;
        this.bookList = bookList;
    }

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String authorName) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
