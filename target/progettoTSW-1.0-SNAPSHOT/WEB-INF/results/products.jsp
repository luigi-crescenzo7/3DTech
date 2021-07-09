<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Ricerca prodotti</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/showProduct.css">
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
        <c:forEach items="${requestScope.products}" var="product">
            <div class="item">
                <a href="${contextPath}/product/product-info?option=${product.id}">
                    <img src="${contextPath}/images/${product.urlImage}" alt="aa">
                </a>
                <span>Nome: <c:out value="${product.nome}"/></span>
                <span>Prezzo: <c:out value="${product.prezzo}"/></span>
                <form action="${contextPath}/cart/add" method="post">
                    <button>Aggiungi al carrello</button>
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="hidden" name="fieldQuantity" value="1">
                    <input type="hidden" name="productCategoryId" value="${product.categoria.id}">
                    <input type="hidden" name="backPath" value="${backPath}">
                </form>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
