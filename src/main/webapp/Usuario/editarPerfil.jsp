<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Pagina para a edição do perfil do usuario
-->
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
        <link href="../css/styleUsuario.css" rel="stylesheet">

        <!-- Your custom styles (efeito) -->
        <link href="../css/efeitos/perflUsuario.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- angular util -->
        <script src="../js/util.js" type="text/javascript"></script>

        <!-- angular controller  -->
        
        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">
    </head>

    <body>
        <!-- inicio do projeto aqui-->
    <ng-include src="'../View/nav-usuario.html'"></ng-include>
    <div class="espaco"></div>


    <section class="section section-blog-fw">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            

            <!--Post data-->
            <div class="jumbotron caixa-informacao-user">

                <ul class="nav nav-tabs md-pills pills-default nav-tab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#panel31" role="tab"><i class="fa fa-lock fa-2x"></i><br>Conta</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#panel32" role="tab"><i class="fa fa-user fa-2x"></i><br> Informações Pessoais</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#panel33" role="tab"><i class="fa fa-map-marker fa-2x"></i><br> Endereço</a>
                    </li>

                </ul>

                <!-- Tab panels -->
                <div class="tab-content conteudo-tab">

                    <!--Panel 1 consulta-->
                    <div class="tab-pane fade in active" id="panel31" role="tabpanel">
                        <br>
                        <form>
                            <h4><i class="fa fa-lock animated rotateIn"></i><strong>Informações da Conta</strong></h4>
                            <div class="md-form col-md-6">  
                                <input  type="email" id="conta-usuario-email" class="form-control" required=""
                                        maxlength="75">
                                <label for="conta-usuario-email">Email</label>
                            </div>
                            <div class="md-form col-md-6">  
                                <label for="conta-usuario-img">Imagem de Perfil</label>
                                <br/>
                                <div class="md-form">                
                                    <input id="conta-usuario-img" type="file" multiple>

                                </div>
                            </div>
                            <div class="md-form col-md-6">  
                                <input type="password" id="conta-usuario-senha" class="form-control" required=""
                                       maxlength="20">
                                <label for="conta-usuario-senha">Senha</label>
                            </div>
                            <div class="md-form col-md-6">  
                                <input  type="password" id="conta-usuario-confirma-senha" class="form-control"
                                        required="" maxlength="20"> 
                                <label for="conta-usuario-confirma-senha">Confirmar a Senha</label>
                            </div>
                            <div class="md-form col-md-2 btn-cadastro">  
                                <input  type="submit" id="btn-cadastrar-conta" class="form-control btn btn-lg btn-warning-outline">                               
                            </div>
                        </form>

                    </div>
                    <!--/.Panel 1-->

                    <!--Panel 2 cadastro -->
                    <div class="tab-pane fade" id="panel32" role="tabpanel">
                        <br>
                        <form>
                            <h4><i class="fa fa-user animated rotateIn"></i><strong>Informações Pessoais</strong></h4>

                            <div class="md-form col-md-6">                
                                <input type="text" id="conta-usuario-nome" class="form-control" required=""
                                       maxlength="35">
                                <label for="conta-usuario-nome">Nome</label>
                            </div>
                            <div class="md-form col-md-6">  
                                <input  type="text" id="conta-usuario-cpf" class="form-control" required=""
                                        maxlength="30">
                                <label for="conta-usuario-cpf">CPF</label>
                            </div>

                            <div class="md-form col-md-4">                
                                <input type="text" id="conta-usuario-dataNacimento" class="form-control" required=""
                                       maxlength="18">
                                <label for="conta-usuario-dataNacimento">Data de Nascimento</label>
                            </div>

                            <div class="md-form col-md-4">  
                                <input  type="text" id="conta-usuario-telefone" class="form-control" required=""
                                        maxlength="12">
                                <label for="conta-usuario-telefone">Telefone </label>
                            </div>
                            <div class="md-form col-md-4">                
                                <input type="text" id="conta-usuario-celular" class="form-control" required=""
                                       maxlength="12">
                                <label for="conta-usuario-celular">Celular</label>
                            </div>
                            <div class="md-form col-md-2 btn-cadastro">  
                                <input  type="submit" id="btn-cadastrar-pessoais" class="form-control btn btn-lg btn-warning-outline">                               
                            </div>
                        </form>                         

                    </div>
                    
                    <!--/.Panel 2-->
                    <div class="tab-pane fade" id="panel33" role="tabpanel">
                        <br>
                        <form>
                            <h4><i class="fa fa-map-marker animated rotateIn"></i><strong>Endereço</strong></h4>

                            <div class="md-form col-md-6">                
                                <input type="text" id="evento-cep" class="form-control">
                                <label for="conta-usuario-cep">CEP</label>
                            </div>

                            <div class="md-form col-md-6">  
                                <input  type="text" id="evento-numero" class="form-control">
                                <label for="conta-usuario-numero">Número</label>
                            </div>

                            <div class="md-form col-md-6">                
                                <input type="text" id="evento-rua" class="form-control">
                                <label for="conta-usuario-rua">Rua</label>
                            </div>

                            <div class="md-form col-md-6">  
                                <input  type="text" id="evento-bairro" class="form-control">
                                <label for="conta-usuario-bairro">Bairro</label>
                            </div>

                            <div class="md-form col-md-6">                
                                <input type="text" id="evento-complemento" class="form-control">
                                <label for="conta-usuario-complemento">Complemento</label>
                            </div>

                            <div class="md-form col-md-6">  
                                <input  type="text" id="evento-cidade" class="form-control">
                                <label for="conta-usuario-cidade">Cidade</label>
                            </div>

                            <div class="md-form col-md-2 btn-cadastro">  
                                <input  type="submit" id="Btn-cadastrar" class="form-control btn btn-lg btn-warning-outline">                               
                            </div>

                        </form> <!-- /.form -->                      

                    </div><!--/.Panel 2-->                    
                </div><!-- tab content -->
            </div> <!-- /.jumbtron caixa-informacao-especifico -->
        </div> <!-- /.col-md-10 -->    
    </section> <!-- /.section -->
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
