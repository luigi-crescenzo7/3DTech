<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/category.css" type="text/css">
    <title>Categorie</title>
</head>
<body>
<%@include file="nav-bar.jsp" %>
<section class="category">
    <c:forEach items="${applicationScope.listCategories}" var="category">
        <div class="cat">
            <a href="#"><img src="${contextPath}/imgs/categorie/${category.urlImage}"
                             alt=""></a>
            <span class="category_label">${category.nome}</span>
        </div>
    </c:forEach>
</section>
<%@include file="footer.jsp" %>
</body>
</html>
