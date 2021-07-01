<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <title>Admin Dashboard</title>
    <!--<link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">-->
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/product.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/modal.css" type="text/css">
    <script defer src="${contextPath}/js/hamburger.js"></script>
    <script src="${contextPath}/js/suffixes.js" defer></script>
    <script src="${contextPath}/js/showModal.js" defer></script>
</head>
<body>
<%@ include file="admin-nav.jsp" %>
<aside class="sidebar">
    <div class="hamburger_menu">
        <a href="${contextPath}/controlpanel/products">Gestisci prodotti</a>
        <a href="#">Gestisci utenti</a>
        <a href="#">Gestione ordini</a>
        <a class="logout" href="#">Logout</a>
    </div>
</aside>

<div class="product">
    <div class="add_product">
        <div class="add_product_label"> Aggiungi prodotto</div>
        <div class="add_product_input">
            <form id="formBello" action="${contextPath}/ll/create" name="aggiungi" method="post"
                  enctype="multipart/form-data">
                <label for="nome">Nome</label>
                <input class="input" type="text" id="nome" name="productName"><br><br>
                <label for="descrizione">Descrizione</label>
                <input class="input" type="text" id="descrizione" name="productDescription"><br><br>
                <label for="marchio">Marchio</label>
                <input class="input" type="text" id="marchio" name="productMark"><br><br>
                <label for="prezzo">Prezzo</label>
                <input class="input" type="number" id="prezzo" name="productPrice"><br><br>
                <label for="peso">Peso</label>
                <input class="input" type="number" id="peso" name="productWeight"><br><br>
                <label for="sconto">Sconto</label>
                <input class="input" type="number" id="sconto" name="productDiscount" value=""><br><br>
                <div id="div-test">
                </div>
                <label for="fieldProductCategory">Categoria</label>
                <select id="fieldProductCategory" name="productCategory" form="formBello">
                    <option selected>
                        -- Seleziona Categoria --
                    </option>
                    <c:forEach items="${applicationScope.listCategories}" var="category">
                        <option>
                                ${category.nome}
                        </option>
                    </c:forEach>
                </select>
                <label for="fieldImage">File: </label>
                <input type="file" name="productImage" id="fieldImage"><br><br>
                <input class="agg" type="submit" value="Aggiungi prodotto">
            </form>
        </div>
    </div>

    <div class="mod_product">
        <div class="mod_product_label"> Modifica prodotto</div>
        <div class="mod_product_input">
            <form action="" name="modifica" method="">
                <label for="id2">ID prodotto</label>
                <input class="input" type="number" id="id2" name="id" value=""><br><br>
                <label for="nome2">Nome</label>
                <input class="input" type="text" id="nome2" name="nome" value=""><br><br>
                <label for="descrizione2">Descrizione</label>
                <input class="input" type="text" id="descrizione2" name="descrizione" value=""><br><br>
                <label for="marchio2">Marchio</label>
                <input class="input" type="text" id="marchio2" name="marchio" value=""><br><br>
                <label for="prezzo2">Prezzo</label>
                <input class="input" type="number" id="prezzo2" name="prezzo" value=""><br><br>
                <label for="peso2">Peso</label>
                <input class="input" type="number" id="peso2" name="peso" value=""><br><br>
                <label for="sconto2">Sconto</label>
                <input class="input" type="number" id="sconto2" name="sconto" value=""><br><br>
                <label for="categoria2">Categoria</label>
                <input class="input" type="text" id="categoria2" name="categoria" value=""><br><br><br>
                <input class="agg" type="submit" value="Modifica prodotto">
            </form>
        </div>
    </div>

    <div class="rem_product">
        <div class="rem_product_label"> Rimuovi prodotto</div>
        <div class="rem_product_input">
            <form action="" name="rimuovi" method="">
                <label for="id">ID prodotto</label>
                <input class="input" type="number" id="id" name="id" value=""><br><br><br>
                <input class="agg" type="submit" value="Rimuovi prodotto">
            </form>
        </div>
    </div>
</div>
<hr id="division-line">
<button id="show-modal">Click me</button>

<div class="table-items">
    <table class="rtable">
        <thead>
        <tr>
            <th>info</th>
            <th>id</th>
            <th>nome</th>
            <th>marchio</th>
            <th>prezzo</th>
            <th>peso</th>
            <th>categoria</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${applicationScope.listProducts}" var="product">
            <tr>
                <td>
                    <button class="show-info" value="${product.id}">clicca</button>
                    <input type="hidden" id="valoreNascosto" value="${product.id}">
                </td>
                <td>${product.id}</td>
                <td>${product.nome}</td>
                <td>${product.marchio}</td>
                <td>${product.prezzo}</td>
                <td>${product.peso}</td>
                <td>${product.categoria.nome}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="modal" id="modal-box">
    <div class="modal-content">
        <p>Nome: Nome bello</p>
        <p>Cognome: Cognome bello</p>
        <img src="${contextPath}/imgs/logoNew.svg" alt="aa">
    </div>
</div>
</body>
</html>
