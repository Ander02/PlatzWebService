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
        <section class="section section-blog-fw" ng-if="permicao">
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
                                <div class="md-form col-md-12">  
                                <label for="conta-usuario-email" class="label-form" > Email:  {{usuario.conta.email}} </label>
                                <hr/>
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

                                <div class="md-form col-md-2">                
                                    <input type="text" id="usuario-cep" class="form-control" ng-blur="onblurCep()">
                                    <label for="usuario-cep">CEP</label>
                                </div>
                                <div class="col-md-2 link-cep">
                                    <a title="clique aqui e saiba seu cep" class="text-info"
                                       href="http://www.buscacep.correios.com.br/sistemas/buscacep/" target="_blank">Não Sei meu cep</a></div>

                                <div class="md-form col-md-3">  
                                    <input  type="text" id="usuario-numero" class="form-control" ng-model="usuarioEdicaoEndereco.endereco.numero">
                                    <label for="usuario-numero">Número</label>
                                </div>

                                <div class="md-form col-md-3">                
                                    <input type="text" id="usuario-complemento" class="form-control" usuarioEdicaoEndereco.endereco.complemento>
                                    <label for="usuario-complemento">Complemento</label>
                                </div>

                                <div class="md-form col-md-12">       
                                    <p ng-bind="usuarioEdicaoEndereco.endereco.rua"></p>
                                    <label for="usuario-rua">Rua</label>
                                    <hr>
                                </div>

                                <div class="md-form col-md-12">                                 
                                    <p ng-bind="usuarioEdicaoEndereco.endereco.bairro"></p>
                                    <label for="usuario-bairro">Bairro</label>  
                                    <hr>
                                </div>

                                <div class="md-form col-md-12">  
                                    <p >{{usuarioEdicaoEndereco.endereco.cidade.nome|| usuarioEdicaoEndereco.endereco.cidade}}</p>
                                    <label for="usuario-cidade">Cidade</label>                                   
                                    <hr>
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
    </div>
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
