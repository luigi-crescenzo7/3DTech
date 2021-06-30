<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common.jsp" %>
    <!--<link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">-->
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/login.css" type="text/css">
    <title>LoginAdmin</title>
</head>
<body class="admin">
<div class="login_admin">
    <div>
        <p>Login Amministratore</p>
    </div>
    <form action="${contextPath}/xx/loginadmin" method="post">
        <label for="email">Email</label>
        <input class="input_admin" type="email" id="email" name="fieldEmail"><br><br>
        <label for="password">Password</label>
        <input class="input_admin" type="password" id="password" name="fieldPassword"><br><br><br>
        <input class="accedi_admin" type="submit" name="Submit" value="Accedi">
    </form>
    <br>
</div>
</body>
</html>