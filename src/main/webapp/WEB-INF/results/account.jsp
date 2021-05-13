<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    Email: ${sessionScope.user.email}<br>
    Nome: ${sessionScope.user.name}<br>
</h1>
<fieldset>
    <legend>
        <form action="ShowOrders" method="post">
            <input type="hidden" name="userId" value="${sessionScope.user.id}">
            <input type="submit" value="Visualizza ordini">
        </form>
    </legend>
</fieldset>
<br>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>