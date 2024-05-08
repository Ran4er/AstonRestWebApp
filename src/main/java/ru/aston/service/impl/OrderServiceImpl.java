package ru.aston.service.impl;

import java.util.List;

import ru.aston.dao.OrderDAO;
import ru.aston.model.Order;
import ru.aston.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
    }
}
