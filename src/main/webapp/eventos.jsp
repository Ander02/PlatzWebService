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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">

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

        <script src="js/controller/loginController.js" type="text/javascript"></script>

        <script src="js/controller/eventosController.js" type="text/javascript"></script>
        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">


    </head>

    <body >
        <!-- inicio do projeto aqui-->
    <ng-include src="'View/nav.html'" ng-controller="loginController"></ng-include>
    <div ng-controller="eventosController">
        <div class="espaco"></div>

        <div class="head-pagina">
            <h1 class="titulo-meusEventos">Categorias</h1>
            <form class="form-inline form-pesquisa" action="buscaEvento.jsp" method="get">
                <div class="form-group">                
                    <select class="form-control btn btn-lg btn-warning-outline " id="selecione1" name="categoriaId" ng-options="categoria.id.toString() as categoria.nome for categoria in categorias" ng-model="categoriaBusca">
                        <option value="">Selecione</option>
                    </select>
                </div>
                <div class="form-group">
                    <input id="nome-evento" name="nome-evento" type="text" class="form-control" maxlength="30" placeholder="Nome do Evento">
                </div>
                <input class="btn btn-default btn-sm" type="submit" value="Pesquisar">
            </form><!-- /. form-inline form-pesquisa -->
        </div><!-- fim da div head-pagina -->


        <div>
            <div class="container">
                <div class="main">
                    <div class="box" ng-repeat="categoria in categorias">

                        <div class="hovereffect">
                            <img class="img-responsive" ng-src="{{baixarImagem(categoria.id)}}" onerror='this.src = "img/logo.png"' alt="">                            
                            <div class="overlay">
                                <h2>{{categoria.nome}}</h2>
                                <a class="info" href="eventosCategoria.jsp?categoria={{categoria.id}}" > Clique e veja mais</a>
                                <a ></a>
                            </div>
                        </div>
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

    </div>

</body>

</html>
