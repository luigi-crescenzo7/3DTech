<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>3DTech</title>
    <%@include file="/WEB-INF/results/common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/index.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
    <script defer src="${contextPath}/js/slider.js"></script>
</head>
<body>
<%@include file="/WEB-INF/results/nav-bar.jsp" %>
<div id="alert-box" class="alert">
    <c:if test="${not empty requestScope.errorMessage}">
        <script>
            document.querySelector("#alert-box").style.display = "block"
        </script>
        <p>${requestScope.errorMessage}</p>
    </c:if>
</div>
<div class="home">
    <div class="best_products">
        <div class="printer">
            <span class="label_printer">Migliori stampanti 3D</span>
            <c:forEach items="${applicationScope.listProducts}" var="product">
                <c:if test="${product.categoria.nome.equals('Stampanti 3D')}">
                    <div class="container-item">
                        <img alt="immagine" src="${contextPath}/images/${product.urlImage}">
                        <span>${product.nome}</span>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div class="brands">
            <span class="label_brands">Migliori resine</span>
            <c:forEach items="${applicationScope.listProducts}" var="product">
                <c:if test="${product.categoria.nome.equals('Resine')}">
                    <div class="container-item">
                        <img alt="immagine" src="${contextPath}/images/${product.urlImage}">
                        <span>${product.nome}</span>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>

<div class="sconti">
    <div class="content">
        <div class="label_sconti">Sconti</div>
        <div class="slick-container">
            <c:forEach items="${applicationScope.listProducts}" var="product">
                <c:if test="${product.sconto > 10}">
                    <div>
                        <img alt="immagine" src="${contextPath}/images/${product.urlImage}">
                        <span>${product.nome}</span>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="WEB-INF/results/footer.jsp" %>
</body>
</html>
