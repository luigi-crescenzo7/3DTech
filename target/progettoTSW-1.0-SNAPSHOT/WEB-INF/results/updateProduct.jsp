<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<b>${requestScope.product.id}</b>
<b>${requestScope.product.nome}</b>
<b>${requestScope.product.prezzo}</b>
<b>${requestScope.product.caratteristiche}</b>
<form action="ProductServlet" method="post">
    <input type="hidden" name="productId" value="${requestScope.product.id}">
    Nome:
    <input type="text" name="fieldName">
    Volume stampante (massimo):
    <input type="text" name="fieldMaxVolume">
    Velocita massima stampante:
    <input type="text" name="fieldMaxSpeed">
    <input type="submit" value="Modifica">
</form>
</body>
</html>