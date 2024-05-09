package ru.aston.dao.impl;

import ru.aston.model.Order;
import ru.aston.model.User;
import ru.aston.model.Book;
import ru.aston.util.DBUtil;
import ru.aston.dao.OrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;

    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                order.setUser(user);
                Book book = new Book();
                book.setId(resultSet.getInt("book_id"));
                order.setBook(book);
                order.setQuantity(resultSet.getInt("quantity"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getOrderById(int id) {
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = new Order();
                    order.setId(resultSet.getInt("id"));
                    User user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    order.setUser(user);
                    Book book = new Book();
                    book.setId(resultSet.getInt("book_id"));
                    order.setBook(book);
                    order.setQuantity(resultSet.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void addOrder(Order order) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO orders (user_id, book_id, quantity) VALUES (?, ?, ?)")) {
            statement.setInt(1, order.getUser().getId());
            statement.setInt(2, order.getBook().getId());
            statement.setInt(3, order.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Order order) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE orders SET user_id=?, book_id=?, quantity=? WHERE id=?")) {
            statement.setInt(1, order.getUser().getId());
            statement.setInt(2, order.getBook().getId());
            statement.setInt(3, order.getQuantity());
            statement.setInt(4, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
