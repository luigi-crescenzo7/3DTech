<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Scheda prodotto</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/productDetails.css" type="text/css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<div class="product_container">
    <div class="product_img">
        <img src="${contextPath}/images/${requestScope.product.urlImage}" alt="aa">
    </div>
    <div class="product_details">

        <span class="name_product">
            <c:out value="${requestScope.product.nome}"/>
        </span>

        <span class="price_product">
            <c:out value="€ ${requestScope.product.prezzo}"/>
        </span>

        <div class="line"></div>

        <div class="buttons">
            <button class="add_to_cart">Aggiungi al carrello</button>
        </div>

        <span class="product_info">
            <c:out value="Descrizione: ${requestScope.product.descrizione}"/>
        </span>

        <span class="product_info">
            <c:out value="Marchio: ${requestScope.product.marchio}"/>
        </span>

        <span class="product_info">
            <c:out value="Peso: ${requestScope.product.peso}"/>
        </span>


        <c:forEach items="${requestScope.product.caratteristiche.keys()}" var="elemName">
            <c:set var="jsonObject" value="${requestScope.product.caratteristiche}"/>
            <c:set var="keyName" value="${elemName}"/>
            <span class="product_info">
                <!-- opt() fa la stessa cosa di get() -->
                <c:out value="${keyName}: ${jsonObject.opt(keyName)}"/>
            </span>
        </c:forEach>

    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>