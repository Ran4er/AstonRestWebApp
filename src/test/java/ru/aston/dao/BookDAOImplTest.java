package ru.aston.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.aston.dao.impl.BookDAOImpl;
import ru.aston.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class BookDAOImplTest {

    private static final String TRUNCATE_BOOKS = "TRUNCATE TABLE books RESTART IDENTITY CASCADE";

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest").withInitScript("init.sql");

    private static BookDAOImpl bookDAO;

    private static Connection connection;

    @BeforeAll
    static void setup() throws SQLException {
        postgresContainer.start();
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        bookDAO = new BookDAOImpl(connection);
    }

    @AfterAll
    static void cleanup() throws SQLException {
        //truncateTable(TRUNCATE_BOOKS);
        connection.close();
        postgresContainer.stop();
    }

    public static void truncateTable(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addBook() {
        Book book = new Book("Test Book", 1, 1, 10.99);
        bookDAO.addBook(book);
        assertNotNull(book.getId());
    }

    @Test
    void getBookById() {
        Book book = bookDAO.getBookById(1);
        assertNotNull(book);
    }

    @Test
    void getAllBooks() {
        List<Book> books = bookDAO.getAllBooks();
        assertEquals(6, books.size());
    }

    @Test
    void updateBook() {
        Book book = bookDAO.getBookById(1);
        book.setPrice(9.99);
        bookDAO.updateBook(book);
        Book updatedBook = bookDAO.getBookById(1);
        assertEquals(9.99, updatedBook.getPrice());
    }

    @Test
    void deleteBook() {
        bookDAO.deleteBook(1);
        List<Book> books = bookDAO.getAllBooks();
        assertEquals(5, books.size());
    }
}
