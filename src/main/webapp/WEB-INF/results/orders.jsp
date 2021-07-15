<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Resoconto Ordini</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/footer.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/orders.css" type="text/css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<div class="order_report">
    <span class="order_label">Ordini</span>
    <div class="line"></div>
    <c:choose>
        <c:when test="${requestScope.userOrders.size() > 0 }">
            <c:forEach items="${requestScope.userOrders}" var="order">
                <c:if test="${not empty requestScope.userOrders}">
                    <c:if test="${order.visible == true}">
                        <div class="order_item">
                            <div class="all_info">
                                <div class="order_info order_id"><c:out value="ID Ordine: ${order.id}"/></div>
                                <div class="order_info order_date"><c:out value="Data: ${order.dataOrdine}"/></div>
                                <div class="order_info order_total_product">
                                    <c:out value="Totale prodotti: ${order.carrello.totaleProdotti()}"/>
                                </div>
                                <div class="order_info order_total_price">
                                    <c:out value="Totale prezzo: ${order.carrello.total}"/>
                                </div>
                                <span class="order_remove_button">
                            <form style="display: inline-block" action="${contextPath}/order/remove" method="post">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <button class="remove_button">Cancella ordine</button>
                            </form>
                        </span>
                            </div>
                            <c:forEach items="${order.carrello.prodotti}" var="cartItem">
                                <div class="order_product">
                                    <div><img alt="immagine" src="${contextPath}/images/${cartItem.prodotto.urlImage}"/>
                                    </div>
                                    <span><c:out value="Nome: ${cartItem.prodotto.nome}"/></span>
                                    <span><c:out value="Prezzo: ${cartItem.prodotto.prezzo}"/></span>
                                    <span><c:out value="Quantità: ${cartItem.quantita}"/></span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>Non hai nessun ordine</p>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="footer.jsp" %>
</body>
</html>