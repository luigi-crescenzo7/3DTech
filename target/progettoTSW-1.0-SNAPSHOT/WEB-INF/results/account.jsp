<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <title>Il tuo account</title>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/account.css" type="text/css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<div class="user_details">
        <span>
            <c:out value="Nome: ${sessionScope.userSession.name}"/>
        </span>
    <span>
            <c:out value="Cognome: ${sessionScope.userSession.surname}"/>
        </span>
    <span>
            <c:out value="Email: ${sessionScope.userSession.email}"/>
        </span>
    <span>
            <c:out value="Data di nascita: ${sessionScope.userSession.dataNascita}"/>
        </span>
    <span>
            <c:out value="Numero di telefono: ${sessionScope.userSession.phoneNumber}"/>
        </span>
    <span>
            <c:out value="Città: ${sessionScope.userSession.city}"/>
        </span>
    <span>
            <c:out value="Via: ${sessionScope.userSession.street}"/>
        </span>
    <span>
            <c:out value="CAP: ${sessionScope.userSession.ZIPCode}"/>
        </span>
</div>
</body>
</html>
<!--<!DOCTYPE html>
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
</html>-->