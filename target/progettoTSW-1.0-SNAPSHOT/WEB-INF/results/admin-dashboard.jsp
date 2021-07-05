<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <title>Admin Dashboard</title>
    <!--<link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">-->
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.3.2/dist/chart.min.js" defer></script>
    <script src="${contextPath}/js/chart.js" defer></script>
    <script src="${contextPath}/js/hamburger.js" defer></script>
</head>
<body>
<%@ include file="admin-nav.jsp" %>
<input id="hidden-path" type="hidden" value="${contextPath}">
<aside class="sidebar">
    <div class="hamburger_menu">
        <a href="${contextPath}/controlpanel/products">Gestisci prodotti</a>
        <!-- agg, elimina e modifica -->
        <a href="#">Gestisci utenti</a>
        <a href="#">Gestione ordini</a>
        <a class="logout" href="#">Logout</a>
    </div>
</aside>
<div class="container">
    <canvas id="chart">
    </canvas>
</div>
<!--
<div id="canvas">

</div>-->
</body>
</html>