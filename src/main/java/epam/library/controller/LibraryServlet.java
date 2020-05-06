package epam.library.controller;


import epam.library.db.Database;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class LibraryServlet extends HttpServlet {
    private static String index = "/WEB-INF/view/libraryPage.jsp";
    final static Logger logger = LogManager.getLogger(LibraryServlet .class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        try {
            request.getRequestDispatcher(index).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.warn(e.getMessage());
        }

    }

}