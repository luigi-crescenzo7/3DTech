<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="common.jsp" %>
</head>
<body>
<form action="${contextPath}" method="post">

    <label for="name">Nome:</label>
    <input id="name" type="text" name="fieldName"><br><br>

    <label for="mark">Marchio:</label>
    <input id="mark" type="text" name="fieldMark"><br><br>

    <label for="description"></label>
    <textarea id="description" name="fieldDescription"></textarea><br><br>

    <label for="color">Colore:</label>
    <input id="color" type="text" name="fieldColor"><br><br>

    <label for="temperature"></label>
    <input id="temperature" type="number" name="fieldTemperature"><br><br>

    <label for="price">Prezzo:</label>
    <input id="price" type="number" name="fieldPrice"><br><br>

    <label for="weight"></label>
    <input id="weight" type="number" name="fieldWeight">
</form>
</body>
</html>