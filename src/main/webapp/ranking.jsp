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

        <!-- angular app script -->
        <script type="text/javascript" src="js/app.js"></script>

        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/services/loginService.js" type="text/javascript"></script>

        <script src="js/controller/loginController.js" type="text/javascript"></script>

        <script src="js/controller/sobreController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">


    </head>

    <body >
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
        <div ng-controller="sobreController">
            <div class="espaco"></div>
            
            
            <!--Section: Magazine v.1-->
<section class="section magazine-section  col-lg-10 section-ranking">
    
    <div class="head-pagina">
    <!--Section heading-->
    <h1 class="section-heading">Ranking dos eventos do mês</h1>
    </div>
    <!--Section description-->
    <p class="section-description">Esta pagina é direcionada aos ranking dos eventos neste mês,
        onde os eventos que tem o maior numero de participações ganha o primeiro lugar,
    independente do dia do mês que for realizado</p>

    <!--First row-->
    <div class="row text-xs-left">
        
        <div class="col-lg-12 col-md-12">
            <div class="single-news card">

                <!--Image-->
                <div class="view overlay hm-white-slight ev-primeiro-lugar">
                    <img src="img/outras/plano-fundo.jpg">
                    <a>
                        <div class="mask"></div>
                    </a>
                </div>

                <!--Excerpt-->
                <div class="news-data info-ev-ranking">
                    <h2><strong> <i class="fa fa-trophy fa-2x" style="color:#FFD700;"></i> Primeiro Lugar </strong></h2>
                    <div class="col-md-6">
                    <h4><i class="fa fa-calendar-check-o"></i> Nome do evento</h4>
                    <p><i class="fa fa-building-o"></i> Nome da Empresa</p>
                    </div>
                    <div class="col-md-6">
                          <p><i class="fa fa-calendar"></i> Data do evento</p>
                    <p><i class="fa fa fa-thumbs-o-up"></i> Numero de eu vou</p>
                    <p><i class="fa fa-star"></i> Numero de estrelas</p>
                    </div> 
                </div>
            </div>
        </div>

        <!--First column-->
        <div class="col-lg-6 col-md-12">

            <!--Featured news-->
            <div class="single-news card ">

                <!--Image-->
                <div class="view overlay hm-white-slight ev-segundo-lugar">
                    <img src="img/outras/plano-fundo-login.jpg">
                    <a>
                        <div class="mask"></div>
                    </a>
                </div>

                <!--Excerpt-->
                <div class="news-data info-ev-ranking">
                    <h5><i class="fa fa-trophy" style="color: silver;"></i> Segundo Lugar</h5
                    <p><strong><i class="fa fa-clock-o"></i> 20/08/2016</strong></p>
                </div>

            </div>
            <!--/Featured news-->
        </div>
        <!--/First column-->

        <!--Second column-->
        <div class="col-lg-6 col-md-12">

            <!--Featured news-->
            <div class="single-news card">

                <!--Image-->
                <div class="view overlay hm-white-slight ev-terceiro-lugar">
                    <img src="img/outras/plano-fundo-login.jpg">
                    <a>
                        <div class="mask"></div>
                    </a>
                </div>

                <!--Excerpt-->
                <div class="news-data info-ev-ranking">
                    <h5><i class="fa fa-trophy" style="color: #EE7600;" ></i> Terceiro Lugar</h5>
                    <p><strong><i class="fa fa-clock-o"></i> 24/08/2016</strong></p>
                </div>
            </div>
            <!--/Featured news-->
        </div>
        <!--/Second column-->
        
        
        <!-- Eventos Pequenos -->
        <div class="col-lg-12">


                    <div class="col-md-4">

                        <!--Image-->
                        <div class="view overlay hm-white-slight">
                            <img src="img/outras/plano-fundo-login.jpg">
                            <a>
                                <div class="mask"></div>
                            </a>
                        </div>
                        
                        
                    <!--Excerpt-->
                    <div class="col-md-9">
                        <p><strong>19/08/2016</strong></p>
                        <a>A journey through the woods.
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </div>
                        
                    </div>
            
              <div class="col-md-4">

                        <!--Image-->
                        <div class="view overlay hm-white-slight">
                            <img src="img/outras/plano-fundo-login.jpg">
                            <a>
                                <div class="mask"></div>
                            </a>
                        </div>
                        
                        
                    <!--Excerpt-->
                    <div class="col-md-9">
                        <p><strong>19/08/2016</strong></p>
                        <a>A journey through the woods.
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </div>
                        
                    </div>
            
              <div class="col-md-4">

                        <!--Image-->
                        <div class="view overlay hm-white-slight">
                            <img src="img/outras/plano-fundo-login.jpg">
                            <a>
                                <div class="mask"></div>
                            </a>
                        </div>
                        
                        
                    <!--Excerpt-->
                    <div class="col-md-9">
                        <p><strong>19/08/2016</strong></p>
                        <a>A journey through the woods.
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </div>
                        
                    </div>



           
        </div>

    </div>
    <!--/First row-->

</section>
<!--/Section: Magazine v.1-->

           

            <ng-include src="'View/footer.html'"></ng-include>
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


            <!-- esconder formularios -->
            <script type="text/javascript" src="js/outros/esconder-form-cad.js"></script>
            <!-- link Angular -->
            <script src="lib/angular/angular-animate.js" type="text/javascript"></script>
            <!-- link Angular -->
            <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>
            <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>
        </div>


    </body>

</html>



