package epam.library.controller;


import epam.library.dao.BookDao;
import epam.library.dao.BookDaoImp;
import epam.library.model.Author;
import epam.library.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/book/*", "/book/find/*", "/book/edit/*", "/book/add/*", "/book/update/*", "/book/new/*", "/book/delete/*", "/book/list"})
public class BookServlet extends HttpServlet {
    private BookDao bookDao = new BookDaoImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        response.setContentType("text/html");

        try {
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
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBooks = bookDao.listAllBooks();
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/indexBook.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewBookForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/book.jsp");
        dispatcher.forward(request, response);
        //System.out.println("SHOW");
    }

    private void showEditBookForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Book book = bookDao.editBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/book.jsp");
        request.setAttribute("book", book);
        dispatcher.forward(request, response);

    }

    private void addBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String authorName = request.getParameter("authorName");
        String genre = request.getParameter("genre");
        int publishYear = Integer.parseInt(request.getParameter("publishYear"));
        int number = Integer.parseInt(request.getParameter("number"));
        Author author = new Author(authorName);
        Book book = new Book(title, authorName, genre, publishYear, number);
        bookDao.addBook(book, author);
        response.sendRedirect("list");
    }

    private void findBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String title = request.getParameter("title");
        Book findBook = bookDao.findBookByName(title);
        List<Book> listBooks = new ArrayList<>();
        listBooks.add(findBook);
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/indexBook.jsp");
        dispatcher.forward(request, response);


    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String authorName = request.getParameter("authorName");
        String genre = request.getParameter("genre");
        int publishYear = Integer.parseInt(request.getParameter("publishYear"));
        int number = Integer.parseInt(request.getParameter("number"));


        Book book = new Book(id, title, authorName, genre, publishYear, number);
        bookDao.updateBook(book);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Book book = new Book(id);
        bookDao.deleteBook(book);
        response.sendRedirect("list");

    }
}
