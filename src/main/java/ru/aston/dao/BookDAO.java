package ru.aston.dao;

import java.util.List;
import ru.aston.model.Book;

public interface BookDAO {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
}
