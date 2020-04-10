package epam.library.controller;


import epam.library.db.Database;

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

//@WebServlet("/")
public class LibraryServlet extends HttpServlet {
    private static String index = "/WEB-INF/view/index.jsp";
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        response.setContentType("text/html");
//        PrintWriter writer = response.getWriter();
//        try {
//                Connection conn = Database.getConnection();
//                request.getRequestDispatcher(index).forward(request, response);
//            }
//
//        catch(Exception ex){
//            writer.println("Connection failed...");
//            writer.println(ex);
//        }
//        finally {
//            writer.close();
//        }
//    }
}