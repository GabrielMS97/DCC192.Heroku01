<%-- 
    Document   : index
    Created on : 18/05/2018, 22:22:04
    Author     : ice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mensagem: ${mensagem}</h1>
        <ul>
            <c:forEach var = 'nome' items = '${nomes}'>
                <li>${nome}</li>
                </c:forEach>
        </ul>
    </body>
</html>
