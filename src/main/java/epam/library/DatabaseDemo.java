package epam.library;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class DatabaseDemo extends HttpServlet {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DATABASE_URL = "jdbc:h2:~/library";
    static final String DATABASE_USER = "root";
    static final String DATABASE_PASSWORD = "root";
    static final String GET_ALL_DEVELOPERS_RECORDS = "SELECT ID, TITLE, NUMBER FROM BOOK";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Database Demo";
        String docType = "<!DOCTYPE html>";

        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(GET_ALL_DEVELOPERS_RECORDS);

            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>DEVELOPERS DATA</h1>");
            writer.println("<br/>");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
               // int number = resultSet.getInt(6);


                writer.println("ID: " + id);
                writer.println("First name: " + name + "<br/>");
                //writer.println("Salary: $" + number + "<br/>");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

