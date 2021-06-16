<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%@include file="common.jsp" %>
    <c:set scope="page" var="product" value="${requestScope.createdProduct}"/>
</head>
<body>
<p>
    Nome: <b>${product.nome}</b>
</p>
<p>
    Marchio: <b>${product.marchio}</b>
</p>
<p>
    Immagine: <img src="${contextPath}/covers/${product.urlImage}" alt="${product.id}">
</p>

</body>
</html>
