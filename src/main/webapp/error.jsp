<%--
  Created by IntelliJ IDEA.
  User: Host
  Date: 19.03.2025
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
<h2 style="color: red;">Ошибка авторизации</h2>
<p><%= request.getAttribute("errorMessage") %></p>
<a href="login">Попробовать снова</a>
</body>
</html>
