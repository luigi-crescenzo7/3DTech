<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Il tuo carrello</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/cart.css" type="text/css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<div class="page_cart">
    <div class="cart">
        <span class="cart_label">Carrello</span>
        <span class="prize_label">Prezzo</span>
        <div class="line"></div>
        <c:forEach items="${sessionScope.sessionCart.prodotti}" var="cartItem">
            <div class="cart_item">
                <div class="cart_item_img">
                    <img src="${contextPath}/images/${cartItem.prodotto.urlImage}" alt="aa">
                </div>
                <span class="main_info_cart_item">
          <c:out value="Nome: ${cartItem.prodotto.nome}"/>
          <span class="price_cart_item"><c:out value="${cartItem.prodotto.prezzo} €"/></span></span>
                <span class="info_cart_item"><c:out value="Descrizione: ${cartItem.prodotto.descrizione}"/></span>

                <span class="info_cart_item">
          <c:out value="Marchio: ${cartItem.prodotto.marchio}"/>
                </span>

                <span class="info_cart_item">
          <c:out value="Peso: ${cartItem.prodotto.peso}"/>
        </span>

                <span class="quantity_and_remove">
          <span><label for="quantita">Quantità: </label></span>
          <span><form action="${contextPath}/UpdateProductQuantityServlet" method="post">
            <select id="quantita" name="quantita" onchange="this.form.submit()">
              <option value="${cartItem.quantita}" selected hidden>${cartItem.quantita}</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
            </select>
          </form>
            </span>
                    <form action="${contextPath}/cart/remove" method="post">
                        <span><button class="remove_button">Rimuovi</button></span>
                        <input type="hidden" name="productId" value="${cartItem.prodotto.id}">
                    </form>
        </span>
            </div>
        </c:forEach>
        <div class="total">
            <c:out value="Totale: ${sessionScope.sessionCart.total} €"/>
        </div>
    </div>
    <div class="order">
        <span class="finish_order">Completa il tuo ordine</span>
        <div class="total">
            <c:out value="Totale: ${sessionScope.sessionCart.total} €"/>
        </div>
        <div class="make_order">
            <form action="" method="post">
                <input class="order_button" type="submit" name="order" value="Ordina">
            </form>
        </div>
    </div>
</div>
</body>
</html>