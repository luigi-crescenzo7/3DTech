<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <title>Admin Dashboard</title>
    <script defer src="js/hamburger.js"></script>
</head>
<body>
<%@ include file="admin-nav.jsp" %>
<aside class="sidebar">
    <ul class="hamburger_menu">
        <li><a href="${contextPath}/GestisciProdotti.jsp">Gestisci prodotti</a></li>
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
