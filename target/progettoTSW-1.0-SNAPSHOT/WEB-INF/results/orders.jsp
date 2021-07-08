<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ordini - 3DTech</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
    <style>
        .orders-container {
            margin: 30px;
            border: 1px solid black;
        }

        .accordion {
            width: 60%;
            border: 1px solid black;
            display: block;
            margin: 0 auto;
        }

        summary {
            background-color: #3a84c1;
            margin: 0;
            padding: 0;
            color: white;
        }

        table {
            width: 100%;
            border: 1px solid black;
            border-collapse: collapse;
        }

        tr,td,th {
            border: 1px solid black;
            text-align: center;
        }
    </style>
</head>
<body>
<%@include file="nav-bar.jsp" %>
<div class="orders-container">
    <c:forEach items="${requestScope.userOrders}" var="order">
        <details class="accordion">
            <summary>Data di creazione: ${order.dataOrdine} -- Id: ${order.id}</summary>
            <table>
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Prezzo</th>
                    <th>Quantita</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.carrello.prodotti}" var="item">
                    <tr>
                        <td data-head="Prodotto">${item.prodotto.nome}</td>
                        <td data-head="Prezzo">${item.prodotto.prezzo}</td>
                        <td data-head="Quantita">${item.quantita}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </details>
    </c:forEach>
</div>
<!--<c:forEach items="${requestScope.userOrders}" var="order">
    <span>id: ${order.id} - data: ${order.dataOrdine} -  totale: ${order.carrello.total}</span>
    <form action="${contextPath}/order/remove" method="post">
        <input type="hidden" name="order-id" value="${order.id}">
        <button type="submit" id="remove-button">Rimuovi</button>
    </form>
    <ul>
        <c:forEach items="${order.carrello.prodotti}" var="item">
            <li>id prodotto: ${item.prodotto.id} - nome: ${item.prodotto.nome} - quantita: ${item.quantita}</li>
            <form action="${contextPath}/product/remove" method="post">
                <button type="submit">Rimuovi prodotto</button>
                <input type="hidden" name="product-id" value="${item.prodotto.id}">
                <input type="hidden" name="order-id" value="${order.id}">
            </form>
        </c:forEach>
    </ul>
</c:forEach>-->
</body>
</html>
