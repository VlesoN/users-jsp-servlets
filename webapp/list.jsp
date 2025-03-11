<%--
  Created by IntelliJ IDEA.
  User: Host
  Date: 07.03.2025
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, user.User" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>

<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<table>
    <tr>
        <th>ID</th><th>Имя</th><th>Email</th><th>Действия</th>
    </tr>
    <% for (User user : users) { %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getEmail() %></td>
        <td>
            <a href="users?action=edit&id=<%= user.getId() %>">Редактировать</a>
            <a href="users?action=delete&id=<%= user.getId() %>">Удалить</a>
        </td>
    </tr>
    <% } %>
</table>
<a href="users?action=add">Добавить нового пользователя</a>
</body>
</html>
