<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Scheda prodotto</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/productDetails.css" type="text/css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<c:set var="backPath" value="${requestScope.back}"/>
<div class="product_container">
    <div class="product_img">
        <img src="${contextPath}/images/${requestScope.product.urlImage}" alt="immagine">
    </div>
    <div class="product_details">

        <span class="name_product">
            <c:out value="${requestScope.product.nome}"/>
        </span>

        <span class="price_product">
            <c:choose>
                <c:when test="${requestScope.product.sconto != 0}">
                    <p style="text-decoration: line-through;">
                        <c:out value="€ ${requestScope.product.prezzo}"/>
                    </p>
                    <p style="color:red;">
                        <c:out value = "€ ${requestScope.product.prezzo - ((requestScope.product.prezzo/100)*requestScope.product.sconto)}"/>
                    </p>
                </c:when>

                <c:otherwise>
                    <p>
                        <c:out value="€ ${requestScope.product.prezzo}"/>
                    </p>
                </c:otherwise>
            </c:choose>



        </span>

        <div class="line"></div>

        <div class="buttons">
            <c:choose>
                <c:when test="${requestScope.product.visible == true}">
                    <form action="${contextPath}/cart/add" method="post">
                        <button class="add_to_cart">Aggiungi al carrello</button>
                        <input type="hidden" name="backPath" value="${backPath}">
                        <input type="hidden" name="productId" value="${requestScope.product.id}">
                        <input type="hidden" name="fieldQuantity" value="1">
                    </form>
                </c:when>
                <c:otherwise>
                    <p>Prodotto non disponibile</p>
                </c:otherwise>
            </c:choose>
        </div>

        <span class="product_info">
            <c:out value="Descrizione: ${requestScope.product.descrizione}"/>
        </span>

        <span class="product_info">
            <c:out value="Marchio: ${requestScope.product.marchio}"/>
        </span>

        <span class="product_info">
            <c:out value="Peso: ${requestScope.product.peso} Kg"/>
        </span>

        <span class="product_info">
            <c:out value="Sconto: ${requestScope.product.sconto} %"/>
        </span>

        <c:forEach items="${requestScope.product.caratteristiche.keys()}" var="elemName">
            <c:set var="jsonObject" value="${requestScope.product.caratteristiche}"/>
            <c:set var="keyName" value="${elemName}"/>
            <span class="product_info">
                <c:out value="${keyName}: ${jsonObject.opt(keyName)}"/>
            </span>
        </c:forEach>

    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>