package epam.library.controller;

import epam.library.db.CreateTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class LibraryServlet extends HttpServlet {
    private static String index = "/WEB-INF/view/libraryPage.jsp";
    final static Logger logger = LogManager.getLogger(LibraryServlet.class);


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        CreateTable createTable = new CreateTable();
        try {
            createTable.createDB();
            logger.info("Tables create");
        } catch (SQLException e) {
            logger.info("Tables don`t create " + e.getMessage());
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        try {
            request.getRequestDispatcher(index).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.warn(e.getMessage());
        }

    }

}