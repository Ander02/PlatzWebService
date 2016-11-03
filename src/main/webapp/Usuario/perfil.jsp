<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Perfil do usuario, onde ele pode alterar as coisas sobre sua conta
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
        
        <!-- Your custom styles (efeito) -->
        <link href="../css/efeitos/perflUsuario.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- angular util -->
        <script src="../js/util.js" type="text/javascript"></script>
        
        <!-- service -->
        <script src="../js/services/loginService.js" type="text/javascript"></script>

        <!-- angular controller  -->

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">



    </head>

    <body>
        <!-- inicio do projeto aqui-->
    <ng-include src="'../View/nav-usuario.html'"></ng-include>


    <div class="espaco"></div>


    <section class="section section-blog-fw">
        <div>
            <div class="col-md-1"></div>
            <div class="col-md-10">
                
                <div class="jumbotron caixa-informacao-user">
                    <div class="col-lg-12 col-md-12 m-b-r">

                        <!--Card-->
                        <div class="card testimonial-card">
                            <div>
                                <!--Avatar-->
                                <div class="col-md-12">
                                    <div class="col-md-8 img-perfil-usuario">                          

                                        <div class="hovereffect">
                                            <img class="img-responsive animated tada" src="../img/outras/plano-fundo.jpg" alt="">
                                            <div class="overlay">
                                                <h2>Editar Informações</h2>
                                                <a class="info" href="editarPerfil.jsp">Editar</a>
                                            </div>
                                        </div>

                                    </div>
                                </div><!--/.Avatar-->
                                <div class="card-block">
                                    <!--Name-->
                                    <h2 class="card-title">Nome usuario</h2>                                  
                                </div>
                            </div>
                            <hr/>                           

                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs tabs-3 " role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#panel5" role="tab"><i class="fa fa-user animated tada"></i> Informações Pessoais</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel6" role="tab"><i class="fa fa-map-marker animated tada"></i> Endereço</a>
                                </li>                                
                            </ul>

                            <!-- Tab panels -->
                            <div class="tab-content">

                                <!--Panel 1-->
                                <div class="tab-pane fade in active" id="panel5" role="tabpanel">
                                    <br>
                                    <ul class="list-group">
                                        <li class="list-group-item"><i class="fa fa-envelope-o"></i> Email</li>
                                        <li class="list-group-item"><i class="fa fa-calendar"></i>Data de Nascimento</li>
                                        <li class="list-group-item"><i class="fa fa-phone"></i>Telefone</li>
                                        <li class="list-group-item"><i class="fa fa-user"></i>CPF</li>                                
                                    </ul>
                                </div><!--/.Panel 1-->                                
                                <!--Panel 2-->
                                <div class="tab-pane fade" id="panel6" role="tabpanel">
                                    <br>
                                    <ul class="list-group">
                                        <li class="list-group-item"><i class="fa fa-map-pin"></i>CEP</li>
                                        <li class="list-group-item"><i class="fa fa-map-pin"></i>Rua</li>
                                        <li class="list-group-item"><i class="fa fa-map-pin"></i>Numero</li>
                                        <li class="list-group-item"><i class="fa fa-map-pin"></i>Bairro</li> 
                                        <li class="list-group-item"><i class="fa fa-map-pin"></i>Complemento</li>
                                        <li class="list-group-item"><i class="fa fa-map-pin"></i>Cidade</li> 
                                    </ul>
                                </div><!--/.Panel 2-->                                
                            </div><!-- /.Tab panels -->
                            <button class="btn btn-default-outline"><i class="fa fa-pencil-square-o"></i> Editar Perfil</button>
                        </div><!--/.Card-->
                    </div>
                </div>               
            </div>
        </div>
    </section>
    
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
