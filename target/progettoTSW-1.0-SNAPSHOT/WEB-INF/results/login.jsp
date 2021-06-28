<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="common.jsp" %>
    <title>Login Utente - 3DTech</title>
    <!--<link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">-->
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/login.css" type="text/css">
    <script src="${contextPath}/js/checkUser.js" defer></script>
</head>
<body>
<%@include file="nav-bar.jsp" %>
<div id="alert-box">
</div>
<div class="form-input">
    <form action="${contextPath}/xx/login" method="post">
        <label for="email">Email</label>
        <input class="input" type="email" id="email" name="fieldEmail" required>
        <span class="error"></span>
        <br><br>
        <label for="password">Password:</label>
        <input class="input" type="password" id="password" name="fieldPassword" required>
        <span class="error"></span>
        <br><br><br>
        <button class="btn-submit" type="submit">Accedi</button>
    </form>
    <br>
</div>
</body>
</html>
<!--
<html>
<head>
<title>Title</title>
</head>
<body>
<form action="login" method="post">
<label for="email">Email:</label>
<input type="email" id="email" name="fieldEmail">
<label for="password">Password:</label>
<input type="password" id="password" name="fieldPassword">
<input type="submit" value="Invia">
</form>
</body>
</html>
-->