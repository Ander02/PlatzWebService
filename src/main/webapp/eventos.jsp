<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Pagina onde ira exibir os eventos, onde sera possivel filtrar os eventos
-->
<html lang="pt-br" ng-app="platz">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <title>Platz - Suas rotas, Seus Eventos</title>

        <!-- Font Awesome -->
        <link href="css/font/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

        <!-- Material Design Bootstrap -->
        <link href="css/bootstrap/mdb.min.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="css/style.css" rel="stylesheet">

        <!-- Your custom styles (efeito) -->
        <link href="css/efeitos/eventos.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="js/app.js"></script>

        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/services/loginService.js" type="text/javascript"></script>

        <script src="js/controller/loginController.js" type="text/javascript"></script>

        <script src="js/controller/eventosController.js" type="text/javascript"></script>
        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">


    </head>

    <body>
        <!-- inicio do projeto aqui-->
        <%
            try {
                String token = session.getAttribute("token").toString();
                if (token == null) {
                    out.print("<input type='hidden' id='token' name='token' value ='' >");
                } else {
                    out.print("<input type='hidden' id='token' name='token' value ='" + token + "' >");
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar sessão " + e.getMessage());
                out.print("<input type='hidden' id='token' name='token' value ='' >");
            }
        %>
        <div ng-controller="loginController">
            <ng-include ng-if="conta == null" src="'View/nav.html'" ></ng-include>
            <ng-include ng-if="conta.perfil === 'Administrador'" src="'View/nav-adm.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Empresa'" src="'View/nav-empresa.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Usuario'" src="'View/nav-usuario.html'"  ></ng-include>
        </div>    <div ng-controller="eventosController">

            <div class="espaco"></div>


            <div class="col-md-12 card-group">
                <div class="head-pagina">
                    <h1> Categoria dos Eventos </h1>
                </div>
                <div class="card-block col-md-3" ng-repeat="categoria in categorias">
                    <div class="hovereffect">
                        <img class="img-responsive img-fluid" ng-src="{{baixarImagemCategoria(categoria.id)}}" onerror='this.src = "img/placeholder.png"' alt="">                            
                        <div class="overlay">
                            <h2 ng-bind="categoria.nome"></h2>
                            <a class="info" href="eventosCategoria.jsp?categoria={{categoria.id}}" > Clique e veja mais</a>
                            <a ></a>
                        </div>
                    </div>  
                </div>

            </div>

            <ng-include src="'View/footer.html'"></ng-include>
            <!-- /fim do projeto-->


            <!-- SCRIPTS -->

            <!-- JQuery -->
            <script type="text/javascript" src="lib/jquery/jquery-2.2.3.min.js"></script>

            <!-- Bootstrap tooltips -->
            <script type="text/javascript" src="lib/bootstrap/tether.min.js"></script>

            <!-- Bootstrap core JavaScript -->
            <script type="text/javascript" src="lib/bootstrap/bootstrap.min.js"></script>

            <!-- MDB core JavaScript -->
            <script type="text/javascript" src="lib/bootstrap/mdb.min.js"></script>

            <!-- link Angular -->
            <script src="lib/angular/angular-animate.js" type="text/javascript"></script>

            <!-- link Angular TOASTR CSS -->
            <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>

            <!-- link Angular TOASTR CSS -->
            <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>

            <script src="js/outros/randomCores.js" type="text/javascript"></script>

        </div>

    </body>

</html>
