<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">

        <!-- script com o arquivo do mapa -->
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyBdWHheIGYeuOEDTS4iYFI1lbIbq7-W7Hw"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="js/app.js"></script>

        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/services/loginService.js" type="text/javascript"></script>
        
        <script src="js/services/validacaoService.js" type="text/javascript"></script>

        <script src="js/controller/loginController.js" type="text/javascript"></script>
        
        <script src="lib/angular/ng-map.min.js" type="text/javascript"></script>

        <script src="js/controller/eventosController.js" type="text/javascript"></script>
    </head>

    <body class="hidden-sn blue-skin">


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
        <div ng-controller="loginController">
            <ng-include ng-if="conta == null" src="'View/nav.html'" ></ng-include>
            <ng-include ng-if="conta.perfil === 'Administrador'" src="'View/nav-adm.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Empresa'" src="'View/nav-empresa.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Usuario'" src="'View/nav-usuario.html'"  ></ng-include>
        </div>

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
                                <img ng-src="{{buscarImagemCapa(evento3.id)}}" onerror="this.src='img/placeholder.png'" class="img-fluid" alt="">
                            </div>
                            <!--Caption-->
                            <div class="carousel-caption">
                                <div class="animated fadeInDown">
                                    <a class="info-color-dark" href="eventoEspecifico.jsp?evento={{evento3.id}}">
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
                    <div class="controls-top controles-slide-mini" ng-if="top15Eventos.length > 1">
                        <a class="btn-floating btn-small btn-warning btn-setas" href="#multi-item-example" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
                        <a class="btn-floating btn-small btn-warning btn-setas" href="#multi-item-example" data-slide="next"><i class="fa fa-chevron-right"></i></a>
                    </div>
                    <!--/.Controls-->


                    <!--Slides-->

                    <div class="carousel-inner" role="listbox" >

                        <!--First slide-->
                        <div class="carousel-item {{$index==0?'active':''}}" ng-repeat="eventosMiniSlide in top15Eventos">

                            <div class="col-md-4" ng-repeat="evento in eventosMiniSlide track by $index">
                                <div class="card img-mini-slide" ng-if="evento != null">
                                    <img class="img-fluid " ng-src="{{buscarImagemCapa(evento.id)}}" onerror="this.src='img/logo.png'" alt="Card image cap">                                    
                                    <div class="card-block">
                                        <h4 class="card-title">{{evento.nome| limitTo:25 }} {{evento.nome.length >= 25 ? '...' : ''}}</h4>
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
                <div class="col-md-12">
                    <h1>Veja os eventos perto de você</h1>
                </div>
                <div id="map_canvas" ></div>
            </div>
            <!-- /. mapa -->



            <!--Section: Features v.4-->
            <section class="section feature-box">

                <!--Section heading-->
                <div class="head-pagina">
                    <h1>Baixe o app Platz!</h1>
                    <br>
                    <hr>
                    <button class="btn btn-amber btn-lg">Baixe aqui</button>
                </div>

                <!--Section sescription-->
                <p class="section-description lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugit
                    , error amet numquam iure provident voluptate esse quasi, veritatis totam voluptas nostrum quisquam eum 
                    porro a pariatur accusamus veniam. Quia, minima?</p>

                <!--First row-->
                <div class="row features-small">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <!--First column-->
                        <div class="col-md-4">
                            <!--First row-->
                            <div class="row">
                                <div class="col-lg-2">
                                    <i class="fa fa-flag-checkered indigo-text fa-3x"></i>
                                </div>
                                <div class="col-xs-10">
                                    <h4 class="feature-title">Nacional</h4>
                                    <p class="grey-text">Com eventos de todo o país, é possivel ficar antenado nos eventos mais badalados da sua cidade .</p>
                                    <div style="height:30px"></div>
                                </div>
                            </div>
                            <!--/First row-->

                            <!--Second row-->
                            <div class="row">
                                <div class="col-xs-2">
                                    <i class="fa fa-flask blue-text fa-3x"></i>
                                </div>
                                <div class="col-lg-10">
                                    <h4 class="feature-title">Atual</h4>
                                    <p class="grey-text">Eventos de todas as categorias possiveis. </p>
                                    <div style="height:30px"></div>
                                </div>
                            </div>
                            <!--/Second row-->

                            <!--Third row-->
                            <div class="row">
                                <div class="col-xs-2">
                                    <i class="fa fa-glass cyan-text fa-3x"></i>
                                </div>
                                <div class="col-xs-10">
                                    <h4 class="feature-title">Clean</h4>
                                    <p class="grey-text">Com design simples, o app é moderno e muito facil de usar.</p>
                                    <div style="height:30px"></div>
                                </div>
                            </div>
                            <!--/Third row-->
                        </div>
                        <!--/First column-->

                        <!--Second column-->
                        <div class="col-md-4 mb-r center-on-small-only">
                            <img src="img/outras/cel2.png" alt="imagem de um celular, contendo nosso aplicativo" class="z-depth-0">
                        </div>
                        <!--/Second column-->

                        <!--Third column-->
                        <div class="col-md-4">
                            <!--First row-->
                            <div class="row">
                                <div class="col-xs-2">
                                    <i class="fa fa-heart red-text fa-3x"></i>
                                </div>
                                <div class="col-md-10">
                                    <h4 class="feature-title">Curta Eventos</h4>
                                    <p class="grey-text">É possivel curtir todos os eventos. </p>
                                    <div style="height:30px"></div>
                                </div>
                            </div>
                            <!--/First row-->

                            <!--Second row-->
                            <div class="row">
                                <div class="col-xs-2">
                                    <i class="fa fa-flash orange-text fa-3x"></i>
                                </div>
                                <div class="col-xs-10">
                                    <h4 class="feature-title">Rapido</h4>
                                    <p class="grey-text">App muito rapido, sem problemas de travamento no seu celular.</p>
                                    <div style="height:30px"></div>
                                </div>
                            </div>
                            <!--/Second row-->

                            <!--Third row-->
                            <div class="row">
                                <div class="col-xs-2">
                                    <i class="fa fa-android lime-text fa-3x"></i>
                                </div>
                                <div class="col-xs-10">
                                    <h4 class="feature-title">Android</h4>
                                    <p class="grey-text">Compativel com versoes do android 1.0, baixe e se divirta .</p>
                                    <div style="height:30px"></div>
                                </div>
                            </div>
                            <!--/Third row-->
                        </div>
                        <!--/Third column-->

                    </div>
                    <!--/First row-->
                </div>

            </section>
            <!--/Section: Features v.4-->



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
            <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>
            <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>
            <script src="js/outros/aside.js" type="text/javascript"></script>
            <script src="js/outros/mapa-index.js"></script>

        </div>

    </body>

</html>
