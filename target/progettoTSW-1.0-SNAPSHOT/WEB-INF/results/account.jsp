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
        <form action="${contextPath}/product/select" method="post">
            <li>${product.nome}</li>
            <label for="quantity">Quantità:</label>
            <input id="quantity" type="number" name="fieldQuantity" value="1">
            <input type="hidden" name="productId" value="${product.id}">
            <input type="submit" value="Seleziona">
        </form>
    </c:forEach>
</ul>
<form action="${contextPath}/order/checkout" method="post">
    <input type="submit" value="Procedi con l'ordine">
</form>
<c:choose>
    <c:when test="${sessionScope.sessionCart.prodotti.size() > 0}">
        <b>Prodotti nel carrello:</b><br>
        <ul>
            <c:forEach items="${sessionScope.sessionCart.prodotti}" var="cartitem">
                <c:set var="prodotto" scope="page" value="${cartitem.prodotto}"/>
                <li> ${prodotto.nome} - ${prodotto.id}</li>
                <p>Quantità: ${cartitem.quantita}</p>
                <form action="${contextPath}/cart/remove" method="post">
                    <input type="submit" value="Rimuovi">
                    <input type="hidden" name="productId" value="${prodotto.id}">
                </form>
            </c:forEach>
        </ul
    </c:when>
    <c:otherwise>
        <p>Non ci sono prodotti nel carrello</p>
    </c:otherwise>
</c:choose>
<fieldset>
    <legend>
        <form action="${contextPath}/order/orders" method="post">
            <input type="submit" value="Visualizza ordini">
        </form>
    </legend>
</fieldset>
<br>
<form action="" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>