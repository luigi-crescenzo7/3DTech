<%@ page contentType="text/html;charset=UTF-8" %>
<nav>
    <div class="barra_di_nav">
        <a id="img-menu"><img alt="immagine" class="three_hor_lines" src="${contextPath}/imgs/three_horizontal_lines_white.png"></a>
        <a id="test-text" href="${contextPath}/controlpanel/">Dashboard</a>
    </div>
    <div class="search">
        <p>${sessionScope.userSession.name}</p>
    </div>
    <input id="ctxPath" type="hidden" value="${contextPath}">
</nav>