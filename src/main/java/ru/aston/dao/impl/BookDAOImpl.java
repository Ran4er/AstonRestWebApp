package ru.aston.dao.impl;

import ru.aston.model.Book;
import ru.aston.dao.BookDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private final Connection connection;

    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

	@Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM books");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getInt("author_id"));
                book.setGenre(resultSet.getInt("genre_id"));
                book.setPrice(resultSet.getDouble("price"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getInt("author_id"));
                    book.setGenre(resultSet.getInt("genre_id"));
                    book.setPrice(resultSet.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public void addBook(Book book) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO books (title, author_id, genre_id, price) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthor());
            statement.setInt(3, book.getGenre());
            statement.setDouble(4, book.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(Book book) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE books SET title=?, author_id=?, genre_id=?, price=? WHERE id=?")) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthor());
            statement.setInt(3, book.getGenre());
            statement.setDouble(4, book.getPrice());
            statement.setInt(5, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE book_id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}