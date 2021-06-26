<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <!--<link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">-->
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <!--<link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">-->
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/category.css" type="text/css">
    <title>Categorie</title>
</head>
<body class="clearfix">
<%@include file="nav-bar.jsp" %>
<section class="category">
    <!--<c:forEach items="${applicationScope.listCategories}" var="category">
        <div class="cat">
            <a href="#"><img src="${contextPath}/imgs/categorie/${category.urlImage}" alt=""></a>
            <span class="text_category category_label">${category.nome}</span>
        </div>
    </c:forEach>-->
    <div class="cat">
        <a href="#"><img src="${contextPath}/imgs/categorie/stampante3d.png" alt=""></a>
        <span class="text_category category_label">Stampanti 3D</span>
    </div>

    <div class="cat">
        <a href="#"><img src="${contextPath}/imgs/categorie/ricambi.png" alt=""></a>
        <span class="text_category category_label">Ricambi</span>
    </div>

    <div class="cat">
        <a href="#"><img src="${contextPath}/imgs/categorie/filamenti.png" alt=""></a>
        <span class="text_category category_label">Filamenti</span>
    </div>

    <div class="cat">
        <a href="#"><img src="${contextPath}/imgs/categorie/resina.png" alt=""></a>
        <span class="text_category category_label">Resina</span>
    </div>

    <div class="cat">
        <a href="#"><img src="${contextPath}/imgs/categorie/utility.png" alt=""></a>
        <span class="text_category category_label">Utensili</span>
    </div>

    <div class="cat">
        <a href="#"><img src="${contextPath}/imgs/categorie/accessori.png" alt=""></a>
        <span class="text_category category_label">Accessori</span>
    </div>
</section>
<%@include file="footer.jsp" %>
</body>
</html>
