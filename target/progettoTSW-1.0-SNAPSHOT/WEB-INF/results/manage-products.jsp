<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <title>Gestione Prodotti - Dashboard</title>
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/product.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/modal.css" type="text/css">
    <script defer src="${contextPath}/js/hamburger.js"></script>
    <script src="${contextPath}/js/suffixes.js" defer></script>
    <script src="${contextPath}/js/updateProduct.js" defer></script>
    <script src="${contextPath}/js/showModal.js" defer></script>
</head>
<body>
<%@ include file="admin-nav.jsp" %>
<div id="alert-box" class="alert">
    <c:if test="${not empty requestScope.errorMessages}">
        <c:forEach items="${requestScope.errorMessages}" var="message">
            <p>${message}</p>
        </c:forEach>
        <script>
            document.querySelector("#alert-box").style.display = "block"
        </script>
    </c:if>
</div>
<div class="main-content">
    <div class="table-container">
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
                        <input type="hidden" value="${product.id}">
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
    <hr id="division-line">
    <div class="product-container">
        <div class="product-form">
            <div class="add_product_label"> Aggiungi prodotto</div>
            <div class="form-content">
                <form action="${contextPath}/product/create" name="aggiungi" method="post"
                      enctype="multipart/form-data">
                    <label for="nome">Nome</label>
                    <input class="input" type="text" id="nome" name="productName" required><br><br>
                    <label for="descrizione">Descrizione</label>
                    <input class="input" type="text" id="descrizione" name="productDescription" required><br><br>
                    <label for="marchio">Marchio</label>
                    <input class="input" type="text" id="marchio" name="productMark" required><br><br>
                    <label for="prezzo">Prezzo</label>
                    <input class="input" type="text" id="prezzo" step=".01" name="productPrice" required><br><br>
                    <label for="peso">Peso</label>
                    <input class="input" type="text" id="peso" step=".01" name="productWeight" required><br><br>
                    <label for="sconto">Sconto</label>
                    <input class="input" type="text" id="sconto" step=".01" name="productDiscount" required><br><br>
                    <div id="div-test">
                    </div>
                    <label for="fieldProductCategory">Categoria</label><br>
                    <select id="fieldProductCategory" name="productCategory">
                        <option selected>
                            -- Seleziona Categoria --
                        </option>
                        <c:forEach items="${applicationScope.listCategories}" var="category">
                            <option>
                                    ${category.nome}
                            </option>
                        </c:forEach>
                    </select><br>
                    <label for="fieldImage">File: </label><br>
                    <input type="file" name="productImage" id="fieldImage"><br><br>
                    <input class="agg" type="submit" value="Aggiungi prodotto">
                </form>
            </div>
        </div>

        <div class="product-form">
            <div class="mod_product_label">Modifica prodotto</div>
            <div class="mod_product_input">
                <label for="list">ID prodotto</label>
                <select id="list" required>
                    <option value="x"> -- Seleziona Id --</option>
                    <c:forEach items="${applicationScope.listProducts}" var="product">
                        <option value="${product.id}">${product.id}</option>
                    </c:forEach>
                </select>
                <form id="form" action="${contextPath}/product/update" name="modifica" enctype="multipart/form-data"
                      method="post">
                    <label for="product-name">Nome</label>
                    <input type="hidden" id="product-id" name="product-id">
                    <input class="input" type="text" id="product-name" name="productName" required><br><br>
                    <label for="product-description">Descrizione</label>
                    <input class="input" type="text" id="product-description" name="productDescription" required><br><br>
                    <label for="product-mark">Marchio</label>
                    <input class="input" type="text" id="product-mark" name="productMark" required><br><br>
                    <label for="product-price">Prezzo</label>
                    <input class="input" step=".01" type="number" id="product-price" name="productPrice" required><br><br>
                    <label for="product-weight">Peso</label>
                    <input class="input" step=".01" type="number" id="product-weight" name="productWeight" required><br><br>
                    <label for="product-discount">Sconto</label>
                    <input class="input" step=".01" type="number" id="product-discount" name="productDiscount" required><br><br>
                    <div id="inputs-container">
                    </div>
                    <label for="fieldProductCategory">Categoria</label><br>
                    <select id="fieldProductCategry" name="productCategory">
                        <option selected>-- Seleziona Categoria --</option>

                        <c:forEach items="${applicationScope.listCategories}" var="category">
                            <option>${category.nome}</option>
                        </c:forEach>

                    </select><br>
                    <label for="fieldImage">File: </label><br>
                    <input type="hidden" id="hidden-file" name="fieldImage">
                    <input type="file" name="productImage" id="feldImage"><br><br>
                    <input class="agg" type="submit" value="Modifica prodotto">
                </form>
            </div>
        </div>

        <div class="product-form">
            <div class="rem_product_label"> Rimuovi prodotto</div>
            <div class="rem_product_input">
                <form action="${contextPath}/product/remove" name="rimuovi" method="post">
                    <label for="idUser">ID prodotto</label>
                    <input class="input" type="number" id="idUser" name="userId"><br><br><br>
                    <input class="agg" type="submit" value="Rimuovi prodotto">
                </form>
            </div>
        </div>
    </div>
    <div class="modal" id="modal-box">
        <input type="hidden" name="modal-type" value="product">
        <div class="modal-content">
        </div>
    </div>
</div>
</body>
</html>
