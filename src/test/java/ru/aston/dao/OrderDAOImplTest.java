package ru.aston.dao;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.aston.dao.impl.OrderDAOImpl;
import ru.aston.model.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class OrderDAOImplTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

    private final OrderDAOImpl orderDAO = new OrderDAOImpl();

    @BeforeAll
    static void setup() {
        postgresContainer.start();
        System.setProperty("DB_URL", postgresContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgresContainer.getUsername());
        System.setProperty("DB_PASSWORD", postgresContainer.getPassword());
        try (Connection connection = DriverManager.getConnection(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword());
             var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS orders (id SERIAL PRIMARY KEY, user_id INT, book_id INT, quantity INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void cleanup() {
        postgresContainer.stop();
    }

    @Test
    void addOrder() {
        Order order = new Order(1, 1, 1);
        orderDAO.addOrder(order);
        assertNotNull(order.getId());
    }

    @Test
    void getOrderById() {
        Order order = orderDAO.getOrderById(1);
        assertNotNull(order);
    }

    @Test
    void getAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        assertEquals(1, orders.size());
    }

    @Test
    void updateOrder() {
        Order order = orderDAO.getOrderById(1);
        order.setQuantity(2);
        orderDAO.updateOrder(order);
        Order updatedOrder = orderDAO.getOrderById(1);
        assertEquals(2, updatedOrder.getQuantity());
    }

    @Test
    void deleteOrder() {
        orderDAO.deleteOrder(1);
        List<Order> orders = orderDAO.getAllOrders();
        assertEquals(0, orders.size());
    }
}
