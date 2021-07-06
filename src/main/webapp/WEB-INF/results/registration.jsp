<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/login.css" type="text/css">
    <title>Registrazione Utente - 3DTech</title>
    <script src="${contextPath}/js/valiateRegistration.js" defer></script>
</head>
<body>
<%@include file="nav-bar.jsp" %>
<div id="alert-box" class="alert">
    <c:if test="${not empty requestScope.errorMsg}">
        <script>
            document.querySelector("#alert-box").style.display = "block"
        </script>
        <p>${requestScope.errorMsg}</p>
    </c:if>
    <c:if test="${not empty requestScope.errorMessages}">
        <script>
            document.querySelector("#alert-box").style.display = "block"
        </script>
        <c:forEach items="${requestScope.errorMessages}" var="message">
            <p>${message}</p>
        </c:forEach>
    </c:if>
</div>
<div class="registrazione">
    <form id="registrationForm" action="${contextPath}/account/registration" method="post">
        <label for="email">Email</label>
        <input class="input" type="email" id="email" name="fieldEmail" required><br><br>
        <label for="name">Nome</label>
        <input class="input" type="text" id="name" name="fieldName" required><br><br>
        <label for="surname">Cognome</label>
        <input class="input" type="text" id="surname" name="fieldSurname" required><br><br>
        <label for="password">Password</label>
        <input class="input" type="password" id="password" name="fieldPassword" required><br><br>
        <label for="date_birth">Data di nascita</label>
        <input class="input" type="date" id="date_birth" name="fieldDateOfBirth" required><br><br>
        <label for="phoneNumber">Telefono</label>
        <input class="input" type="tel" id="phoneNumber" name="fieldPhoneNumber" required><br><br>
        <label for="street">Indirizzo</label>
        <input class="input" type="text" id="street" name="fieldStreet" required><br><br>
        <label for="city">Città</label>
        <input class="input" type="text" id="city" name="fieldCity" required><br><br>
        <label for="zipcode">CAP</label>
        <input class="input" type="text" id="zipcode" name="fieldZIPCode" required><br><br>

        <input class="btn-submit" type="submit" value="Registrati">
    </form>
</div>
</body>
</html>
