<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            <c:set var="categoryName" value="${category.nome}"/>
            <c:set var="categoryId" value="${category.id}"/>
            <a href="${contextPath.concat("/categorie/category?option=").concat(categoryId)}"><img
                    src="${contextPath}/images/${category.urlImage}"
                    alt=""></a>
            <span class="category_label">${category.nome}</span>
        </div>
    </c:forEach>
</section>
<%@include file="footer.jsp" %>
</body>
</html>
