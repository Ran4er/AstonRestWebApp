<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.aston.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.aston.service.BookService" %>
<%@ page import="ru.aston.service.impl.BookServiceImpl" %>
<%@ page import="ru.aston.dao.BookDAO" %>
<%@ page import="ru.aston.dao.impl.BookDAOImpl" %>

<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>All Books</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <% BookDAO bookDAO = new BookDAOImpl();%>
            <% BookService bookService = new BookServiceImpl(bookDAO); %>
            <% List<Book> books = bookService.getAllBooks(); %>
            <% for (Book book : books) { %>
                <tr>
                    <td><%= book.getId() %></td>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getGenre() %></td>
                    <td><%= book.getPrice() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
