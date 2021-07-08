<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="main">
    <div class="left_menu">
        <ul>
            <li id="label-categories">Categorie</li>
            <c:forEach items="${applicationScope.listCategories}" var="category">
                <li><a href="${contextPath}/categorie/category?option=${category.id}">${category.nome}</a></li>
            </c:forEach>
            <!--<li><a href="${contextPath}/?option=stampanti">Stampanti 3D</a></li>
            <li><a href="${contextPath}/categorie/category?option=filamenti">Filamenti</a></li>
            <li><a href="${contextPath}/categorie/category?option=ricambi">Ricambi per stampanti</a></li>
            <li><a href="${contextPath}/VisualizzazioneProdottiServlet?option=resina">Resina</a></li>
            <li><a href="${contextPath}/VisualizzazioneProdottiServlet?option=utensili">Utensili</a></li>
            <li><a href="${contextPath}/VisualizzazioneProdottiServlet?option=accessori">Accessori</a></li>-->
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
                <form action="" method="post"> <!-- servlet da definire -->
                    <button>Aggiungi al carrello</button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
