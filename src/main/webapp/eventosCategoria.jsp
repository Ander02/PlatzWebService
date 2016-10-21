<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
pagina onde é retornada a busca de eventos por uma unica determinada categoria
-->
<html lang="pt-br" ng-app="platz">
    <head>

        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- meta para compatibilidade no IE -->
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

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">

    </head>

    <body>


        <!-- inicio do projeto aqui-->

    <ng-include src="'View/nav.html'"></ng-include>


    <div class="espaco"></div>

    <!-- Section: Retornando eventos -->
    <section class="section sectionCategoria col-md-10">

        <!--Section heading-->
        <h1 class="section-heading">Nome da Categoria</h1>


        <!--First column-->
        <div class="col-lg-4 col-md-6 m-b-r">
            <!--Card-->
            <div class="card card-cascade narrower">
                <!--Card image-->
                <div class="view overlay hm-white-slight">
                    <img src="img/outras/plano-fundo.jpg" class="img-fluid" alt="">
                    <a>
                        <div class="mask"></div>
                    </a>
                </div>
                <!--/.Card image-->

                <!--Card content-->
                <div class="card-block text-xs-center">
                    <!--Categoria & nome da empresa e do evento-->                  
                    <h4 class="card-title"><strong>Nome Do evento</strong></h4> 
                    <h4 class="card-title"><i class="fa fa-building-o"></i><strong><a href=""> Nome Da Empresa</a></strong></h4> 


                    <!--Description-->
                    <p class="card-text"><i class="fa fa-calendar"></i> Data e horario</p>
                    <p class="card-text"><i class="fa fa-map-marker"></i> Local</p>

                    <!--Card footer-->
                    <div class="card-footer">

                        <span class="right">
                            <a class="btn btn-amber btn-lg" href="">Ver Mais >></a>                   
                        </span>
                    </div>

                </div><!--/.Card content-->               
            </div><!--/.Card-->         
        </div><!--/First column--> 
        <div class="col-lg-4 col-md-6 m-b-r">
            <!--Card-->
            <div class="card card-cascade narrower">
                <!--Card image-->
                <div class="view overlay hm-white-slight">
                    <img src="img/outras/plano-fundo.jpg" class="img-fluid" alt="">
                    <a>
                        <div class="mask"></div>
                    </a>
                </div>
                <!--/.Card image-->

                <!--Card content-->
                <div class="card-block text-xs-center">
                    <!--Categoria & nome da empresa e do evento-->                  
                    <h4 class="card-title"><strong>Nome Do evento</strong></h4> 
                    <h4 class="card-title"><i class="fa fa-building-o"></i><strong><a href=""> Nome Da Empresa</a></strong></h4> 


                    <!--Description-->
                    <p class="card-text"><i class="fa fa-calendar"></i> Data e horario</p>
                    <p class="card-text"><i class="fa fa-map-marker"></i> Local</p>

                    <!--Card footer-->
                    <div class="card-footer">

                        <span class="right">
                            <a class="btn btn-amber btn-lg" href="">Ver Mais >></a>                   
                        </span>
                    </div>

                </div><!--/.Card content-->               
            </div><!--/.Card-->         
        </div><!--/First column-->

        <div class="col-lg-4 col-md-6 m-b-r">
            <!--Card-->
            <div class="card card-cascade narrower">
                <!--Card image-->
                <div class="view overlay hm-white-slight">
                    <img src="img/outras/plano-fundo.jpg" class="img-fluid" alt="">
                    <a>
                        <div class="mask"></div>
                    </a>
                </div>
                <!--/.Card image-->

                <!--Card content-->
                <div class="card-block text-xs-center">
                    <!--Categoria & nome da empresa e do evento-->                  
                    <h4 class="card-title"><strong>Nome Do evento</strong></h4> 
                    <h4 class="card-title"><i class="fa fa-building-o"></i><strong><a href=""> Nome Da Empresa</a></strong></h4> 


                    <!--Description-->
                    <p class="card-text"><i class="fa fa-calendar"></i> Data e horario</p>
                    <p class="card-text"><i class="fa fa-map-marker"></i> Local</p>

                    <!--Card footer-->
                    <div class="card-footer">

                        <span class="right">
                            <a class="btn btn-amber btn-lg" href="">Ver Mais >></a>                   
                        </span>
                    </div>

                </div><!--/.Card content-->               
            </div><!--/.Card-->         
        </div><!--/First column-->

        <div class="col-lg-4 col-md-6 m-b-r">
            <!--Card-->
            <div class="card card-cascade narrower">
                <!--Card image-->
                <div class="view overlay hm-white-slight">
                    <img src="img/outras/plano-fundo.jpg" class="img-fluid" alt="">
                    <a>
                        <div class="mask"></div>
                    </a>
                </div>
                <!--/.Card image-->

                <!--Card content-->
                <div class="card-block text-xs-center">
                    <!--Categoria & nome da empresa e do evento-->                  
                    <h4 class="card-title"><strong>Nome Do evento</strong></h4> 
                    <h4 class="card-title"><i class="fa fa-building-o"></i><strong><a href=""> Nome Da Empresa</a></strong></h4> 


                    <!--Description-->
                    <p class="card-text"><i class="fa fa-calendar"></i> Data e horario</p>
                    <p class="card-text"><i class="fa fa-map-marker"></i> Local</p>

                    <!--Card footer-->
                    <div class="card-footer">

                        <span class="right">
                            <a class="btn btn-amber btn-lg" href="">Ver Mais >></a>                   
                        </span>
                    </div>

                </div><!--/.Card content-->               
            </div><!--/.Card-->         
        </div><!--/First column-->

        <div class="col-lg-4 col-md-6 m-b-r">
            <!--Card-->
            <div class="card card-cascade narrower">
                <!--Card image-->
                <div class="view overlay hm-white-slight">
                    <img src="img/outras/plano-fundo.jpg" class="img-fluid" alt="">
                    <a>
                        <div class="mask"></div>
                    </a>
                </div>
                <!--/.Card image-->

                <!--Card content-->
                <div class="card-block text-xs-center">
                    <!--Categoria & nome da empresa e do evento-->                  
                    <h4 class="card-title"><strong>Nome Do evento</strong></h4> 
                    <h4 class="card-title"><i class="fa fa-building-o"></i><strong><a href=""> Nome Da Empresa</a></strong></h4> 


                    <!--Description-->
                    <p class="card-text"><i class="fa fa-calendar"></i> Data e horario</p>
                    <p class="card-text"><i class="fa fa-map-marker"></i> Local</p>

                    <!--Card footer-->
                    <div class="card-footer">

                        <span class="right">
                            <a class="btn btn-amber btn-lg" href="">Ver Mais >></a>                   
                        </span>
                    </div>

                </div><!--/.Card content-->               
            </div><!--/.Card-->         
        </div><!--/First column-->


    </section><!--/Section: Products v.1-->

    <!-- se não tiver evento-->
    <div class="col-md-10" id="section-sem-evento" >
        <div class="jumbotron animated fadeInUp">
            <h1> Desculpe esta categoria não possui evento</h1>
            <p>Porem você pode navegar e descobrir otimos eventos em outra categoria </p>
            <p><a class="btn btn-amber">Voltar</a></p>
        </div>
    </div>



    <ng-include src="'View/footer.html'"></ng-include>
    <!-- /Fim do projeto -->


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

    <!-- link Angular Animate -->
    <script src="lib/angular/angular-animate.js" type="text/javascript"></script>

    <!-- link Angular css Toast-->
    <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>

    <!-- link Angular Toast-->
    <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>

    <!-- FIM SCRIPTS -->

</body>

</html>
