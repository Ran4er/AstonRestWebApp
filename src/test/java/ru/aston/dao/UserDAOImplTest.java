package ru.aston.dao;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.aston.dao.impl.UserDAOImpl;
import ru.aston.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class UserDAOImplTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

    private final UserDAOImpl userDAO = new UserDAOImpl();

    @BeforeAll
    static void setup() {
        postgresContainer.start();
        System.setProperty("DB_URL", postgresContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgresContainer.getUsername());
        System.setProperty("DB_PASSWORD", postgresContainer.getPassword());
        try (Connection connection = DriverManager.getConnection(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword());
             var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, username VARCHAR(255), password VARCHAR(255))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void cleanup() {
        postgresContainer.stop();
    }

    @Test
    void addUser() {
        User user = new User("testUser", "testPassword");
        userDAO.addUser(user);
        assertNotNull(user.getId());
    }

    @Test
    void getUserById() {
        User user = userDAO.getUserById(1);
        assertNotNull(user);
    }

    @Test
    void getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        assertEquals(1, users.size());
    }

    @Test
    void updateUser() {
        User user = userDAO.getUserById(1);
        user.setPassword("newPassword");
        userDAO.updateUser(user);
        User updatedUser = userDAO.getUserById(1);
        assertEquals("newPassword", updatedUser.getPassword());
    }

    @Test
    void deleteUser() {
        userDAO.deleteUser(1);
        List<User> users = userDAO.getAllUsers();
        assertEquals(0, users.size());
    }
}
