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
    <title>Удаление пользователя</title>
</head>
<body>
<h2>Удаление пользователя</h2>
<p>Вы уверены, что хотите удалить пользователя <b><%= user.getUsername() %></b>?</p>
<form action="users" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="hidden" name="id" value="<%= user.getId() %>">
    <button type="submit">Удалить</button>
    <a href="users">Отмена</a>
</form>
</body>
</html>
