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
            <form action="users" method="post">
                <input type="hidden" name="id" value="<%= user.getId() %>">
                <input type="hidden" name="username" value="<%= user.getUsername() %>">
                <input type="hidden" name="password" value="<%= user.getPassword() %>">
                <input type="hidden" name="email" value="<%= user.getEmail() %>">
                <input type="hidden" name="_method" value="PUT">
                <a href="users?action=edit&id=<%= user.getId() %>">Редактировать</a>
            </form>

            <form action="users" method="post">
                <input type="hidden" name="id" value="<%= user.getId() %>">
                <input type="hidden" name="_method" value="DELETE">
                <a href="users?action=delete&id=<%= user.getId() %>">Удалить</a>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<form action="users" method="post">
    <input type="hidden" name="action" value="add">
    <a href="users?action=add">Добавить нового пользователя</a>
</form>
</body>
</html>
