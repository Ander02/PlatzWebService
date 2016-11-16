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
        <script src="../js/controller/loginController.js" type="text/javascript"></script>

        <script src="../js/controller/usuarioController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

    </head>

    <body>
        <!-- inicio do projeto aqui-->
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
    <ng-include ng-controller="loginController" src="'../View/nav-usuario.html'"></ng-include>
    <div class="espaco"></div>
    <div ng-controller="usuarioController">

        <div class="head-pagina">
            <h1>Eventos</h1>
            <p>
                pagina dedica aos eventos que foram curtidos
            </p>
        </div>

        <!--Carousel Wrapper-->
        <div class="col-md-1"></div>
        <div id="multi-item-example" class="carousel slide carousel-multi-item col-md-10" data-ride="carousel">

            <!--Controls-->
            <div class="controls-top" ng-if="eventosCurtidos.length > 1">
                <a class="btn-floating btn-lg orange" href="#multi-item-example" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
                <a class="btn-floating btn-lg orange" href="#multi-item-example" data-slide="next"><i class="fa fa-chevron-right"></i></a>
            </div>
            <!--/.Controls-->


            <!--Slides-->
            <div class="carousel-inner" role="listbox" ng-if="permicao">

                <!--First slide-->
                <div class="carousel-item {{$index==0?'active':''}}" ng-repeat="evento3 in eventosCurtidos">
                    <div class="col-md-4 " ng-repeat="evento in evento3 track by $index">
                        <div class="card" ng-if="evento != null">                       
                            <div class="hovereffect">
                                <img class="img-fluid imagem-evento" ng-src="{{buscarImagemCapa(evento.id)}}" onerror='this.src = "../img/outras/plano-fundo.jpg"' alt="">
                                <div class="{{evento.cancelado === null?'ev-normais':'ev-cancelado'}}">
                                    <p>{{evento.cancelado === null? evento.nome.length < 20? evento.nome:evento.nome :'Cancelado'}}</p>
                                </div>
                                <div class="overlay">
                                    <h2 ng-bind="evento.nome">Nome do Evento</h2>
                                    <p>
                                        <a href="/eventoEspecifico.jsp?evento={{evento.id}}" data-toggle="tooltip" data-placement="bottom" title="Ver Detalhes">
                                            <i class="fa fa-eye"></i>
                                        </a>
                                        <a href="#" data-toggle="tooltip" data-placement="bottom" title="Descurtir" ng-click="descurtir(evento.id)">
                                            <i class="fa fa-thumbs-o-down"></i>
                                        </a>
                                    </p>
                                </div>

                            </div>                        
                            <div class="card-block info-evento-curtido">                          
                                <h4 class="card-title"><a>{{evento.nome}} <i class="fa fa-angle-right"></i></a></h4>
                                <p class="card-text">
                                    <a> <i class="fa fa-building-o"></i> {{evento.empresa.nomeFantasia}}</a>
                                <p><strong><i class="fa fa-clock-o"></i> {{evento.dataInicio}}</strong></p>                                
                                <a href="/eventoEspecifico.jsp?evento={{evento.id}}" class="btn btn-default-outline" >Ver Mais Detalhes</a>
                            </div>
                        </div>
                    </div> <!-- coluna com o primeiro card -->
                </div> <!--/.First slide-->           
            </div><!--/.Slides-->      
        </div><!--/.Carousel Wrapper-->
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

