<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/results/common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/index.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
    <script src="${contextPath}/js/slider.js" defer></script>
    <title>3DTech - Home</title>
</head>
<body>
<input id="path" type="hidden" value="${contextPath}">
<%@include file="WEB-INF/results/nav-bar.jsp" %>
<div class="home">

    <div class="printers-container">

        <span class="printers-label">Migliori stampanti 3D</span>
        <div class="container-item">
            <img src="${contextPath}/imgs/test/favpng_hewlett-packard-enterprise-printer-managed-print-services.png"
                 alt="immagine">
            <span>1. Nome Prodotto Numero 1</span>
        </div>

        <div class="container-item">
            <img src="${contextPath}/imgs/test/favpng_hewlett-packard-enterprise-printer-managed-print-services.png"
                 alt="immagine">
            <span>2. Nome Prodotto Numero 2</span>
        </div>

        <div class="container-item">
            <img src="${contextPath}/imgs/test/favpng_hewlett-packard-enterprise-printer-managed-print-services.png"
                 alt="immagine">
            <span>3. Nome Prodotto Numero 3</span>
        </div>

    </div>

    <div class="brands-container">

        <span class="brands-label">Migliori marchi 2021</span>
        <div class="container-item">
            <img src="${contextPath}/imgs/brands/kisspng-intel-dell-logo-business-computer-icons-intel-logo-5b3f706848fc28.452094801530884200299.png"
                 alt="immagine">
            <span>1. Nome primo marchio</span>
        </div>

        <div class="container-item">
            <img src="${contextPath}/imgs/brands/kisspng-intel-dell-logo-business-computer-icons-intel-logo-5b3f706848fc28.452094801530884200299.png"
                 alt="immagine">
            <span>2. Nome secondo marchio</span>
        </div>

        <div class="container-item">
            <img src="${contextPath}/imgs/brands/kisspng-intel-dell-logo-business-computer-icons-intel-logo-5b3f706848fc28.452094801530884200299.png"
                 alt="immagine">
            <span>3. Nome terzo marchio</span>
        </div>

    </div>

</div>


<div class="sconti">
    <div class="content">
        <div class="label_sconti">Sconti</div>
        <div class="slider">
            <div>
                <img src="${contextPath}/imgs/test/favpng_3d-printing-filament-aleph-objects-printer.png"
                     alt="immagine">
                <span>Nome Prodotto Numero 1</span>
            </div>
            <div>
                <img src="${contextPath}/imgs/test/favpng_hewlett-packard-enterprise-printer-managed-print-services.png"
                     alt="immagine">
                <span>Nome Prodotto Numero 2</span>
            </div>
            <div>
                <img src="${contextPath}/imgs/categorie/stampante3d.png" alt="immagine">
                <span>Nome Prodotto Numero 3</span>
            </div>
            <div>
                <img src="${contextPath}/imgs/categorie/filamenti.png" alt="immagine">
                <span>Nome Prodotto Numero 4</span>
            </div>
        </div>
    </div>
</div>
<!--<section class="main">
        <div class="slick-container">
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
        </div>
</section>-->
<jsp:include page="WEB-INF/results/footer.jsp"/>
</body>
</html>