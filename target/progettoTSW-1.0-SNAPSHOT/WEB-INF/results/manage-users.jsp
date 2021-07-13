<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <script defer src="${contextPath}/js/hamburger.js"></script>
</head>
<body>
<%@include file="admin-nav.jsp" %>
<div class="user">
    <table class="rtable">
        <thead>
        <tr>
            <th>id</th>
            <th>email</th>
            <th>nome</th>
            <th>cognome</th>
            <th>telefono</th>
            <th>indirizzo</th>
            <th>città</th>
            <th>cap</th>
            <th>privilegi</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.street}</td>
                <td>${user.city}</td>
                <td>${user.ZIPCode}</td>
                <td>${user.admin}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="rem_user">

    <div class="rem_user_label"> Rimuovi utente</div>

    <div class="rem_user_input">

        <form action="" name="rimuovi" method="">
            <label for="email_">Email utente</label>
            <input class="input" type="text" id="email_" name="email" value=""><br><br><br>
            <input class="agg" type="submit" value="Rimuovi utente">
        </form>
    </div>
</div>
</body>
</html>
