<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Ira aparecer todos os eventos que o usuario marcou participação
-->
<html lang="pt-br" ng-app="platz">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <title>Platz - Suas rotas, Seus Eventos</title>

        <!-- Font Awesome -->
        <link href="../css/font/font-awesome.min.css" rel="stylesheet" type="text/css"/>

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

        <!-- service -->
        <script src="../js/services/loginService.js" type="text/javascript"></script>

        <!-- angular controller  -->

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

    </head>

    <body>

    <ng-include src="'../View/nav-usuario.html'"></ng-include>
    <div class="espaco"></div>
    <div >

        <div class="head-pagina">
            <h1>Eventos</h1>
            <p>
                pagina dedica aos eventos que tiveram sua participação marcada, tanto com eu vou , talvez ou não vou
            </p>
        </div>

       
        <div class="col-md-1"></div>
      <!--Carousel Wrapper-->
            <div class="col-md-10">

                
                <div id="multi-item-example" class="carousel slide carousel-multi-item" data-ride="carousel">

                    <!--Controls-->
                    <div class="controls-top controles-slide-mini" >
                        <a class="btn-floating btn-small btn-warning btn-setas" href="#multi-item-example" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
                        <a class="btn-floating btn-small btn-warning btn-setas" href="#multi-item-example" data-slide="next"><i class="fa fa-chevron-right"></i></a>
                    </div>
                    <!--/.Controls-->


                    <!--Slides-->

                    <div class="carousel-inner" role="listbox" >

                        <!--First slide-->
                        <div class="carousel-item ">

                            <div class="col-md-4">
                                <div class="card img-mini-slide" >
                                    <img class="img-fluid "  onerror="this.src='img/logo.png'" alt="Card image cap">
                                    <div class="card-block">
                                        <h4 class="card-title"></h4>
                                        <h5><i class="fa fa-building-o animated bounceInDown"></i> </h5>
                                        <p><i class="fa fa-calendar animated bounceInDown"></i>  </p>
                                        <p><i class="fa fa-map-marker animated bounceInDown"></i></p>
                                        <p><a class="btn btn-warning " href="" role="button">Ver Mais Detalhes &raquo;</a></p>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!--/.First slide-->

                    </div>
                    <!--/.Slides-->

                </div>
            </div>
            <!--/.Carousel Wrapper-->
    </div>
    <div class="espaco"></div>
    <ng-include src="'../View/footer.html'"></ng-include>
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

    <!-- aside -->
    <script src="../js/outros/aside.js" type="text/javascript"></script>


</body>

</html>

