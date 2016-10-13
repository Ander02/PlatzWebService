<%-- 
    Document   : index.jsp
    Created on : 10/10/2016, 14:02:37
    Author     : 15153766
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html ng-app="platz">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="lib/angular/angular.js" type="text/javascript"></script>

        <script src="js/app.js" type="text/javascript"></script>

        <script src="lib/angular/angular-toastr.tpls.js" type="text/javascript"></script>

        <script src="lib/angular/angular-animate.js" type="text/javascript"></script>

        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/controller/loginController.js" type="text/javascript"></script>
    </head>

    <body ng-controller="loginController" >
        <%
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
        %>

        <form>
            <input type="text" id="email" value="<%out.println(email);%>" ng-model="login.email" />
            <input type="text" id="senha" value="<%out.println(senha);%>" ng-model="login.senha" />      
            <button ng-click="logar()">Clique para confirmar o login</button>
            {{contaSession}}
        </form>
    </body>

    <%
        HttpSession sessao = request.getSession();
        sessao.setAttribute("Token", "asas");
    %>
</html>
