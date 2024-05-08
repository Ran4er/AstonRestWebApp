package ru.aston.service;

import java.util.List;

import ru.aston.model.Order;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(int id);
    void addOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(int id);
}
