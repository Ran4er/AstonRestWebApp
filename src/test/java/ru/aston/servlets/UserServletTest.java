package ru.aston.servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aston.model.User;
import ru.aston.service.UserService;

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

public class UserServletTest {

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private UserServlet userServlet;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        users.add(new User("testUser", "testPassword"));
        when(userService.getAllUsers()).thenReturn(users);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        userServlet.doGet(request, response);

        verify(userService, times(1)).getAllUsers();
        verify(request, times(1)).setAttribute("users", users);
        verify(requestDispatcher, times(1)).forward(request, response);

        String result = stringWriter.toString().trim();
        assertEquals("", result);
    }

    @Test
    void testDoPost() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("testPassword");

        userServlet.doPost(request, response);

        verify(userService, times(1)).addUser(any(User.class));
        verify(response, times(1)).sendRedirect("users");
    }
}
