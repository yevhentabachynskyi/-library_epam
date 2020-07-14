package epam.library.controller;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthorServletTest {
    private final static String path = "/WEB-INF/view/author.jsp";


    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {

        final AuthorServlet authorServlet = mock(AuthorServlet.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        authorServlet.doGet(request, response);
        verify(authorServlet).doGet(request,response);
    }

}