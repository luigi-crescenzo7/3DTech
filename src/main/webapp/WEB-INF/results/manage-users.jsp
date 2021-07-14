<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/user-dashboard.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/modal.css" type="text/css">
    <script src="${contextPath}/js/showModal.js" defer></script>
    <script defer src="${contextPath}/js/hamburger.js"></script>
</head>
<body>
<%@include file="admin-nav.jsp" %>
<div class="table-container">
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
<hr id="division-line">
<div class="flex-container-user">

    <div id="user-orders" class="rem_user">
        <div class="rem_user_label">
            <label for="users-list">Ordini</label>
        </div>

        <div class="rem_user_input">
            <select id="users-list" name="userId">
                <option> -- Seleziona id --</option>
                <c:forEach items="${requestScope.listUsers}" var="user">
                    <option value="${user.id}">${user.id}</option>
                </c:forEach>
            </select>
            <button class="agg" id="orders-btn" type="submit">get</button>
            <div class="orders-content">
            </div>
        </div>
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

</div>
<div class="modal" id="modal-box">
    <input type="hidden" name="modal-type" value="user">
    <div class="modal-content">
    </div>
</div>
</body>
</html>
