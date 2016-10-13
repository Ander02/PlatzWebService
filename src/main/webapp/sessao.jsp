<%-- 
    Document   : sessao
    Created on : 11/10/2016, 15:46:39
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
            String token = request.getParameter("token");
            String perfil = request.getParameter("perfil");
            //HttpSession sessao = request.getSession();
            session.setAttribute("token", token);
            response.sendRedirect(perfil + "/");      

        %>
    </body>
</html>
