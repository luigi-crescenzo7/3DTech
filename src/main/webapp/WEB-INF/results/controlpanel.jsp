<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<fieldset>
    <legend>Pannello di controllo</legend>
    <form id="test" action="ShowProducts" method="post">
        <button form="test">Visualizza prodotti</button>
    </form>
</fieldset>
<br>
<br>
<br>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
