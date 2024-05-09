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
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest").withInitScript("DB-migration.sql");

    private static UserDAOImpl userDAO;
    private static Connection connection;

    @BeforeAll
    static void setup() throws SQLException {
        postgresContainer.start();
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        userDAO = new UserDAOImpl(connection);
    }

    @AfterAll
    static void cleanup() throws SQLException {
        connection.close();
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
        User user = userDAO.getUserById(2);
        assertNotNull(user);
    }

    @Test
    void getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        assertEquals(6, users.size());
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
        assertEquals(5, users.size());
    }
}
