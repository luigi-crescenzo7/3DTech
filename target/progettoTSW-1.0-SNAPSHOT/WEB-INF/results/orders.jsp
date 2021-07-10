<%@ page contentType="text/html;charset=UTF-8" %>
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

        tr, td, th {
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
</body>
</html>
