package ru.aston.dao.impl;

import ru.aston.model.Book;
import ru.aston.util.DBUtil;
import ru.aston.dao.BookDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

	@Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement("SELECT * FROM books");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author_id"));
                book.setGenre(resultSet.getString("genre_id"));
                book.setPrice(resultSet.getDouble("price"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = null;
        try (Connection connection = DBUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setGenre(resultSet.getString("genre"));
                    book.setPrice(resultSet.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void addBook(Book book) {
        try (Connection connection = DBUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement("INSERT INTO books (title, author_id, genre_id, price) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setDouble(4, book.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) {
        try (Connection connection = DBUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement("UPDATE books SET title=?, author_id=?, genre_id=?, price=? WHERE id=?")) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setDouble(4, book.getPrice());
            statement.setInt(5, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        try (Connection connection = DBUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}