<%@ page contentType="text/html;charset=UTF-8" %>
<nav id="admin-nav">
    <input id="ctxPath" type="hidden" value="${contextPath}">
    <div class="barra_di_nav">
        <a id="img-menu" onclick="openNav()"><img alt="immagine" class="three_hor_lines"
                                                  src="${contextPath}/imgs/three_horizontal_lines_white.png"></a>
        <a id="test-text" href="${contextPath}/controlpanel/">Dashboard</a>
    </div>
    <div class="search">
        <p>${sessionScope.userSession.name}</p>
    </div>
</nav>
<aside id="mySidenav" class="sidebar sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="${contextPath}/controlpanel/products">Gestisci prodotti</a>
    <a href="${contextPath}/controlpanel/users">Gestisci utenti</a>
    <a href="${contextPath}/controlpanel/categories">Gestione categorie</a>
    <form action="${contextPath}/account/logout" method="post">
        <a class="logout">
            <button type="submit">Logout</button>
        </a>
    </form>
</aside>