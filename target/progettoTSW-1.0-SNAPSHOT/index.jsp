<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="WEB-INF/results/common.jsp" %>
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/navbar.css" type="text/css">
    <link rel="stylesheet" href="${contextPath}/css/cssprogetto/footer.css" type="text/css">
    <script src="${contextPath}/js/slider.js" defer></script>
    <title>3DTech - Home</title>
</head>
<body>
<input id="path" type="hidden" value="${contextPath}">
<%@include file="WEB-INF/results/nav-bar.jsp" %>
<section class="main">
        <div class="slick-container">
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
            <div>
                <img height="150px" width="150px" src="${contextPath}/images/logoNew.svg" alt="aa">
            </div>
        </div>
</section>
<jsp:include page="WEB-INF/results/footer.jsp"/>
</body>
</html>