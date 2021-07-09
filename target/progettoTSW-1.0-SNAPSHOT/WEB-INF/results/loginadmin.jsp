<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@ include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/login.css" type="text/css">
    <script src="${contextPath}/js/validateLogin.js" defer></script>
    <title>Login Admin - 3DTech</title>
</head>
<body class="admin">
<div id="alert-box" class="alert">
    <c:if test="${not empty requestScope.errorMsg}">
        <script>
            document.querySelector("#alert-box").style.display = "block"
        </script>
        <p>${requestScope.errorMsg}</p>
    </c:if>
</div>
<div class="login_admin">
    <div>
        <p>Login Amministratore</p>
    </div>
    <form id="loginForm" action="${contextPath}/account/loginadmin" method="post">
        <label for="email">Email</label>
        <input class="input_admin" type="email" id="email" name="fieldEmail"><br><br>
        <label for="password">Password</label>
        <input class="input_admin" type="password" id="password" name="fieldPassword"><br><br><br>
        <input class="accedi_admin" type="submit" name="Submit" value="Accedi">
    </form>
</div>
</body>
</html>