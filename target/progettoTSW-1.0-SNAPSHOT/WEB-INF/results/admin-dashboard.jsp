<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.3.2/dist/chart.min.js" defer ></script>
    <script src="${contextPath}/js/hamburger.js" defer></script>
    <script src="${contextPath}/js/chart.js" defer> let contextPath = "${contextPath}"</script>
</head>
<body>
<%@ include file="admin-nav.jsp" %>
<aside class="sidebar">
    <ul class="hamburger_menu">
        <li><a href="${contextPath}/">Gestisci prodotti</a></li>
        <!-- agg, elimina e modifica -->
        <li><a href="#">Gestisci utenti</a></li>
        <li><a href="#">Gestione ordini</a></li>
        <li><a class="logout" href="#">Logout</a></li>
    </ul>
</aside>
<div id="canvas" title="${contextPath}">
    <canvas id="chart" title="${contextPath}">
    </canvas>
</div>
</body>
</html>