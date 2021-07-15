<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <title>Il tuo account</title>
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/account.css" type="text/css">
</head>
<body>
<%@include file="nav-bar.jsp" %>
<div class="user_details">
    <span>
        <c:out value="Nome: ${sessionScope.userSession.name}"/>
    </span>

    <span>
        <c:out value="Cognome: ${sessionScope.userSession.surname}"/>
    </span>

    <span>
        <c:out value="Email: ${sessionScope.userSession.email}"/>
    </span>

    <span>
        <c:out value="Data di nascita: ${sessionScope.userSession.dataNascita}"/>
    </span>

    <span>
        <c:out value="Numero di telefono: ${sessionScope.userSession.phoneNumber}"/>
    </span>

    <span>
        <c:out value="Città: ${sessionScope.userSession.city}"/>
    </span>

    <span>
        <c:out value="Via: ${sessionScope.userSession.street}"/>
    </span>

    <span>
        <c:out value="CAP: ${sessionScope.userSession.ZIPCode}"/>
    </span>
</div>
</body>
</html>