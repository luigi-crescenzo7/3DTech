<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <title>Admin Dashboard</title>
    <script defer src="js/hamburger.js"></script>
</head>
<body>
    <nav>
        <div class="barra_di_nav">
            <a href="#"><img class="three_hor_lines" src="img/three_horizontal_lines_white.png"></a>
            <div class="search">
                <form>
                    <input type="search" name="search" placeholder="Cerca...">
                </form>
            </div>
        </div>
    </nav>
        <aside class="sidebar">
            <ul class="hamburger_menu">
            <li><a href="${pageContext.request.contextPath}/GestisciProdotti.jsp">Gestisci prodotti</a></li> <!-- agg, elimina e modifica -->
            <li><a href="#">Gestisci utenti</a> </li>
            <li><a href="#">Gestione ordini</a> </li>
            <li><a class="logout" href="#">Logout</a></li>
            </ul>
        </aside>
    <section>
    </section>
</body>
</html>
