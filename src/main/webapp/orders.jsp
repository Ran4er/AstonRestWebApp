<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.aston.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.aston.service.OrderService" %>
<%@ page import="ru.aston.service.impl.OrderServiceImpl" %>
<%@ page import="ru.aston.dao.impl.OrderDAOImpl" %>
<%@ page import="ru.aston.dao.OrderDAO" %>

<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>All Orders</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Book ID</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <% OrderDAO orderDAO = new OrderDAOImpl(); %>
            <% OrderService orderService = new OrderServiceImpl(orderDAO); %>
            <% List<Order> orders = orderService.getAllOrders(); %>
            <% for (Order order : orders) { %>
                <tr>
                    <td><%= order.getId() %></td>
                    <td><%= order.getUserId() %></td>
                    <td><%= order.getBookId() %></td>
                    <td><%= order.getQuantity() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
