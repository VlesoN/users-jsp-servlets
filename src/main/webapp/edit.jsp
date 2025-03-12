<%--
  Created by IntelliJ IDEA.
  User: Host
  Date: 07.03.2025
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="user.User" %>
<%
    User user = (User) request.getAttribute("user");
%>
<html>
<head>
    <title>Редактировать пользователя</title>
</head>
<body>
<h2>Редактировать пользователя</h2>
<form action="users" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="hidden" name="id" value="<%= user.getId() %>">
    <label>Имя пользователя: <input type="text" name="username" value="<%= user.getUsername() %>" required></label><br>
    <label>Пароль: <input type="password" name="password" value="<%= user.getPassword() %>" required></label><br>
    <label>Email: <input type="email" name="email" value="<%= user.getEmail() %>" required></label><br>
    <button type="submit">Сохранить изменения</button>
</form>
<br>
<a href="users">Отмена</a>
</body>
</html>
