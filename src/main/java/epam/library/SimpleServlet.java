package epam.library;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet extends HttpServlet {

    private String message;
    private static String index = "/WEB-INF/view/indexReader.jsp";

    public void init() throws ServletException {
        message = "This is simple servlet message";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        PrintWriter messageWriter = response.getWriter();
//        messageWriter.println("<h1>" + message + "<h1>");
        request.getRequestDispatcher(index).forward(request, response);
    }

    public void destroy() {

    }
}