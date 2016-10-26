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
        <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

        <!-- Material Design Bootstrap -->
        <link href="../css/bootstrap/mdb.min.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="../css/style.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="../css/styleEmpresa.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- link app -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- link util -->
        <script src="../js/util.js" type="text/javascript"></script>

        <!-- Link Controller -->
        <script src="../js/controller/perfilEmpresaController.js" type="text/javascript"></script>
        
        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

        <!-- Your custom styles (efeito) -->
        <link href="../css/efeitos/perfilEmpresa.css" rel="stylesheet">

    </head>

    <body ng-controller="perfilEmpresaController">
        <!-- inicio do projeto aqui-->
    <ng-include src="'../View/nav-empresa.html'"></ng-include>

    <%
        try {
            String token = session.getAttribute("token").toString();
            if (token == null) {
                response.sendRedirect("../login.jsp");
            } else {
                out.print("<input type='hidden' id='token' name='token' value ='" + token + "' >");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar sessão " + e.getMessage());
            response.sendRedirect("../login.jsp");
        }
    %>

    <div ng-if="permicao">
        <div class="espaco"></div>

        <section class="section section-blog-fw">
            <!--First row-->
            <div class="row">
                <!--Post data-->
                <div class="jumbotron caixa-informacao-perfil-empresa">
                    <div class="col-lg-12 col-md-12 m-b-r">

                        <!--Card-->
                        <div class="card">
                            <div class="col-md-12">
                                <div class="col-lg-6 col-md-4 col-sm-6 col-xs-12 imagem-perfil-empresa-efeito imagem-perfil-empresa">
                                    <div class="hovereffect">
                                        <img class="img-responsive " src="../img/outras/plano-fundo.jpg" alt="">
                                        <div class="overlay">
                                            <h2>{{empresa.nomeFantasia}}</h2>
                                            <a class="info" href="editarPerfil.jsp">Editar Perfil</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-block">
                                <!--Name-->
                                <h4 class="card-title">{{empresa.razaoSocial}}</h4>                              
                                <hr>
                                <!--Quotation-->
                                <p>{{empresa.endereco.bairro}} {{empresa.endereco.cidade.nome}} - {{empresa.endereco.cidade.estado.uf}}</p>
                            </div>
                        </div> <!--/.Card-->                       
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
                            <div class="card-group col-md-12">
                                <div class="col-md-4 evento-perfil-empresa" ng-repeat="evento in eventos">
                                    <div class="card">
                                        <div class="hovereffect">
                                            <img class=" img-responsive " src="../img/outras/plano-fundo.jpg" alt="Imagem do Evento" >
                                        </div><!-- /. div hovereffect  -->
                                        <h4 class="card-title">{{evento.nome}}</h4>
                                        <p><i class="fa fa-calendar animated bounceInDown"></i> {{evento.dataInicio}}</p>
                                        <p><i class="fa fa-map-marker animated bounceInDown"></i> {{evento.endereco.bairro}}, {{evento.endereco.cidade.nome}} - {{evento.endereco.cidade.estado.uf}} </p>
                                        <p><a class="btn btn-warning " href="../eventoEspecifico.jsp?evento={{evento.id}}" role="button">Ver Mais Detalhes &raquo;</a></p>
                                    </div>
                                </div>
                            </div><!-- /.card group -->
                        </div><!-- /.painel -->
                    </div><!--/. tab-content-->                  
                </div><!-- jumbtron -->
            </div><!--/First row-->
        </section>

        <ng-include src="'../View/footer.html'"></ng-include>
        <!-- /. fim do projeto -->

        <!-- SCRIPTS -->

        <!-- JQuery -->
        <script type="text/javascript" src="../lib/jquery/jquery-2.2.3.min.js"></script>

        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="../lib/bootstrap/tether.min.js"></script>

        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="../lib/bootstrap/bootstrap.min.js"></script>

        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="../lib/bootstrap/mdb.min.js"></script>

        <!-- Paginação da tabela -->
        <script type="text/javascript" src="../js/outros/paginacao-empresa.js"></script>

        <!-- link Angular animate -->
        <script src="../lib/angular/angular-animate.js" type="text/javascript"></script>

        <!-- link Angular css toastr -->
        <link href="../css/angular-toastr.css" rel="stylesheet" type="text/css"/>

        <!-- link TOASTR -->
        <script type="text/javascript" src="../lib/angular/angular-toastr.tpls.js"></script>

        <!-- aside -->
        <script src="../js/outros/aside.js" type="text/javascript"></script>

    </div>
</body>

</html>