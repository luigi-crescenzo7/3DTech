<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Ricerca prodotti</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/showProduct.css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<c:set var="backPath" value="${requestScope.back}"/>
<div class="main">
    <div class="left_menu">
        <ul>
            <li id="label-categories">Categorie</li>
            <c:forEach items="${applicationScope.listCategories}" var="category">
                <li><a href="${contextPath}/categorie/category?option=${category.id}">${category.nome}</a></li>
            </c:forEach>
        </ul>
    </div>

    <div class="all_products">
        <c:choose>
            <c:when test="${not empty requestScope.products}">
                <c:forEach items="${requestScope.products}" var="product">
                    <div class="item">
                        <a href="${contextPath}/product/product-info?option=${product.id}">
                            <img src="${contextPath}/images/${product.urlImage}" alt="immagine">
                        </a>
                        <span>Nome: <c:out value="${product.nome}"/></span>

                        <c:choose>

                            <c:when test="${product.sconto > 0}">
                                <span style="color:red;">Special price:</span>
                                <span style="color:red;"> € <c:out value="${product.prezzo}"/></span>
                            </c:when>
                            <c:otherwise>
                                    <span>Prezzo: € <c:out value="${product.prezzo}"/></span>
                            </c:otherwise>
                        </c:choose>

                        <form action="${contextPath}/cart/add" method="post">
                            <c:choose>
                                <c:when test="${product.visible == true}">
                                    <button>Aggiungi al carrello</button>
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="hidden" name="fieldQuantity" value="1">
                                    <input type="hidden" name="productCategoryId" value="${product.categoria.id}">
                                    <input type="hidden" name="backPath" value="${backPath}">
                                </c:when>
                                <c:otherwise>
                                    <button style="background: gray" disabled>Aggiungi al carrello</button>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>Nessun risultato trovato</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>
