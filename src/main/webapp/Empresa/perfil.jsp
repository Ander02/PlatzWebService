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
        
        <!-- Link Controller -->

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

        <!-- Your custom styles (efeito) -->
        <link href="../css/efeitos/perfilEmpresa.css" rel="stylesheet">

    </head>

    <body>
        <!-- inicio do projeto aqui-->
    <ng-include src="'../View/nav-empresa.html'"></ng-include>

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
                                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 imagem-perfil-empresa-efeito">
                                    <div class="hovereffect">
                                        <img class="img-responsive" src="../img/outras/plano-fundo.jpg" alt="">
                                        <div class="overlay">
                                            <h2>Nome da Empresa</h2>
                                            <a class="info" href="editarPerfil.jsp">Editar Perfil</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card-block">

                                <!--Name-->
                                <h4 class="card-title">Razão Social</h4>                              
                                <hr>
                                <!--Quotation-->
                                <p>Endereço e telefone</p>
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
                                <div class="col-md-4 evento-perfil-empresa">
                                    <div class="card">
                                        <div class="hovereffect">
                                            <img class=" img-responsive img-evento-efeito" src="../img/outras/plano-fundo.jpg" alt="Imagem do Evento" >
                                        

                                        </div><!-- /. div hovereffect  -->

                                        <h4 class="card-title">Nome do evento</h4>
                                        <h5><i class="fa fa-building-o animated bounceInDown"></i> Empresa</h5>
                                        <p><i class="fa fa-calendar animated bounceInDown"></i> Data: </p>
                                        <p><i class="fa fa-map-marker animated bounceInDown"></i> Local: </p>
                                        <p><a class="btn btn-warning " href="../eventoEspecifico.html" role="button">Ver Mais Detalhes &raquo;</a></p>
                                    </div>
                                </div>
                        </div><!-- /.card group -->
                    </div><!-- /.painel -->
                </div><!--/. tab-content-->                  
            </div><!-- jumbtron -->
        </div><!--/First row-->
</section>


<!-- /Start your project here-->


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


</body>

</html>