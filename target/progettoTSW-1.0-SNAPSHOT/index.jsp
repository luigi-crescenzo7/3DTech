<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/results/common.jsp" %>
    <!--<link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css">-->
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
    <script src="${contextPath}/js/search.js" defer></script>
    <title>3DTech</title>
</head>
<body>
<input id="path" type="hidden" value="${contextPath}">
<%@include file="WEB-INF/results/nav-bar.jsp" %>
<section class="prima">
    Sconti
</section>
<section class="seconda">
    Tendenze
</section>
<section class="terza">
    Nuovi articoli
</section>
<section class="quarta">
    Recenti
</section>
<jsp:include page="WEB-INF/results/footer.jsp"/>
</body>
</html>