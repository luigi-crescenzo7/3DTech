
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
</head>
<body>

<form action="RegistrationServlet" method="post">
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