package ru.aston.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aston.dao.BookDAO;
import ru.aston.model.Book;
import ru.aston.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook() {
        Book book = new Book("Test Book", 0, 0, 10.99);
        bookService.addBook(book);
        verify(bookDAO, times(1)).addBook(book);
    }

    @Test
    void getBookById() {
        Book expectedBook = new Book("Test Book", 0, 0, 10.99);
        when(bookDAO.getBookById(1)).thenReturn(expectedBook);
        Book actualBook = bookService.getBookById(1);
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void getAllBooks() {
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("Test Book 1", 0, 0, 10.99));
        expectedBooks.add(new Book("Test Book 2", 0, 0, 20.99));
        when(bookDAO.getAllBooks()).thenReturn(expectedBooks);
        List<Book> actualBooks = bookService.getAllBooks();
        assertEquals(expectedBooks.size(), actualBooks.size());
    }

    @Test
    void updateBook() {
        Book book = new Book("Test Book", 0, 0, 10.99);
        book.setId(1);
        bookService.updateBook(book);
        verify(bookDAO, times(1)).updateBook(book);
    }

    @Test
    void deleteBook() {
        bookService.deleteBook(1);
        verify(bookDAO, times(1)).deleteBook(1);
    }
}
