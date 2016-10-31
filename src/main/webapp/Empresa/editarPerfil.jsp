<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
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
        <link href="../css/styleEmpresa.css" rel="stylesheet">
        

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- link app -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- link util -->
        <script src="../js/util.js" type="text/javascript"></script>
        <!-- Link Controller -->
        <script src="../js/controller/loginController.js" type="text/javascript"></script>

        <script src="../js/controller/perfilEmpresaController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">
    </head>

    <body ng-controller="perfilEmpresaController">

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
        <!-- inicio do projeto aqui-->

    <ng-include src="'../View/nav-empresa.html'"></ng-include>


    <div class="espaco"></div>


    <section class="section section-blog-fw">
        <div class="col-md-1"></div>
        <div class="col-md-10">


            <!--Post data-->
            <div class="jumbotron caixa-informacao-empresa">

                <ul class="nav nav-tabs md-pills pills-default nav-tab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#panel31" role="tab"><i class="fa fa-lock fa-2x"></i><br>Conta</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#panel32" role="tab"><i class="fa fa-building-o fa-2x"></i><br> Informações Empresariais</a>
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
                                <label for="conta-empresa-email" class="label-form" > Email:  {{empresa.conta.email}} </label>
                                <p></p>
                                <hr/>
                            </div>

                            <div class="md-form col-md-6">  
                                <input type="password" id="conta-empresa-senha" class="form-control" required ng-model="empresaEdicaoSenha.senha"
                                       maxlength="20">
                                <label for="conta-empresa-senha">Senha</label>
                            </div>
                            <div class="md-form col-md-6">  
                                <input  type="password" id="conta-empresa-confirma-senha" class="form-control" ng-model="empresaEdicaoSenha.confirmaSenha"
                                        required maxlength="20"> 
                                <label for="conta-empresa-confirma-senha">Confirmar a Senha</label>
                            </div>
                            <div class="md-form col-md-2 btn-cadastro">  
                                <input  type="submit" id="btn-cadastrar-conta" class="form-control btn btn-lg btn-warning-outline" ng-click="alterarSenha()">                        
                            </div>
                        </form>

                    </div>
                    <!--/.Panel 1-->

                    <!--Panel 2 cadastro -->
                    <div class="tab-pane fade" id="panel32" role="tabpanel">
                        <br>
                        <form>
                            <h4><i class="fa fa-building-o animated rotateIn"></i><strong>Informações Empresariais</strong></h4>

                            <div class="md-form col-md-12">  
                                <label for="conta-empresa-img">Imagem de Perfil</label>
                                <br/>
                                <div class="md-form">                
                                    <input id="conta-empresa-img" type="file" >
                                </div>
                            </div>

                            <div class="md-form col-md-6">                
                                <input type="text" id="conta-empresa-razao" class="form-control" required ng-model="empresaEdicaoInfo.razaoSocial"
                                       maxlength="35">
                                <label for="conta-empresa-razao">Razão Social</label>
                            </div>
                            <div class="md-form col-md-6">  
                                <input  type="text" id="conta-empresa-nomeFantasia" class="form-control" required ng-model="empresaEdicaoInfo.nomeFantasia"
                                        maxlength="30">
                                <label for="conta-empresa-nomeFantasia">Nome Fantasia</label>
                            </div>

                            <div class="md-form col-md-4">                
                                <input type="text" id="conta-empresa-cnpj" class="form-control" required ng-model="empresaEdicaoInfo.cnpj"
                                       maxlength="18">
                                <label for="conta-empresa-cnpj">CNPJ</label>
                            </div>

                            <div class="md-form col-md-4">  
                                <input  type="text" id="conta-empresa-telefone1" class="form-control" required ng-model="empresaEdicaoInfo.telefone"
                                        maxlength="12">
                                <label for="conta-empresa-telefone1">Telefone 1</label>
                            </div>
                            <div class="md-form col-md-4">                
                                <input type="text" id="conta-empresa-telefone2" class="form-control" required ng-model="empresaEdicaoInfo.telefone2"
                                       maxlength="12">
                                <label for="conta-empresa-telefone2">Telefone 2</label>
                            </div>
                            <div class="md-form col-md-2 btn-cadastro">  
                                <input  type="submit" id="btn-cadastrar-pessoais" class="form-control btn btn-lg btn-warning-outline" ng-click="alterarInfoEmpresariais()">    
                            </div>
                        </form>                         

                    </div>
                    <!--/.Panel 2-->

                    <div class="tab-pane fade" id="panel33" role="tabpanel">
                        <br>
                        <form>
                            <h4><i class="fa fa-map-marker animated rotateIn"></i><strong>Endereço</strong></h4>

                            <div class="md-form col-md-2">                
                                <input type="text" id="conta-empresa-cep" class="form-control" ng-blur="onblurCep()">
                                <label for="conta-empresa-cep" id="conta-empresa-cep" >CEP</label>
                            </div>
                            <div class="col-md-2 link-cep">
                                <a title="clique aqui e saiba seu cep" class="text-info"
                                   href="http://www.buscacep.correios.com.br/sistemas/buscacep/" target="_blank">Não Sei meu cep</a></div>

                            <div class="md-form col-md-4">  
                                <input  type="text" id="conta-empresa-numero" class="form-control" ng-model="empresaEdicaoEndereco.endereco.numero">
                                <label for="conta-empresa-numero">Número</label>
                            </div>

                            <div class="md-form col-md-4">                
                                <input type="text" id="conta-empresa-complemento" class="form-control" ng-model="empresaEdicaoEndereco.endereco.complemento">
                                <label for="conta-empresa-complemento">Complemento</label>
                            </div>

                            <div class="md-form col-md-12">
                                <label for="conta-empresa-rua" class="label-form">Rua</label>
                                <p ng-bind="empresaEdicaoEndereco.endereco.rua"></p>
                                <hr>
                            </div>

                            <div class="md-form col-md-12">                                 
                                <label for="conta-empresa-bairro" class="label-form">Bairro</label>
                                <p ng-bind="empresaEdicaoEndereco.endereco.bairro"></p>
                                <hr>
                            </div>

                            <div class="md-form col-md-12">  
                                <label for="conta-empresa-cidade" class="label-form">Cidade</label>
                                <p >{{empresaEdicaoEndereco.endereco.cidade.nome || empresaEdicaoEndereco.endereco.cidade}}</p>
                                <hr>
                            </div>

                            <div class="md-form col-md-2 btn-cadastro">  
                                <input  type="submit" id="Btn-cadastrar" class="form-control btn btn-lg btn-warning-outline" ng-click="alterarEndereco()">                               
                            </div>

                        </form> <!-- /.form -->                      

                    </div>
                    <!--/.Panel 2-->



                </div>

            </div> <!-- /.jumbtron caixa-informacao-especifico -->
        </div> <!-- /.col-md-10 -->    
    </section> <!-- /.section -->

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
    
      <script src="../lib/jquery/jquery.mask.min.js" type="text/javascript"></script>

        <script src="../js/outros/validacoes.js" type="text/javascript"></script>


</body>

</html>
