package epam.library.controller;


import epam.library.dao.BookDao;
import epam.library.dao.BookDaoImp;
import epam.library.model.Author;
import epam.library.model.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/book/*", "/book/find/*", "/book/edit/*", "/book/add/*", "/book/update/*", "/book/new/*", "/book/delete/*", "/book/list"})
public class BookServlet extends HttpServlet {
    private BookDao bookDao = new BookDaoImp();
    final static Logger logger = LogManager.getLogger(BookServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        response.setContentType("text/html");

        switch (action) {
            case "/book/new":
                showNewBookForm(request, response);
                break;
            case "/book/add":
                addBook(request, response);
                break;
            case "/book/delete":
                deleteBook(request, response);
                break;
            case "/book/edit":
                showEditBookForm(request, response);
                break;
            case "/book/find":
                findBook(request, response);
                break;
            case "/book/update":
                updateBook(request, response);
                break;
            default:
                listBook(request, response);
                break;
        }
    }


    private void listBook(HttpServletRequest request, HttpServletResponse response) {
        List<Book> listBooks = bookDao.listAllBooks();
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/indexBook.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("List Book Error " + e.getMessage());
        }
    }

    private void showNewBookForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/book.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Show New Book Form Error " + e.getMessage());
        }
    }

    private void showEditBookForm(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter("id"));
        Book book = bookDao.editBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/book.jsp");
        request.setAttribute("book", book);
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Show New Book Form Error " + e.getMessage());
        }

    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String authorName = request.getParameter("authorName");
        String genre = request.getParameter("genre");
        int publishYear = Integer.parseInt(request.getParameter("publishYear"));
        int number = Integer.parseInt(request.getParameter("number"));
        Author author = new Author(authorName);
        Book book = new Book(title, authorName, genre, publishYear, number);
        bookDao.addBook(book, author);
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            logger.error("Add New Book Error " + e.getMessage());
        }
    }

    private void findBook(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        Book findBook = bookDao.findBookByName(title);
        List<Book> listBooks = new ArrayList<>();
        listBooks.add(findBook);
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/indexBook.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Find Book Error " + e.getMessage());
        }


    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String authorName = request.getParameter("authorName");
        String genre = request.getParameter("genre");
        int publishYear = Integer.parseInt(request.getParameter("publishYear"));
        int number = Integer.parseInt(request.getParameter("number"));
        Book book = new Book(id, title, authorName, genre, publishYear, number);
        bookDao.updateBook(book);
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            logger.error("Update Book Error " + e.getMessage());
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter("id"));
        Book book = new Book(id);
        bookDao.deleteBook(book);
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            logger.error("Delete Book Error " + e.getMessage());
        }

    }
}
