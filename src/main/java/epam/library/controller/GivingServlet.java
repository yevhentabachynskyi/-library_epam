package epam.library.controller;

import epam.library.dao.BookDao;
import epam.library.dao.BookDaoImp;
import epam.library.dao.GivingDao;
import epam.library.dao.GivingDaoImp;
import epam.library.exception.AlreadyHasBookException;
import epam.library.exception.BooksOverException;
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
import java.util.*;

@WebServlet(urlPatterns = {"/reader/give", "/reader/return", "/reader/give/findBook", "/reader/give/givingBook", "/reader/return/returnBook", "/reader/allListBook"})
public class GivingServlet extends HttpServlet {
    private GivingDao givingDao = new GivingDaoImp();
    private BookDao bookDao = new BookDaoImp();
    final static Logger logger = LogManager.getLogger(GivingServlet.class);

    private long idReader;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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
        } catch (ServletException | IOException e) {
            logger.error("Give List Book Error " + e.getMessage());
        }
    }

    private void returnListBook(HttpServletRequest request, HttpServletResponse response) {
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

    private void allListBook(HttpServletRequest request, HttpServletResponse response) {
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
        Map<Long, Map<Giving, Book>> giveMap = new HashMap<>();
        Map<Giving, Book> giveBooks = new HashMap<>();
        String messageOver = "The books are over";
        String messageAlready = "The reader already has this book";
        long idBook = Long.parseLong(request.getParameter("idBook"));
        Giving newGiving = new Giving(idBook, idReader);
        giveMap.putAll(givingDao.returnList(idReader));
        giveBooks.putAll(giveMap.get(idReader));
        try {
            if (!giveBooks.isEmpty()) {
                for (Map.Entry<Giving, Book> entry : giveBooks.entrySet()) {
                    if (entry.getKey().getIdBook() == newGiving.getIdBook()) {
                        throw new AlreadyHasBookException();
                    }
                }
            }
            givingDao.giveBook(newGiving);
        } catch (AlreadyHasBookException e) {
            request.setAttribute("messageAlready", messageAlready);
            try {
                request.getRequestDispatcher("/WEB-INF/view/give.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.toString());
        } catch (BooksOverException e) {
            request.setAttribute("messageOver", messageOver);
            try {
                request.getRequestDispatcher("/WEB-INF/view/give.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                logger.error(ex.getMessage());
            }
            logger.error(e.toString());
        }
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            logger.error("Give Book Error " + e.getMessage());
        }
    }

    private void returnBook(HttpServletRequest request, HttpServletResponse response) {
        long idBook = Long.parseLong(request.getParameter("idBook"));
        givingDao.returnBook(idBook);
        try {
            response.sendRedirect("list");
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
