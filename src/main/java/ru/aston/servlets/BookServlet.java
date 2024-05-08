package ru.aston.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ru.aston.model.Book;
import ru.aston.service.BookService;

@WebServlet(name = "BookServlet", urlPatterns = "/books")
public class BookServlet extends HttpServlet {
    private final BookService bookService;

    public BookServlet(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        double price = Double.parseDouble(request.getParameter("price"));

        Book newBook = new Book(id, title, author, genre, price);
        bookService.addBook(newBook);

        response.sendRedirect("books");
    }
}
