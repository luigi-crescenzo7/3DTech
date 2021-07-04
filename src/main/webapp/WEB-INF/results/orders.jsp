<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ordini - 3DTech</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<c:forEach items="${requestScope.userOrders}" var="order">
    <span>id: ${order.id} - data: ${order.dataOrdine} -  totale: ${order.carrello.total}</span>
    <ul>
        <c:forEach items="${order.carrello.prodotti}" var="item">
            <li>id prodotto: ${item.prodotto.id} - nome: ${item.prodotto.nome} -  quantita: ${item.quantita}</li>
        </c:forEach>
    </ul>
</c:forEach>
</body>
</html>
