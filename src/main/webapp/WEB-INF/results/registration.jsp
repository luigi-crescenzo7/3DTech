<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">
    <title>Registrazione Utente - 3DTech</title>
    <script src="${contextPath}/js/checkUser.js" defer></script>
</head>
<body>
<%@include file="NavigationBar.jsp" %>
<div class="registrazione">
    <form action="${contextPath}/xx/registration" method="post">
        <label for="email">Email</label>
        <input class="input" type="email" id="email" name="fieldEmail"><br><br>
        <label for="nome">Nome</label>
        <input class="input" type="text" id="nome" name="fieldName"><br><br>
        <label for="cognome">Cognome</label>
        <input class="input" type="text" id="cognome" name="fieldSurname"><br><br>
        <label for="password">Password</label>
        <input class="input" type="password" id="password" name="fieldPassword"><br><br><br>
        <label for="telefono">Telefono</label>
        <input class="input" type="tel" id="telefono" name="telefono"><br><br>
        <label for="indirizzo">Indirizzo</label>
        <input class="input" type="text" id="indirizzo" name="indirizzo"><br><br>
        <label for="citta">Città</label>
        <input class="input" type="text" id="citta" name="citta"><br><br>
        <label for="cap">CAP</label>
        <input class="input" type="text" id="cap" name="cap"><br><br>

        <input class="btn-submit" type="submit" value="Registrati">
    </form>
</div>
</body>
</html>

<!--<html>
<head>
<title>Registrazione</title>
</head>
<body>

<form action="registration" method="post">
<fieldset>
<legend>aaa</legend>
<label for="email">Email:</label>
<input type="email" id="email" name="fieldEmail"><br><br>
<label for="password">Password:</label>
<input type="password" id="password" name="fieldPassword"><br><br>
<label for="name">Name:</label>
<input type="text" id="name" name="fieldName"><br><br>
<label for="surname">Surname:</label>
<input type="text" id="surname" name="fieldSurname"><br><br>
<label for="dateOfBirth">Data di nascita:</label>
<input type="date" id="dateOfBirth" name="fieldDateOfBirth"><br><br>
<label for="phoneNumber">Numero di telefono:</label>
<input type="number" id="phoneNumber" name="fieldPhoneNumber"><br><br>
<label for="zipCode">CAP:</label>
<input type="number" id="zipCode" name="fieldZIPCode"><br><br>
<label for="citta">Città:</label>
<input type="text" id="citta" name="fieldCity"><br><br>
<label for="street">Via (Nome + Numero civico):</label>
<input type="text" id="street" name="fieldStreet"><br><br>
<input type="submit" value="Invia">
</fieldset>
</form>
</body>
</html>
-->