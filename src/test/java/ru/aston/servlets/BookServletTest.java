package ru.aston.servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aston.model.Book;
import ru.aston.service.BookService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BookServletTest {

    @Mock
    private BookService bookService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private BookServlet bookServlet;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Test Book", 0, 0, 10.99));
        when(bookService.getAllBooks()).thenReturn(books);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        bookServlet.doGet(request, response);

        verify(bookService, times(1)).getAllBooks();
        verify(request, times(1)).setAttribute("books", books);
        verify(requestDispatcher, times(1)).forward(request, response);

        String result = stringWriter.toString().trim();
        assertEquals("", result);
    }

    @Test
    void testDoPost() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("title")).thenReturn("Test Book");
        when(request.getParameter("author")).thenReturn("0");
        when(request.getParameter("genre")).thenReturn("0");
        when(request.getParameter("price")).thenReturn("10.99");

        bookServlet.doPost(request, response);

        verify(bookService, times(1)).addBook(any(Book.class));
        verify(response, times(1)).sendRedirect("books");
    }
}
