package epam.library.controller;

import epam.library.dao.ReaderDao;
import epam.library.dao.ReaderDaoImp;
import epam.library.model.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

@WebServlet(urlPatterns ={"/reader/*", "/reader/find/*", "/reader/edit/*", "/reader/add/*","/reader/update/*", "/reader/new/*", "/reader/delete/*", "/reader/list"})
public class ReaderServlet extends HttpServlet {
    private ReaderDao readerDao = new ReaderDaoImp();
    final static Logger logger = LogManager.getLogger(ReaderServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        response.setContentType("text/html");

        switch (action) {
            case "/reader/new":
                showNewReaderForm(request, response);
                break;
            case "/reader/add":
                addReader(request, response);
                break;
            case "/reader/delete":
                deleteReader(request, response);
                break;
            case "/reader/edit":
                showEditReaderForm(request, response);
                break;
            case "/reader/find":
                findReader(request, response);
                break;
            case "/reader/update":
                updateReader(request, response);
                break;
            default:
                listReader(request, response);
                break;
        }
    }

    private void listReader(HttpServletRequest request, HttpServletResponse response) {
        List<Reader> listReader = readerDao.listAllReaders();
        request.setAttribute("listReader", listReader);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/indexReader.jsp");
        try {
            dispatcher.forward(request, response);
        } catch ( IOException | ServletException e) {
            logger.error("List All Reader Error " + e.getMessage());
        }

    }

    private void showNewReaderForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reader.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Show New Reader Form Error " + e.getMessage());
        }
    }

    private void showEditReaderForm(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter("id"));
        Reader edReader = readerDao.editReader(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reader.jsp");
        request.setAttribute("reader", edReader);
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Show Edit Reader Form Error " + e.getMessage());
        }

    }

    private void addReader(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        Reader newReader = new Reader(name, address, phone);
        readerDao.addReader(newReader);
        try {
            response.sendRedirect("list");
            logger.info("Add reader: " + newReader.getName());
        } catch (IOException e) {
            logger.error("Add Reader Error " + e.getMessage());
        }
    }

    private void findReader(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        Reader findReader = readerDao.findReaderByName(name);
        List<Reader> listReader = new ArrayList<>();
        listReader.add(findReader);
        request.setAttribute("listReader", listReader);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/indexReader.jsp");
        try {
            dispatcher.forward(request, response);
            logger.info("Find reader: " + findReader.getName());
        } catch (IOException | ServletException e) {
            logger.error("Find Reader Error " + e.getMessage());
        }

    }

    private void updateReader(HttpServletRequest request, HttpServletResponse response){
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Reader reader = new Reader(id, name, address, phone);
        readerDao.updateReader(reader);
        try {
            response.sendRedirect("list");
            logger.info("Update reader: " + reader.getName());
        } catch (IOException e) {
            logger.error("Update Reader Error " + e.getMessage());
        }
    }

    private void deleteReader(HttpServletRequest request, HttpServletResponse response){
        long id = Long.parseLong(request.getParameter("id"));
        Reader reader = new Reader(id);
        logger.info("Delete reader: " + reader.getName());
        readerDao.deleteReader(reader);
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            logger.error("Delete Reader Error " + e.getMessage());
        }

    }
}
