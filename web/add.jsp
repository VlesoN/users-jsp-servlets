<%--
  Created by IntelliJ IDEA.
  User: Host
  Date: 07.03.2025
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление пользователя</title>
</head>
<body>
<h2>Добавить пользователя</h2>
<form action="users" method="post">
    <input type="hidden" name="action" value="add">
    <label>Имя пользователя: <input type="text" name="username" required></label><br>
    <label>Пароль: <input type="password" name="password" required></label><br>
    <label>Email: <input type="email" name="email" required></label><br>
    <button type="submit">Добавить</button>
</form>
<br>
<a href="users">Вернуться к списку</a>
</body>
</html>
