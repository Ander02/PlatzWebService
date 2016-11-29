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
        <link href="../css/font/font-awesome.min.css" rel="stylesheet" type="text/css"/>

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

        <!-- service -->
        <script src="../js/services/loginService.js" type="text/javascript"></script>

        <script src="../js/services/validacaoService.js" type="text/javascript"></script>

        <script src="../js/controller/usuarioController.js" type="text/javascript"></script>

        <!-- angular controller  -->
        <script src="../js/controller/loginController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">



    </head>

    <body>
        <%
            try {
                String token = session.getAttribute("token").toString();
                if (token == null) {
                    response.sendRedirect("/login.jsp");
                } else {
                    out.print("<input type='hidden' id='token' name='token' value ='" + token + "' >");
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar sessão " + e.getMessage());
                response.sendRedirect("/login.jsp");
            }
        %>

        <!-- inicio do projeto aqui-->

    <ng-include ng-controller="loginController" src="'../View/nav-usuario.html'"></ng-include>


    <div class="espaco"></div>

    <div class="head-pagina">
        <h1 >Eventos Por participação </h1>
        <p>Eventos que você marcou algum tipo de participação, sua diversão é nossa prioridade</p>
    </div>


    <!--Section: COM OS EVENTOS-->
    <div ng-controller="usuarioController">
        <section class="section magazine-section section-eventos-proximos">


            <!-- Nav tabs -->
            <ul class="nav nav-tabs md-pills pills-ins" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#panel11" role="tab"><i class="fa fa-smile-o fa-2x"></i> Vou </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel12" role="tab"><i class="fa fa-meh-o fa-2x"></i> Talvez </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel13" role="tab"><i class="fa fa-frown-o fa-2x"></i> Não vou</a>
                </li>
            </ul>

            <!-- Tab panels -->
            <div class="tab-content">

                <!--Panel 1-->
                <div class="tab-pane fade in active" id="panel11" role="tabpanel">
                    <br>
                    <div class="col-lg-4 col-md-12">

                        <!--Evento maior-->
                        <div class="single-news">

                            <div>
                                <div class="hovereffect">
                                    <img class="img-responsive img-eventoProximo" src="../img/outras/plano-fundo.jpg" alt="">
                                    <div class="ev-cancelado">
                                        <p>Cancelado</p>
                                    </div>
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

                </div>
                <!--/.Panel 1-->

                <!--Panel 2-->
                <div class="tab-pane fade" id="panel12" role="tabpanel">
                    <br>
                    <div class="col-lg-4 col-md-12">

                        <!--Evento maior-->
                        <div class="single-news">

                            <div>
                                <div class="hovereffect">
                                    <img class="img-responsive img-eventoProximo" src="../img/outras/plano-fundo.jpg" alt="">
                                    <div class="ev-cancelado">
                                        <p>Cancelado</p>
                                    </div>
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

                </div>
                <!--/.Panel 2-->

                <!--Panel 3-->
                <div class="tab-pane fade" id="panel13" role="tabpanel">
                    <br>
                    <div class="col-lg-4 col-md-12">

                        <!--Evento maior-->
                        <div class="single-news">

                            <div>
                                <div class="hovereffect">
                                    <img class="img-responsive img-eventoProximo" src="../img/outras/plano-fundo.jpg" alt="">
                                    <div class="ev-cancelado">
                                        <p>Cancelado</p>
                                    </div>
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

                </div>
                <!--/.Panel 3-->


            </div>





        </section><!--/Section: eventos-->
    </div>
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
