package ru.aston.servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aston.model.Order;
import ru.aston.service.OrderService;

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

public class OrderServletTest {

    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private OrderServlet orderServlet;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1,1, 1, 1));
        when(orderService.getAllOrders()).thenReturn(orders);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        orderServlet.doGet(request, response);

        verify(orderService, times(1)).getAllOrders();
        verify(request, times(1)).setAttribute("orders", orders);
        verify(requestDispatcher, times(1)).forward(request, response);

        String result = stringWriter.toString().trim();
        assertEquals("", result);
    }

    @Test
    void testDoPost() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("quantity")).thenReturn("1");

        orderServlet.doPost(request, response);

        verify(orderService, times(1)).addOrder(any(Order.class));
        verify(response, times(1)).sendRedirect("orders");
    }
}
