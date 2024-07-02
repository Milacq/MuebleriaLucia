
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope.usuario}">
    <% response.sendRedirect("loginadmin.jsp"); %>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link href="css/dash.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="gradient-div">
        <h1 class="centered-title">Visitas a la página web</h1>
            <div class="ha" id="sfc6byl42zwr2rrsuqhjg8xf5x5rgldylzb"></div>        
        </div>
        <script type="text/javascript" src="https://counter8.optistats.ovh/private/counter.js?c=6byl42zwr2rrsuqhjg8xf5x5rgldylzb&down=async" async></script>
        <noscript><a href="https://www.contadorvisitasgratis.com" title="contador de visitas para mi blog"><img src="https://counter8.optistats.ovh/private/contadorvisitasgratis.php?c=6byl42zwr2rrsuqhjg8xf5x5rgldylzb" border="0" title="contador de visitas para mi blog" alt="contador de visitas para mi blog"></a></noscript>
    </body>
</html>
