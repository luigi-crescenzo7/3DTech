<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="UpdateProductServlet" method="post">

    <c:forEach items="${applicationScope.listProducts}" var="product">
        <b style="display: inline;">${product.nome}</b>
        <input style="display: inline;" type="hidden" name="productId" value="${product.id}">
        <input type="submit" value="Scegli">
    </c:forEach>
</form>
</body>
</html>
