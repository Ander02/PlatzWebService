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

        <script src="js/controller/eventosCategoriaController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">

    </head>

    <body>


        <!-- inicio do projeto aqui-->
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

            String idCategoria = request.getParameter("categoria");

            out.print("<input type='hidden' id='idCategoria' name='idCategoria' value ='" + idCategoria + "' >");

        %>

        <div ng-controller="loginController">
            <ng-include ng-if="conta == null" src="'View/nav.html'" ></ng-include>
            <ng-include ng-if="conta.perfil === 'Administrador'" src="'View/nav-adm.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Empresa'" src="'View/nav-empresa.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Usuario'" src="'View/nav-usuario.html'"  ></ng-include>
        </div>
        <div ng-controller="eventosCategoriaController">
            <div class="espaco"></div>


            <!-- Section: Retornando eventos -->
            <section class="section sectionCategoria col-md-10">

                <!--Section heading-->
                <h1 class="section-heading" ng-bind="categoria.nome"></h1>

                <!--First column-->
                <div class="col-lg-4 col-md-6 m-b-r"  ng-repeat="evento in eventos">
                    <!--Card-->
                    <div class="card card-cascade narrower">
                        <!--Card image-->
                        <div class="view overlay hm-white-slight">
                            <img ng-src="{{buscarImagemCapa(evento.id)}}" onerror="this.src='img/placeholder.png'" class="img-fluid" alt="">
                            <a>
                                <div class="mask"></div>
                            </a>
                        </div>
                        <div class="ev-cancelado">
                            <p>Cancelado</p>
                        </div>
                        <!--/.Card image-->

                        <!--Card content-->
                        <div class="card-block text-xs-center">
                            <!--Categoria & nome da empresa e do evento-->                  
                            <h4 class="card-title"><strong ng-bind="evento.nome"></strong></h4> 
                            <h4 class="card-title"><i class="fa fa-building-o"></i><strong ><a href="perfilEmpresa.jsp?empresa={{evento.empresa.id}}" ng-bind="evento.empresa.nomeFantasia"> </a></strong></h4> 
                            <!--Description-->
                            <p class="card-text" ><i class="fa fa-calendar"></i>{{evento.dataInicio}}</p>
                            <p class="card-text"><i class="fa fa-map-marker"></i> {{evento.endereco.cidade.nome}} - {{evento.endereco.cidade.estado.uf}}</p>
                            <span class="right">
                                <a class="btn btn-amber btn-lg" href="eventoEspecifico.jsp?evento={{evento.id}}">Ver Mais >></a>                   
                            </span>

                        </div><!--/.Card content-->               
                    </div><!--/.Card-->         
                </div><!--/First column-->
            </section><!--/Section: Products v.1-->


        </section><!--/Section: Products v.1-->

        <!-- se não tiver evento-->
        <div class="col-md-10" id="section-sem-evento" ng-if="eventos.length == 0">
            <div class="jumbotron animated fadeInUp">
                <h1> Desculpe esta categoria não possui evento</h1>
                <p>Porem você pode navegar e descobrir otimos eventos em outra categoria </p>
                <p><a class="btn btn-amber" href="eventos.jsp">Voltar</a></p>
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

        <!-- link Angular Animate -->
        <script src="lib/angular/angular-animate.js" type="text/javascript"></script>

        <!-- link Angular css Toast-->
        <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>

        <!-- link Angular Toast-->
        <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>

        <link href="css/bootstrap/isteven-multi-select.css" rel="stylesheet" type="text/css"/>

        <script src="lib/angular/isteven-multi-select.js" type="text/javascript"></script>

        <script src="js/outros/aside.js" type="text/javascript"></script>


        <!-- FIM SCRIPTS -->
    </div>
</body>

</html>
