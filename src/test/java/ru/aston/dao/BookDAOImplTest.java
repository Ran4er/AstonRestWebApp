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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class BookDAOImplTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

    private final BookDAOImpl bookDAO = new BookDAOImpl();

    @BeforeAll
    static void setup() {
        postgresContainer.start();
        System.setProperty("DB_URL", postgresContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgresContainer.getUsername());
        System.setProperty("DB_PASSWORD", postgresContainer.getPassword());
        try (Connection connection = DriverManager.getConnection(postgresContainer.getJdbcUrl(), postgresContainer.getUsername(), postgresContainer.getPassword());
             var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS books (id SERIAL PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), genre VARCHAR(255), price DOUBLE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void cleanup() {
        postgresContainer.stop();
    }

    @Test
    void addBook() {
        Book book = new Book("Test Book", "Test Author", "Test Genre", 10.99);
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
        assertEquals(1, books.size());
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
        assertEquals(0, books.size());
    }
}
