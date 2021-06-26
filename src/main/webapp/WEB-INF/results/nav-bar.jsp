<!--<script src="/js/user_logged.js" defer></script>-->
<img id="logo" src="${contextPath}/imgs/logoNew.svg" alt="Logo">
<header>
    <c:if test="${sessionScope.userSession == null}">
        <div class="accesso">
            <a href="${contextPath}/xx/registration">Registrati</a>
            <a href="${contextPath}/xx/login">Login</a>
        </div>
    </c:if>
    <!-- todo: mettere controllo sullo user memorizzato in sessione se non è admin-->
    <c:if test="${sessionScope.userSession != null}"> <!--pageContext.request.getSession().getAttribute('user') != null -->
        <div class="utente_loggato">
            <button class="email_user">${sessionScope.userSession.email}</button>
            <a href="#pagina_carello">
                <img class="carrello" src="${contextPath}img/carrello.png">
            </a>
            <div class="opzioni">
                <ul>
                    <form action="${contextPath}/xx/logout" method="post">
                        <li><a href="${contextPath}/xx/account">Account</a></li>
                        <li><a href="${contextPath}">Ordini</a></li>
                        <li>
                            <button id="logout-btn">Logout
                            </button>
                        </li>
                    </form>
                </ul>
            </div>
        </div>
    </c:if>

</header>
<nav>
    <ul class="barra_di_nav">
        <li><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/categorie/">Categorie</a></li>
        <li><a href="#">Chi Siamo</a></li>
        <li><a href="#">Contatti</a></li>
    </ul>
    <div class="search">
        <form>
            <input type="search" name="search" placeholder="Cerca...">
        </form>
    </div>
</nav>