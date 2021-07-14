<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Il tuo carrello</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cart.css" type="text/css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<c:set var="listSize" value="${sessionScope.sessionCart.prodotti.size()}"/>
<div class="page_cart">
    <div class="cart">
        <span class="cart_label">Carrello</span>
        <div class="total">
            <c:out value="Totale: ${sessionScope.sessionCart.total} €"/>
        </div>
        <div class="line"></div>
        <c:choose>
            <c:when test="${listSize > 0}">
                <c:forEach items="${sessionScope.sessionCart.prodotti}" var="cartItem">
                    <div class="cart_item">
                        <div class="cart_item_img">
                            <a href="${contextPath}/product/product-info?option=${cartItem.prodotto.id}">
                                <img src="${contextPath}/images/${cartItem.prodotto.urlImage}" alt="immagine">
                            </a>
                        </div>

                        <p>Nome: ${cartItem.prodotto.nome}</p>
                        <p>Prezzo: € ${cartItem.prodotto.prezzo}</p>
                        <p>Marchio: ${cartItem.prodotto.marchio}</p>
                        <p>Sconto: ${cartItem.prodotto.sconto} %</p>
                        <form action="${contextPath}/cart/remove" method="post">
                            <label for="quantita">Quantità:</label>
                            <input id="quantita" type="number" name="quantita" value="${cartItem.quantita}">
                            <button class="remove_button">Rimuovi</button>
                            <input type="hidden" name="productId" value="${cartItem.prodotto.id}">
                        </form>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>Il tuo carrello è vuoto</p>
            </c:otherwise>
        </c:choose>
    </div>
    <c:if test="${listSize > 0}">
        <div class="order">
            <span class="finish_order">Completa il tuo ordine</span>
            <div class="total">
                <c:out value="Totale: ${sessionScope.sessionCart.total} €"/>
            </div>
            <div class="make_order">
                <form action="${contextPath}/order/checkout" method="post">
                    <input class="order_button" type="submit" name="order" value="Conferma ordine">
                </form>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>