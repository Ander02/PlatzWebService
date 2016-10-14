<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Ira aparecer todos os eventos que o usuario curtiu 
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
        <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

        <!-- Material Design Bootstrap -->
        <link href="../css/bootstrap/mdb.min.css" rel="stylesheet">

        <!--  styles (global) -->
        <link href="../css/style.css" rel="stylesheet">

        <!-- styles (usuario) -->
        <link href="../css/styleUsuario.css" rel="stylesheet">

        <!-- styles (efeito) -->
        <link href="../css/efeitos/eventosUsuario.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- angular util -->
        <script src="../js/util.js" type="text/javascript"></script>

        <!-- angular controller  -->

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

    </head>

    <body>
        <!-- inicio do projeto aqui-->
    <ng-include src="'../View/nav-usuario.html'"></ng-include>
    <div class="espaco"></div>

    <div class="head-pagina">
        <h1>Eventos</h1>
        <p>
            pagina dedica aos eventos que foram curtidos ou pelo menos tiveram suas partipações como talvez
        </p>
    </div>

    <div class="head-pagina">
    <h3>Eu vou</h3>
    </div>
    <!--Carousel Wrapper-->
    <div class="col-md-1"></div>
    <div id="multi-item-example" class="carousel slide carousel-multi-item col-md-10" data-ride="carousel">

        <!--Controls-->
        <div class="controls-top">
            <a class="btn-floating btn-lg orange" href="#multi-item-example" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
            <a class="btn-floating btn-lg orange" href="#multi-item-example" data-slide="next"><i class="fa fa-chevron-right"></i></a>
        </div>
        <!--/.Controls-->


        <!--Slides-->
        <div class="carousel-inner" role="listbox">

            <!--First slide-->
            <div class="carousel-item active">

                <div class="col-md-4 ">
                    <div class="card">                       
                        <div class="hovereffect">
                            <img class="img-fluid imagem-evento" src="../img/outras/plano-fundo.jpg" alt="">
                            <div class="overlay">
                                <h2>Nome do Evento</h2>
                                <p>
                                    <a href="#" data-toggle="tooltip" data-placement="bottom" title="Ver Detalhes">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                    <a href="#" data-toggle="tooltip" data-placement="bottom" title="Descurtir">
                                        <i class="fa fa-thumbs-o-down"></i>
                                    </a>
                                </p>
                            </div>
                        </div>                        
                        <div class="card-block info-evento-curtido">                          
                            <h4 class="card-title"><a>Nome do Evento <i class="fa fa-angle-right"></i></a></h4>
                            <p class="card-text">
                                <a> <i class="fa fa-building-o"></i> Nome da Empresa</a>
                            <p><strong><i class="fa fa-clock-o"></i> 27/02/2016</strong></p>
                            <p><strong><i class="fa fa fa-th-list"></i> Nome da Categoria</strong></p>
                            <a href="#" class="btn btn-default-outline">Ver Mais Detalhes</a>
                        </div>
                    </div>
                </div> <!-- coluna com o primeiro card -->
            </div> <!--/.First slide-->           
        </div><!--/.Slides-->      
    </div><!--/.Carousel Wrapper-->

    <div class="espaco"></div>

    <!-- /.fim do projeto-->


    <!-- SCRIPTS -->

    <!-- JQuery -->
    <script type="text/javascript" src="../lib/jquery/jquery-2.2.3.min.js"></script>

    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="../lib/bootstrap/tether.min.js"></script>

    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="../lib/bootstrap/bootstrap.min.js"></script>

    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="../lib/bootstrap/mdb.min.js"></script>
    
    <!-- tooltip -->
    <script src="../js/outros/tooltip.js" type="text/javascript"></script>
    
    <!-- link Angular animate -->
    <script src="../lib/angular/angular-animate.js" type="text/javascript"></script>

    <!-- link Angular css toastr -->
    <link href="../css/angular-toastr.css" rel="stylesheet" type="text/css"/>

    <!-- link TOASTR -->
    <script type="text/javascript" src="../lib/angular/angular-toastr.tpls.js"></script>



</body>

</html>

