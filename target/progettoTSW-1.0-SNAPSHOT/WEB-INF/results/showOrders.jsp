<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<ul>
    <c:forEach items="${orders}" var="order">
        <li>
            <b>${order.id}   -   ${order.dataOrdine}</b>
        </li>
    </c:forEach>
</ul>
</body>
</html>
