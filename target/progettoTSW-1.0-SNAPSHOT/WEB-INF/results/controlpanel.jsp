<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">
    <title>Admin Dashboard</title>
    <script src="${contextPath}/js/hamburger.js" defer></script>
</head>
<body>
<nav>
    <div class="barra_di_nav">
        <a id="img-menu" href="#"><img class="three_hor_lines" src="${contextPath}/imgs/three_horizontal_lines_white.png" alt="menu"></a>
        <p>aaaaaaaaaaaaaa</p>
        <div class="search">
            <form>
                <input type="search" name="search" placeholder="Cerca...">
            </form>
        </div>
    </div>
</nav>
<aside class="sidebar">
    <ul class="hamburger_menu">
        <li><a href="${pageContext.request.contextPath}/GestisciProdotti.jsp">Gestisci prodotti</a></li>
        <!-- agg, elimina e modifica -->
        <li><a href="#">Gestisci utenti</a></li>
        <li><a href="#">Gestione ordini</a></li>
        <li><a class="logout" href="#">Logout</a></li>
    </ul>
</aside>
<section>
</section>
</body>
</html>
<!--<html>
<head>
<%--<%@include file="common.jsp" %>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<%--${contextPath}--%>/js/createProduct.js" defer></script>
<style>
.rtable {
display: inline-block;
vertical-align: top;
max-width: 100%;
overflow-x: auto;
white-space: nowrap;
border-collapse: collapse;
border-spacing: 0;
}

.rtable {
-webkit-overflow-scrolling: touch;
background-size: 10px 100%, 10px 100%;
/*background: radial-gradient(left, ellipse, rgba(0, 0, 0, .2) 0%, rgba(0, 0, 0, 0) 75%) 0 center,
radial-gradient(right, ellipse, rgba(0, 0, 0, .2) 0%, rgba(0, 0, 0, 0) 75%) 100% center;
*/
}

.rtable td:first-child {
background-image: linear-gradient(to right, rgba(255, 255, 255, 1) 50%, rgba(255, 255, 255, 0) 100%);
background-repeat: no-repeat;
background-size: 20px 100%;
}

.rtable td:last-child {
background-image: linear-gradient(to left, rgba(255, 255, 255, 1) 50%, rgba(255, 255, 255, 0) 100%);
background-repeat: no-repeat;
background-position: 100% 0;
background-size: 20px 100%;
}

.rtable th {
font-size: 11px;
text-align: left;
text-transform: uppercase;
background: #f2f0e6;
}

.rtable th,
.rtable td {
padding: 6px 12px;
border: 1px solid #d9d7ce;
}


body {
margin: 0;
padding: 25px;
color: #494b4d;
font-size: 14px;
line-height: 20px;
}

table {
margin-bottom: 30px;
}

a {
color: #ff6680;
}

</style>
</head>
<body>

<fieldset>
<legend>Pannello di controllo</legend>
<form id="formCreate" action="<%--${contextPath}--%>/ll/create" method="post"
enctype="multipart/form-data">

<label for="fieldProductName">Nome prodotto:</label>
<input id="fieldProductName" type="text" name="productName"><br><br>

<label for="fieldProductMark">Marchio:</label>
<input id="fieldProductMark" type="text" name="productMark"><br><br>

<label for="fieldProductDescription">Descrizione:</label>
<textarea id="fieldProductDescription" name="productDescription"></textarea><br><br>

<label for="fieldProductCaratheristics">Caratteristiche:</label>
<input id="fieldProductCaratheristics" type="text" name="productCaratheristics"><br><br>

<label for="fieldProductPrice">Prezzo:</label>
<input id="fieldProductPrice" type="number" name="productPrice"><br><br>

<label for="fieldProductWeight">Peso:</label>
<input id="fieldProductWeight" type="number" name="productWeight"><br><br>

<label for="fieldProductDiscount">Sconto:</label>
<input id="fieldProductDiscount" type="number" name="productDiscount"><br><br>

<label for="fieldProductCategory">Categoria:</label>
<select id="fieldProductCategory" name="productCategory" form="formCreate">
<option>
Stampanti 3D
</option>
<option>
Materiale plastico
</option>
</select>
<label for="fieldImage">File: </label>
<input type="file" name="productImage" id="fieldImage">

<input type="submit" value="Crea prodotto">
</form>
</fieldset>
<img src="<%--${contextPath}--%>/covers/logo.png" alt="aa">
<table class="rtable">
<thead>
<tr>
<th>id</th>
<th>nome</th>
<th>marchio</th>
<th>prezzo</th>
<th>peso</th>
<th>categoria</th>
</tr>
</thead>
<tbody>
<%--
<c:forEach items="${applicationScope.listProducts}" var="product">
    <tr>
    <td>${product.id}</td>
    <td>${product.nome}</td>
    <td>${product.marchio}</td>
    <td>${product.prezzo}</td>
    <td>${product.peso}</td>
    <td>${product.categoria.nome}</td>
    </tr>
</c:forEach> --%>
</tbody>
</table>
<br>
<br>
<br>
<form action="logout" method="post">
<input type="submit" value="Logout">
</form>
</body>
</html>
-->