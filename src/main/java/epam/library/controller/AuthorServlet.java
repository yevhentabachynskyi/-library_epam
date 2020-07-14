package epam.library.controller;

import epam.library.dao.AuthorDao;
import epam.library.dao.AuthorDaoImp;
import epam.library.model.Author;
import epam.library.model.Book;;
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

@WebServlet(urlPatterns = {"/author/*", "/author/find/*", "/book/listBook"})
public class AuthorServlet extends HttpServlet {
    private AuthorDao authorDao = new AuthorDaoImp();
    final static Logger logger = LogManager.getLogger(AuthorServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();

        response.setContentType("text/html");

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
    }

    private void findAuthor(HttpServletRequest request, HttpServletResponse response) {
        String name = null;
        Author findAuthor = null;
        List<Author> listAuthors = null;
        try {
            name = request.getParameter("name");
            System.out.println(name);
            findAuthor = authorDao.findAuthorByName(name);
            listAuthors = new ArrayList<>();
            listAuthors.add(findAuthor);
            request.setAttribute("listAuthors", listAuthors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/author.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Find Author Error " + e.getMessage());
        }

    }

    private void listAuthor(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Author> listAuthors = authorDao.listAllAuthors();
            request.setAttribute("listAuthors", listAuthors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/author.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("List Author Error " + e.getMessage());
        }
    }

    private void listBookByAuthor(HttpServletRequest request, HttpServletResponse response) {
        String name = null;
        List<Book> listBooks = null;
        try {
            name = request.getParameter("name");
            listBooks = authorDao.listAllBooksByAuthor(name);
            request.setAttribute("listBooks", listBooks);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/indexBook.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("List Book By Author Error " + e.getMessage());
        }

    }

}
