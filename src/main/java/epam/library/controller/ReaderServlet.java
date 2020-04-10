package epam.library.controller;

import epam.library.dao.ReaderDao;
import epam.library.dao.ReaderDaoImp;
import epam.library.model.Reader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class ReaderServlet extends HttpServlet {
    private ReaderDao readerDao = new ReaderDaoImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/add":
                    addReader(request, response);
                    break;
                case "/delete":
                    deleteReader(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateReader(request, response);
                    break;
                default:
                    listReader(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listReader(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Reader> listReader = readerDao.listAllReaders();
        request.setAttribute("listReader", listReader);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
        dispatcher.forward(request, response);
        System.out.println("listReader");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reader.jsp");
        dispatcher.forward(request, response);
        System.out.println("SHOW");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Reader edReader = readerDao.editReader(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reader.jsp");
        request.setAttribute("reader", edReader);
        dispatcher.forward(request, response);

    }

    private void addReader(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));
        Reader newReader = new Reader(name, address, phone);
        readerDao.addReader(newReader);
        response.sendRedirect("list");
    }

    private void updateReader(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));

        Reader reader = new Reader(id, name, address, phone);
        readerDao.updateReader(reader);
        response.sendRedirect("list");
    }

    private void deleteReader(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Reader reader = new Reader(id);
        readerDao.deleteReader(reader);
        response.sendRedirect("list");

    }
}
