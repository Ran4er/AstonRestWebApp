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

    private static final String TRUNCATE_ORDERS = "TRUNCATE TABLE orders RESTART IDENTITY CASCADE";

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest").withInitScript("init.sql");

    private static OrderDAOImpl orderDAO;

    private static Connection connection;

    @BeforeAll
    static void setup() throws SQLException {
        postgresContainer.start();
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        orderDAO = new OrderDAOImpl(connection);
    }

    @AfterAll
    static void cleanup() throws SQLException {
        //truncateTable(TRUNCATE_ORDERS);
        connection.close();
        postgresContainer.stop();
    }

    /*public static void truncateTable(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    @Test
    void addOrder() {
        Order order = new Order(1,1, 1, 1);
        orderDAO.addOrder(order);
        assertNotNull(order.getId());
    }

    @Test
    void getOrderById() {
        Order order = orderDAO.getOrderById(2);
        assertNotNull(order);
    }

    @Test
    void getAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        assertEquals(5, orders.size());
    }

    @Test
    void updateOrder() {
        Order order = orderDAO.getOrderById(2);
        order.setQuantity(2);
        orderDAO.updateOrder(order);
        Order updatedOrder = orderDAO.getOrderById(2);
        assertEquals(2, updatedOrder.getQuantity());
    }

    @Test
    void deleteOrder() {
        orderDAO.deleteOrder(1);
        List<Order> orders = orderDAO.getAllOrders();
        assertEquals(5, orders.size());
    }
}
