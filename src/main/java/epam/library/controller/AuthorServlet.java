package epam.library.controller;

import epam.library.dao.AuthorDao;
import epam.library.dao.AuthorDaoImp;
import epam.library.model.Author;
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

@WebServlet(urlPatterns ={"/author/*", "/author/find/*"})
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
  /*              case "/reader/add":
                    addReader(request, response);
                    break;
                case "/reader/delete":
                    deleteReader(request, response);
                    break;
                case "/reader/edit":
                    showEditForm(request, response);
                    break;
                case "/reader/find":
                    findReader(request, response);
                    break;
                case "/reader/update":
                    updateReader(request, response);
                    break;*/
                default:
                    listAuthor(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void findAuthor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
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

}
