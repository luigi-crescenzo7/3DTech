<!--<script src="/js/user_logged.js" defer></script>-->
<img src="${contextPath}/imgs/logoNew.png" alt="Logo">
<header>
    <c:if test="${sessionScope.user == null}">
        <div class="accesso">
            <a href="${contextPath}/xx/registration">Registrati</a>
            <a href="${contextPath}/xx/login">Login</a>
        </div>
    </c:if>
    <c:if test="${sessionScope.user != null}"> <!--pageContext.request.getSession().getAttribute('user') != null -->
        <div class="utente_loggato">
            <button class="email_user">${sessionScope.user.email}</button>
            <a href="#pagina_carello">
                <img class="carrello" src="${contextPath}img/carrello.png">
            </a>
            <div class="opzioni">
                <ul>
                    <form action="${contextPath}/xx/logout" method="post">
                        <li><a href="#account">Account</a></li>
                        <li><a href="#ordini">Ordini</a></li>
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
        <li><a href="#">Home</a></li>
        <li><a href="${contextPath}Categorie.jsp">Categorie</a></li>
        <li><a href="#">Chi Siamo</a></li>
        <li><a href="#">Contatti</a></li>
        <li>
            <div class="search">
                <form>
                    <input type="search" name="search" placeholder="Cerca...">
                </form>
            </div>
        </li>
    </ul>
</nav>