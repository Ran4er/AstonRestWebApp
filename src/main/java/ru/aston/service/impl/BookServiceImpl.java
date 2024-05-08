package ru.aston.service.impl;

import ru.aston.dao.BookDAO;
import ru.aston.model.Book;
import ru.aston.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
	
    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
    	this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }
    
}