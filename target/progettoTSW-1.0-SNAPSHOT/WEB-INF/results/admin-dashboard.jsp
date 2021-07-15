<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="common.jsp" %>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${contextPath}/css/navbar.css" type="text/css">
    <script src="${contextPath}/js/chart.js" defer></script>
    <script src="${contextPath}/js/hamburger.js" defer></script>
</head>
<body>
<%@ include file="admin-nav.jsp" %>

<div class="container">
    <canvas id="chart">
    </canvas>
</div>

</body>
</html>