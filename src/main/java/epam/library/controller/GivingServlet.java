package epam.library.controller;

import epam.library.dao.BookDao;
import epam.library.dao.BookDaoImp;
import epam.library.dao.GivingDao;
import epam.library.dao.GivingDaoImp;
import epam.library.model.Book;
import epam.library.model.Giving;
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
import java.util.Map;

@WebServlet(urlPatterns = {"/reader/give", "/reader/return", "/reader/give/findBook", "/reader/give/givingBook", "/reader/return/returnBook", "/reader/allListBook"})
public class GivingServlet extends HttpServlet {
    private GivingDao givingDao = new GivingDaoImp();
    private BookDao bookDao = new BookDaoImp();
    final static Logger logger = LogManager.getLogger(GivingServlet.class);

    private long idReader;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        response.setContentType("text/html");

        switch (action) {
            case "/reader/give":
                giveListBook(request, response);
                break;
            case "/reader/return":
                returnListBook(request, response);
                break;
            case "/reader/give/givingBook":
                giveBook(request, response);
                break;
            case "/reader/return/returnBook":
                returnBook(request, response);
                break;
            case "/reader/allListBook":
                allListBook(request, response);
                break;
            case "/reader/give/findBook":
                findBookGive(request, response);
                break;
        }
    }

    private void giveListBook(HttpServletRequest request, HttpServletResponse response) {
        List<Book> listBooks = bookDao.listAllBooks();
        idReader = Long.parseLong(request.getParameter("idReader"));
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/give.jsp");
        try {
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e) {
            logger.error("Give List Book Error " + e.getMessage());
        }
    }

    private void returnListBook(HttpServletRequest request, HttpServletResponse response){
        idReader = Long.parseLong(request.getParameter("idReader"));
        System.out.println(idReader + " return servlet");
        Map<Long, Map<Giving, Book>> giveMap = givingDao.returnList(idReader);
        Map<Giving, Book> giveBooks = giveMap.get(idReader);
        System.out.println(giveBooks);
        request.setAttribute("giveMap", giveBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/return.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Return List Book Error " + e.getMessage());
        }
    }

    private void allListBook(HttpServletRequest request, HttpServletResponse response){
        idReader = Long.parseLong(request.getParameter("idReader"));
        System.out.println(idReader + " return servlet");
        Map<Long, Map<Giving, Book>> giveMap = givingDao.allGivingList(idReader);
        Map<Giving, Book> giveBooks = giveMap.get(idReader);
        System.out.println(giveBooks);
        request.setAttribute("giveMap", giveBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/readerBooks.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("All List Read Book Error " + e.getMessage());
        }
    }


    private void giveBook(HttpServletRequest request, HttpServletResponse response) {
        long idBook = Long.parseLong(request.getParameter("idBook"));
        System.out.println(idBook);
        Giving newGiving = new Giving(idBook, idReader);
        givingDao.giveBook(newGiving);
        System.out.println("GIVE");
        try {
            response.sendRedirect("list");
            logger.info("Giving book: " + new Book(idBook).getTitle());
        } catch (IOException e) {
            logger.error("Give Book Error " + e.getMessage());
        }
    }

    private void returnBook(HttpServletRequest request, HttpServletResponse response) {
        long idBook = Long.parseLong(request.getParameter("idBook"));
        givingDao.returnBook(idBook);
        System.out.println("RETURN");
        try {
            response.sendRedirect("list");
            logger.info("Return book: " + new Book(idBook).getTitle());
        } catch (IOException e) {
            logger.error("Return Book Error " + e.getMessage());
        }
    }

    public void findBookGive(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        Book findBook = bookDao.findBookByName(title);
        List<Book> listBooks = new ArrayList<>();
        listBooks.add(findBook);
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/give.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Find Giving Book Error " + e.getMessage());
        }

    }


}
