<%-- 
    Document   : QuebraSessao
    Created on : 03/11/2016, 15:42:55
    Author     : 15153766
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="display: none">

        <%
            session.invalidate();
            response.sendRedirect("/index.jsp");
        %>
    </body>
</html>
