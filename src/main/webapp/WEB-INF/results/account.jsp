<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <c:set var="contextPath" scope="page" value="${pageContext.request.contextPath}"/>
</head>
<body>
<h1>
    Email: ${sessionScope.userSession.email}<br>
    Nome: ${sessionScope.userSession.name}<br>
</h1>
<ul>
    <c:forEach items="${applicationScope.listProducts}" var="product">
        <form action="${contextPath}/ll/select" method="post">
            <li>${product.nome}</li>
            <label for="quantity">Quantità:</label>
            <input id="quantity" type="number" name="fieldQuantity">
            <input type="hidden" name="productId" value="${product.id}">
            <input type="submit" value="Seleziona">
        </form>
    </c:forEach>
</ul>
<form action="${contextPath}/ll/checkout" method="post">
    <input type="submit" value="Procedi con l'ordine">
</form>
<c:if test="${sessionScope.products != null}">
    <b>Prodotti nel carrello:</b><br>
    <ul>
        <c:forEach items="${sessionScope.products}" var="cartitem">
            <c:set var="prodotto" scope="page" value="${cartitem.prodotto}"/>
            <li> ${prodotto.nome} - ${prodotto.id}</li>
            <label for="quantity">Quantità: ${cartitem.quantita}</label>
        </c:forEach>
    </ul>
</c:if>
<fieldset>
    <legend>
        <form action="${contextPath}/tt/orders" method="post">
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