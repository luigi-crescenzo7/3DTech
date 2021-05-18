<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="./css/style.css?trroo" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@900&display=swap" rel="stylesheet">
</head>
<body>
<header>
    <!--<h1>3DTech</h1>-->
    <img class="logo" src="${pageContext.request.contextPath}/imgs/logoNew.png" alt="aa">
    <div class="test">
        <ul>
            <li>Home</li>
            <li>Chi siamo</li>
            <li>Dove trovarci</li>
        </ul>
    </div>
    <c:if test="${sessionScope.user != null}">
        <p>
            Ciao ${user.email}!
        </p>
    </c:if>
</header>

<div class="xxt">
    <ul id="categories">
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <li><a href="xx/login">Login</a></li>
                <li><a href="xx/registration">Registrazione</a></li>
            </c:when>
            <c:when test="${sessionScope.user.admin == true}">
                <li><a href="xx/account">Il mio profilo</a></li>
                <li><a href="">Pannello di controllo</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="xx/account">Il mio profilo</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<div class="elem">
    <p>Hey, i am sticked</p>
</div>
<br><br><br><br>
<form class="sp-cc">
    <p></p>
</form>
<br>
<br>
<br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
</body>
</html>