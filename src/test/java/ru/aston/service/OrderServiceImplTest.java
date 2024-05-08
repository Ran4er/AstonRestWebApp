package ru.aston.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aston.dao.OrderDAO;
import ru.aston.model.Order;
import ru.aston.service.impl.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    private OrderDAO orderDAO;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addOrder() {
        Order order = new Order(1, 1, 1);
        orderService.addOrder(order);
        verify(orderDAO, times(1)).addOrder(order);
    }

    @Test
    void getOrderById() {
        Order expectedOrder = new Order(1, 1, 1);
        when(orderDAO.getOrderById(1)).thenReturn(expectedOrder);
        Order actualOrder = orderService.getOrderById(1);
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void getAllOrders() {
        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Order(1, 1, 1));
        expectedOrders.add(new Order(2, 2, 2));
        when(orderDAO.getAllOrders()).thenReturn(expectedOrders);
        List<Order> actualOrders = orderService.getAllOrders();
        assertEquals(expectedOrders.size(), actualOrders.size());
    }

    @Test
    void updateOrder() {
        Order order = new Order(1, 1, 1);
        order.setId(1);
        orderService.updateOrder(order);
        verify(orderDAO, times(1)).updateOrder(order);
    }

    @Test
    void deleteOrder() {
        orderService.deleteOrder(1);
        verify(orderDAO, times(1)).deleteOrder(1);
    }
}
