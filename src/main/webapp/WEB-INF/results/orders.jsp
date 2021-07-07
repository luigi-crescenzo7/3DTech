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
        <form action="${contextPath}/order/remove" method="post">
            <input type="hidden" name="order-id" value="${order.id}">
            <button id="remove-button">Rimuovi</button>
        </form>
        <ul>
            <c:forEach items="${order.carrello.prodotti}" var="item">
                <li>id prodotto: ${item.prodotto.id} - nome: ${item.prodotto.nome} - quantita: ${item.quantita}</li>
            </c:forEach>
        </ul>
</c:forEach>
<!--<script>
    let buttons = document.querySelectorAll('#remove-button')
    let ctxPath = document.querySelector('#ctxPath').value

    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener('click', removeOrder)
    }

    function removeOrder(event) {
        const xhttp = new XMLHttpRequest()
        let idOrder = event.target.nextElementSibling.value

        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {

            }
        }
        console.log(ctxPath)

        try {
            xhttp.open('POST', ctxPath + '/order/remove')
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
            xhttp.send('idOrder=' + idOrder)
        } catch (e) {
            console.log(e)
        }
    }
</script>-->
</body>
</html>
