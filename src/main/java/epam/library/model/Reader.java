package epam.library.model;

import java.util.List;

public class Reader {
    private long id;
    private String name;
    private String address;
    private String phone;
    private List<Book> bookList;


    public Reader() {
    }
    public Reader(long id) {
        this.id = id;
    }
    public Reader(long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Reader(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Reader(long id, String name, String address, String phone, List<Book> bookList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
