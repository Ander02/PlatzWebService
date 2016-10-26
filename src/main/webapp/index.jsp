<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">

        <!-- script com o arquivo do mapa -->
        <script type="text/javascript"
        src="http://maps.google.com/maps/api/js?key=AIzaSyBdWHheIGYeuOEDTS4iYFI1lbIbq7-W7Hw"></script>
        <script src="js/outros/mapa-index.js"></script>
        <!-- angular app script -->
        <script type="text/javascript" src="js/app.js"></script>

        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/controller/loginController.js" type="text/javascript"></script>

        <script src="js/controller/eventosController.js" type="text/javascript"></script>
    </head>

    <body onload="initialize()" class="hidden-sn blue-skin">
        
        
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


        <!-- inicio do projeto aqui-->

    <ng-include src="'View/nav.html'"  ng-controller="loginController"></ng-include>
    <div ng-controller="eventosController">
        <div class="slider-index">
            <!--Carousel Wrapper-->
            <div id="carousel-example-2" class="carousel slide carousel-fade" data-ride="carousel">

                <!--Slides-->
                <div class="carousel-inner" role="listbox">
                    <!--First slide-->
                    <div class="carousel-item {{$index==0?'active':''}}" ng-repeat="evento3 in top3Eventos">
                        <!--Mask color-->
                        <div class="view hm-black-light">
                            <img src="http://mdbootstrap.com/images/slides/slide%20(11).jpg" class="img-fluid" alt="">
                            <div class="full-bg-img">
                            </div>
                        </div>
                        <!--Caption-->
                        <div class="carousel-caption">
                            <div class="animated fadeInDown">
                                <a class="info-color" href="eventoEspecifico.jsp?evento={{evento3.id}}">
                                    <h3 class="h3-responsive" ng-bind="evento3.nome"></h3>
                                </a>
                                <p ng-bind="evento3.detalhes"></p>
                            </div>
                        </div>
                        <!--Caption-->
                    </div>
                    <!--/First slide-->

                </div>
                <!--/.Slides-->

                <!--Controls-->
                <a class="left carousel-control" href="#carousel-example-2" role="button" data-slide="prev">
                    <span class="icon-prev" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-2" role="button" data-slide="next">
                    <span class="icon-next" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
                <!--/.Controls-->
            </div>
            <!--/.Carousel Wrapper-->


        </div>
        <!-- fim do slider -->



        <!--Carousel Wrapper-->
        <div class="col-md-10 carousel-index-destaques">
            <h1>Eventos em destaque</h1>
            <div id="multi-item-example" class="carousel slide carousel-multi-item" data-ride="carousel">

                <!--Controls-->
                <div class="controls-top">
                    <a class="btn-floating btn-small btn-warning btn-setas" href="#multi-item-example" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
                    <a class="btn-floating btn-small btn-warning btn-setas" href="#multi-item-example" data-slide="next"><i class="fa fa-chevron-right"></i></a>
                </div>
                <!--/.Controls-->


                <!--Slides-->

                <div class="carousel-inner" role="listbox" >

                    <!--First slide-->
                    <div class="carousel-item {{$index==0?'active':''}}" ng-repeat="eventosMiniSlide in top15Eventos">

                        <div class="col-md-4" ng-repeat="evento in eventosMiniSlide track by $index">
                            <div class="card" ng-if="evento != null">
                                <img class="img-fluid" src="http://mdbootstrap.com/images/regular/nature/img%20(1).jpg" alt="Card image cap">
                                <div class="card-block">
                                    <h4 class="card-title">{{evento.nome}}</h4>
                                    <h5><i class="fa fa-building-o animated bounceInDown"></i> {{evento.empresa.nomeFantasia}}</h5>
                                    <p><i class="fa fa-calendar animated bounceInDown"></i> {{evento.dataInicio}} </p>
                                    <p><i class="fa fa-map-marker animated bounceInDown"></i>{{evento.endereco.cidade.nome}} - {{evento.endereco.cidade.estado.uf}}</p>
                                    <p><a class="btn btn-warning " href="eventoEspecifico.jsp?evento={{evento.id}}" role="button">Ver Mais Detalhes &raquo;</a></p>
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

        <!-- mapa com os eventos -->
        <div class="mapa">
            <h1>Veja os eventos perto de você</h1>
            <div id="map_canvas" ></div>
        </div>
        <!-- /. mapa -->

        <ng-include src="'View/footer.html'"></ng-include>
        <ng-include src="'View/footer-index.html'"></ng-include>




        <!-- /Start your project here-->


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
        <!-- link Angular -->
        <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>
    </div>

</body>

</html>