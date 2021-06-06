<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath + "js/createProduct.js"}" defer></script>
</head>
<body>

<fieldset>
    <legend>Pannello di controllo</legend>
    <!--<form id="test" action="ShowProducts" method="post">
        <button form="test">Visualizza prodotti</button>
    </form>-->
    <form action="create" method="post">
        <label for="fieldProductName">Nome prodotto:</label>
        <input id="fieldProductName" type="text" name="productName"><br><br>

        <label for="fieldProductMark">Marchio:</label>
        <input id="fieldProductMark" type="text" name="productMark"><br><br>

        <label for="fieldProductDescription">Descrizione:</label>
        <textarea id="fieldProductDescription"></textarea><br><br>

        <label for="fieldProductCaratheristics">Caratteristiche:</label>
        <input id="fieldProductCaratheristics" type="text" name="productCaratheristics"><br><br>

        <label for="fieldProductPrice">Prezzo:</label>
        <input id="fieldProductPrice" type="number" name="productPrice"><br><br>

        <label for="fieldProductWeight">Peso:</label>
        <input id="fieldProductWeight" type="number" name="productWeight"><br><br>

        <label for="fieldProductDiscount">Sconto:</label>
        <input id="fieldProductDiscount" type="number" name="productDiscount"><br><br>

        <label for="fieldProductCategory">Categoria:</label>
        <input id="fieldProductCategory" type="text" name="productCategory"><br><br>

        <input type="submit" value="Crea prodotto">
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
