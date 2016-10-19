<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
index do usuario onde mostra algumas sugestões de eventos
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

        <!-- Your custom styles (optional) -->
        <link href="../css/style.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="../css/styleUsuario.css" rel="stylesheet">

        <link href="../css/efeitos/indexUsuario.css" rel="stylesheet" type="text/css"/>

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
        <h1 >Eventos Proximos </h1>
        <p>Eventos que você curtiu e estão proximos de acontecer! Não se esqueça e participe, sua diversão é nossa prioridade</p>
    </div>


    <!--Section: COM OS EVENTOS-->
    <section class="section magazine-section section-eventos-proximos">

        <!--First row-->
        <div class="row text-xs-left ">

            <!--coluna com o evento grande-->
            <div class="col-lg-6 col-md-12">

                <!--Evento maior-->
                <div class="single-news">
                    
                     <div>
                            <div class="hovereffect">
                                <img class="img-responsive img-eventoProximo" src="../img/outras/plano-fundo.jpg" alt="">
                                <div class="overlay">
                                    <h2>Nome do Evento</h2>
                                    <p> 
                                        <a href="#">Clique aqui e veja o evento</a>
                                    </p> 
                                </div>
                            </div>
                        </div>
                    <br>

                    <!--Informações-->
                    <div class="news-data">
                        <h5><i class="fa fa-car"></i> Local do evento</h5>
                        <p><strong><i class="fa fa-clock-o"></i> 27/02/2016</strong></p>
                    </div>

                    <h5><i class="fa fa-building-o"></i> <a>Nome da Empresa</a></h5>

                    <p> parte da descrição com no maximo 30 caracteres
                    </p>
                    
                    <a class="btn btn-default-outline"> Ver Detalhes </a>

                </div><!--/evento proximo (maior)-->
            </div><!--/First column-->

            <!--Segunda coluna com os eventos menores-->
            <div class="col-lg-6 col-md-12 coluna-dois">


                <!--evento menor-->
                <div class="col-md-6">
                    <div class="col-md-10">
                        <!--Imagem do evento-->
                        <div class="view overlay hm-white-slight">
                            <img class="animated pulse" src="http://mdbootstrap.com/images/regular/nature/img%20(75).jpg">
                            <a>
                                <div class="mask"></div>
                            </a>
                        </div>
                    </div>

                    <!--Informações-->
                    <div class="col-md-9">                           
                        <h4> <a> Nome do Evento <i class="fa fa-angle-right"></i> </a> </h4>
                        <a>Nome da Empresa</a>
                        <p><strong><i class="fa fa-clock-o"></i> 27/02/2016</strong></p>
                        
                        <a class="btn btn-sm btn-default-outline"> Ver Detalhes </a>
                    </div>

                </div><!--/eventos menor-->
            </div> <!--/Segunda coluna, com os quatro quadrados de eventos-->           
        </div> <!--/First row-->
    </section><!--/Section: eventos-->
    
<ng-include src="'../View/footer.html'"></ng-include>    <!-- /.fim do projeto-->


    <!-- SCRIPTS -->

    <!-- JQuery -->
    <script type="text/javascript" src="../lib/jquery/jquery-2.2.3.min.js"></script>

    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="../lib/bootstrap/tether.min.js"></script>

    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="../lib/bootstrap/bootstrap.min.js"></script>

    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="../lib/bootstrap/mdb.min.js"></script>

    <!-- link Angular animate -->
    <script src="../lib/angular/angular-animate.js" type="text/javascript"></script>

    <!-- link Angular css toastr -->
    <link href="../css/angular-toastr.css" rel="stylesheet" type="text/css"/>

    <!-- link TOASTR -->
    <script type="text/javascript" src="../lib/angular/angular-toastr.tpls.js"></script>

     <!-- aside -->
    <script src="../js/outros/aside.js" type="text/javascript"></script>



</body>

</html>
