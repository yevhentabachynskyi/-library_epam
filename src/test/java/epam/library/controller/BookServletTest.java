package epam.library.controller;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BookServletTest {
    private final static String path = "/WEB-INF/view/book.jsp";


    @Test
    public void whenCallDoGetThenServletReturnBookPage() throws ServletException, IOException {

        final ReaderServlet readerServlet = mock(ReaderServlet.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        readerServlet.doGet(request, response);
        verify(readerServlet ).doGet(request,response);
    }

}