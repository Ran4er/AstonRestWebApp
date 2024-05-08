<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.aston.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.aston.service.UserService" %>
<%@ page import="ru.aston.service.impl.UserServiceImpl" %>
<%@ page import="ru.aston.dao.impl.UserDAOImpl" %>
<%@ page import="ru.aston.dao.UserDAO" %>

<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>All Users</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
            </tr>
        </thead>
        <tbody>
            <% UserDAO userDAO = new UserDAOImpl(); %>
            <% UserService userService = new UserServiceImpl(userDAO); %>
            <% List<User> users = userService.getAllUsers(); %>
            <% for (User user : users) { %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getUsername() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
