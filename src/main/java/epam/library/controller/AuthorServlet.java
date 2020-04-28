package epam.library.controller;

import epam.library.dao.AuthorDao;
import epam.library.dao.AuthorDaoImp;
import epam.library.dao.BookDaoImp;
import epam.library.model.Author;
import epam.library.model.Book;
import epam.library.model.Reader;

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

@WebServlet(urlPatterns ={"/author/*", "/author/find/*", "/book/listBook"})
public class AuthorServlet extends HttpServlet {

    private AuthorDao authorDao = new AuthorDaoImp();

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
                case "/author/find":
                    findAuthor(request, response);
                    break;
                case "/book/listBook":
                    listBookByAuthor(request, response);
                    break;
                default:
                    listAuthor(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void findAuthor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("author.name");
        System.out.println(name);
        Author findAuthor = authorDao.findAuthorByName(name);
        List<Author> listAuthors = new ArrayList<>();
        listAuthors.add(findAuthor);
        request.setAttribute("listAuthors", listAuthors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/author.jsp");
        dispatcher.forward(request, response);
    }

    private void listAuthor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Author> listAuthors = authorDao.listAllAuthors();
        request.setAttribute("listAuthors", listAuthors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/author.jsp");
        dispatcher.forward(request, response);
    }
    private void listBookByAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        List<Book> listBooks = authorDao.listAllBooksByAuthor(name);
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/indexBook.jsp");
        dispatcher.forward(request, response);
    }

}
