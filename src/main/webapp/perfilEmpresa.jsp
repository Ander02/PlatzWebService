<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br" ng-app="platz">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <title>Platz - Suas rotas, Seus Eventos</title>

        <!-- Font Awesome -->
        <link href="../css/font/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

        <!-- Material Design Bootstrap -->
        <link href="css/bootstrap/mdb.min.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="css/style.css" rel="stylesheet">

        <!-- Your custom styles (efeito) -->
        <link href="css/efeitos/perfilEmpresa.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="js/app.js"></script>

        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/services/loginService.js" type="text/javascript"></script>

        <script src="js/controller/loginController.js" type="text/javascript"></script>

        <script src="js/services/validacaoService.js" type="text/javascript"></script>

        <script src="js/controller/empresaEspecificaController.js" type="text/javascript"></script>        


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
        %>

        <div ng-controller="loginController">
            <ng-include ng-if="conta == null" src="'View/nav.html'" ></ng-include>
            <ng-include ng-if="conta.perfil === 'Administrador'" src="'View/nav-adm.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Empresa'" src="'View/nav-empresa.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Usuario'" src="'View/nav-usuario.html'"  ></ng-include>
        </div>        <%
            String empresa = request.getParameter("empresa");

            out.print("<input type='hidden' id='idEmpresa' name='idEmpresa' value ='" + empresa + "' >");
        %>


        <div ng-controller="empresaEspecificaController">
            <div class="espaco"></div>

            <section class="section section-blog-fw section-perfil-empresa">

                <!--First row-->
                <div class="row">
                    <!--Post data-->
                    <div class="jumbotron caixa-informacao-perfil-empresa">
                        <div class="col-lg-12 col-md-12 m-b-r">

                            <!--Card-->
                            <div class="card">
                                <div class="col-md-12">
                                    <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 imagem-perfil-empresa-efeito">
                                        <div class="hovereffect">
                                            <img class="img-responsive" ng-src="{{imagemPerfil}}" onerror="src='img/outras/plano-fundo.jpg'" alt="">
                                            <div class="overlay">
                                                <h2 ng-bind="empresa.nomeFantasia"></h2>
                                                <a class="info" href="#">Curta Nossos Eventos</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-block">

                                    <!--Name-->
                                    <h4 class="card-title" ng-bind="empresa.razaoSocial"></h4>                              
                                    <hr>
                                    <!--Quotation-->
                                    <p>{{empresa.endereco.rua}} - {{empresa.endereco.numero}} </p>
                                    <p>{{empresa.endereco.cidade.nome}} - {{empresa.endereco.cidade.estado.uf}} </p>
                                </div>

                            </div>
                            <!--/.Card-->
                        </div>
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs md-pills pills-ins" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#panel11" role="tab"><i class=" fa fa-th-large"></i> Eventos</a>
                            </li>


                        </ul>

                        <!-- Tab panels -->
                        <div class="tab-content">

                            <!--Panel 1-->
                            <div class="tab-pane fade in active" id="panel11" role="tabpanel">
                                <br>                            

                                <div class="col-lg-4 col-md-6 m-b-r"  ng-repeat="evento in eventos">
                                    <!--Card-->
                                    <div class="card card-cascade narrower">
                                        <!--Card image-->
                                        <div class="view overlay hm-white-slight">
                                            <img ng-src="{{buscarImagemCapa(evento.id)}}" onerror="src='img/outras/plano-fundo.jpg'" class="img-fluid" alt="">
                                            <a>
                                                <div class="mask"></div>
                                            </a>
                                        </div>
                                        <!--/.Card image-->

                                        <!--Card content-->
                                        <div class="card-block text-xs-center">
                                            <!--Categoria & nome da empresa e do evento-->                  
                                            <h4 class="card-title"><strong>{{evento.nome|limitTo:20}}{{evento.nome.length>=20?'...':''}}</strong></h4> 

                                            <!--Description-->
                                            <p class="card-text" ><i class="fa fa-calendar"></i>{{evento.dataInicio}}</p>
                                            <p class="card-text"><i class="fa fa-map-marker"></i> {{evento.endereco.cidade.nome}} - {{evento.endereco.cidade.estado.uf}}</p>

                                            <!--Card footer-->
                                            <div class="card-footer">

                                                <span class="right">
                                                    <a class="btn btn-amber btn-lg" href="eventoEspecifico.jsp?evento={{evento.id}}">Ver Mais >></a>                   
                                                </span>
                                            </div>

                                        </div><!--/.Card content-->               
                                    </div><!--/.Card-->         
                                </div><!--/First column--> 

                            </div>

                        </div>

                    </div>
                    <!--/.Panel 1-->                  

                </div>
                <!--/Post data-->
            </section>
        </div>
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
    <!-- link Angular -->
    <script src="lib/angular/angular-animate.js" type="text/javascript"></script>
    <!-- link Angular -->
    <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>
    <script src="js/outros/aside.js" type="text/javascript"></script>



</body>

</html>