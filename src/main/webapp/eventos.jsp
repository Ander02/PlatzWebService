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

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">


    </head>

    <body >
        <!-- inicio do projeto aqui-->
    <ng-include src="'View/nav.html'"></ng-include>
    <div class="espaco"></div>

    <div class="head-pagina">
        <h1 class="titulo-meusEventos">Categorias</h1>
        <form class="form-inline form-pesquisa">
            <div class="form-group">                
                <select class="form-control btn btn-lg btn-warning-outline " id="selecione1">
                    <option>Selecione </option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                </select>
            </div>
            <div class="form-group">
                <input id="nome-evento" type="text" class="form-control" maxlength="30" placeholder="Nome do Evento">
            </div>
            <button class="btn btn-default" type="button">Pesquisar</button>
        </form><!-- /. form-inline form-pesquisa -->
    </div><!-- fim da div head-pagina -->


    <div>
        <div class="container">
            <div class="main">
                <div class="box">
                    <div class="hovereffect">
                        <img class="img-responsive" src="img/outras/icone.png" alt="">
                        <div class="overlay">
                            <h2>Nome Da Categoria</h2>
                            <a class="info" href="#">Clique e veja mais</a>
                        </div>
                    </div>

                </div>
                
                <div class="box">
                    <div class="hovereffect">
                        <img class="img-responsive" src="img/outras/icone2.png" alt="">
                        <div class="overlay">
                            <h2>Nome Da Categoria</h2>
                            <a class="info" href="#">Clique e veja mais</a>
                        </div>
                    </div>

                </div>
                
                <div class="box">
                    <div class="hovereffect">
                        <img class="img-responsive" src="img/outras/icone3.png" alt="">
                        <div class="overlay">
                            <h2>Nome Da Categoria</h2>
                            <a class="info" href="#">Clique e veja mais</a>
                        </div>
                    </div>

                </div>
                
                <div class="box">
                    <div class="hovereffect">
                        <img class="img-responsive" src="img/outras/icone4.png" alt="">
                        <div class="overlay">
                            <h2>Nome Da Categoria</h2>
                            <a class="info" href="#">Clique e veja mais</a>
                        </div>
                    </div>

                </div>
                
                <div class="box">
                    <div class="hovereffect">
                        <img class="img-responsive" src="img/outras/icone5.png" alt="">
                        <div class="overlay">
                            <h2>Nome Da Categoria</h2>
                            <a class="info" href="#">Clique e veja mais</a>
                        </div>
                    </div>

                </div>
                
                <div class="box">
                    <div class="hovereffect">
                        <img class="img-responsive" src="img/outras/icone6.png" alt="">
                        <div class="overlay">
                            <h2>Nome Da Categoria</h2>
                            <a class="info" href="#">Clique e veja mais</a>
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

    <!-- angular app script -->
    <script type="text/javascript" src="js/app.js"></script>

    <!-- link Angular -->
    <script src="lib/angular/angular-animate.js" type="text/javascript"></script>

    <!-- link Angular TOASTR CSS -->
    <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>

    <!-- link Angular TOASTR CSS -->
    <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>



</body>

</html>
