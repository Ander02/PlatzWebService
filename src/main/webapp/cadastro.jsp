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
        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

        <!-- Material Design Bootstrap -->
        <link href="css/bootstrap/mdb.min.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="css/style.css" rel="stylesheet">

        <!-- link Angular --> 
        <script type="text/javascript" src="lib/angular/angular.js"></script>
        
        <script src="js/app.js" type="text/javascript"></script>
        
        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/controller/cadastroController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">
    </head>

    <body ng-controller="cadastroController">
        <!-- inicio do projeto aqui-->

    <ng-include src="'View/nav.html'"></ng-include>

    <div class="espaco"></div>

    <div class="head-pagina col-md-12">

        <h1>Cadastro</h1>
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <p>Escolha uma maneira de cadastro</p>
            <p>Ao escolher se cadastrar como empresa você tera os beneficios de  poder 
                postar eventos e ver avaliações de seus visitantes, alem de ter um perfil publico, você tera mais interação 
                com quem acompanha seus eventos </p>
            <p>Ao escolher se cadastrar como usuario , você tera mais facilidade a encontrar o caminho certo
                para os eventos que você marcou presençã, alem de poder avaliar e entrar em contato direto com a empresa
                organizadora do evento</p>

        </div>
    </div>

    <div id="box-toggle">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <div class="col-md-5">
                <button class="btn-warning-outline btn-lg" id="btnEmpresa" onclick="Mudarestado('cadastroEmpresa', 'cadastroUsuario'), selecionar('btnEmpresa', 'btnUsuario')">
                    <i class="fa fa-building-o animated fadeInLeftBig"></i> EMPRESA</button>
            </div>
            <div class="col-md-2"></div>
            <div class="col-md-5">
                <button class="btn-default-outline btn-lg" id="btnUsuario" onclick="Mudarestado('cadastroUsuario', 'cadastroEmpresa'), selecionar('btnUsuario', 'btnEmpresa')">
                    <i class="fa fa-user animated fadeInLeftBig"></i> USUARIO</button>
            </div>
        </div>
        <div class="tgl formCadastro" id="cadastroEmpresa">
            <form>

                <div class="col-md-8 corpo-form-cadastro">

                    <div class="col-md-12">

                        <!--Card content-->
                        <div class="card-block text-xs-center">
                            <!--Title-->
                            <h4 class="card-title"><i class="fa fa-lock animated rotateIn"></i><strong> Informações Conta</strong></h4>
                            <div class="col-md-12">
                                <label for="conta-empresa-img">Imagem de Perfil</label>
                                <div class="md-form">                
                                    <input id="conta-empresa-img" type="file" multiple>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="md-form">  
                                    <input  type="email" id="conta-empresa-email" class="form-control" required ng-model="empresa.conta.email"
                                            maxlength="75">
                                    <label for="conta-empresa-email">Email</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="md-form">                
                                    <input type="password" id="conta-empresa-senha" class="form-control" required ng-model="empresa.conta.senha"
                                           maxlength="20">
                                    <label for="conta-empresa-senha">Senha</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="md-form">  
                                    <input  type="password" id="conta-empresa-confirma-senha" class="form-control" ng-model="empresa.conta.confirmaSenha"
                                            required maxlength="20"> 
                                    <label for="conta-empresa-confirma-senha">Confirmar a Senha </label>
                                </div>
                            </div>                      

                        </div>
                        <!--/.Card content-->

                    </div>

                    <div class="col-md-12">

                        <!--Card content-->
                        <div class="card-block text-xs-center">
                            <!--Title-->
                            <h4 class="card-title"><i class="fa fa-building-o animated rotateIn"></i><strong> Informações Empresariais</strong></h4>
                            <div class="col-md-12">
                                <div class="md-form">                
                                    <input type="text" id="conta-empresa-razao" class="form-control" ng-model="empresa.razaoSocial"
                                           required maxlength="35">
                                    <label for="conta-empresa-razao" >Razão Social</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="md-form">  
                                    <input  type="text" id="conta-empresa-nomeFantasia" class="form-control" required ng-model="empresa.nomeFantasia"
                                            maxlength="30">
                                    <label for="conta-empresa-nomeFantasia">Nome Fantasia</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="md-form">                
                                    <input type="text" id="conta-empresa-cnpj" class="form-control" required ng-model="empresa.cnpj"
                                           maxlength="18">
                                    <label for="conta-empresa-cnpj">CNPJ</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">  
                                    <input  type="text" id="conta-empresa-telefone1" class="form-control" required ng-model="empresa.telefone"
                                            maxlength="12">
                                    <label for="conta-empresa-telefone1">Telefone 1</label>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="conta-empresa-telefone2" class="form-control" required ng-model="empresa.telefone2"
                                           maxlength="12">
                                    <label for="conta-empresa-telefone2">Telefone 2</label>
                                </div>
                            </div>


                        </div>
                        <!--/.Card content-->

                    </div>
                    <!--/.Card-->
                    <div class="col-md-12">

                        <!--Card content-->
                        <div class="card-block text-xs-center">
                            <!--Title-->
                            <h4 class="card-title"><i class="fa fa-map-marker animated rotateIn"></i><strong> Endereço</strong></h4>
                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="evento-cep" class="form-control" ng-model="empresa.endereco.cep">
                                    <label for="conta-empresa-cep" >CEP</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">  
                                    <input  type="text" id="evento-numero" class="form-control" ng-model="empresa.endereco.numero">
                                    <label for="conta-empresa-numero">Número</label>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="evento-rua" class="form-control" ng-model="empresa.endereco.rua">
                                    <label for="conta-empresa-rua">Rua</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">  
                                    <input  type="text" id="evento-bairro" class="form-control" ng-model="empresa.endereco.bairro">
                                    <label for="conta-empresa-bairro">Bairro</label>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="evento-complemento" class="form-control" ng-model="empresa.endereco.complemento"complemento>
                                    <label for="conta-empresa-complemento">Complemento</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">  
                                    <input  type="text" id="evento-cidade" class="form-control" ng-model="empresa.endereco.cidade">
                                    <label for="conta-empresa-cidade">Cidade</label>
                                </div>
                            </div>

                        </div>
                        <!--/.Card content-->

                        <div>
                            <button class="btn btn-lg btn-warning" ng-click="cadastrarEmpresa()"><i class="fa fa-check"></i> Cadastrar</button>
                        </div>
                    </div>



                </div>
            </form>
        </div>

        <div class="tgl formCadastro" id="cadastroUsuario">
            <form>

                <div class="col-md-8 corpo-form-cadastro">

                    <div class="col-md-12">

                        <!--Card content-->
                        <div class="card-block text-xs-center">
                            <!--Title-->
                            <h4 class="card-title"><i class="fa fa-lock animated rotateIn"></i><strong>Informações Conta</strong></h4>
                            <div class="col-md-12">
                                <label for="conta-usuario-img">Imagem de Perfil</label>
                                <div class="md-form">                
                                    <input id="conta-usuario-img" type="file" multiple>

                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="md-form">  
                                    <input  type="email" id="conta-usuario-email" class="form-control" required=""
                                            maxlength="75">
                                    <label for="conta-usuario-email">Email</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="md-form">                
                                    <input type="password" id="conta-usuario-senha" class="form-control" required=""
                                           maxlength="20">
                                    <label for="conta-usuario-senha">Senha</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="md-form">  
                                    <input  type="password" id="conta-usuario-confirma-senha" class="form-control"
                                            required="" maxlength="20"> 
                                    <label for="conta-usuario-confirma-senha">Confirmar a Senha</label>
                                </div>
                            </div>                      

                        </div>
                        <!--/.Card content-->

                    </div>

                    <div class="col-md-12">

                        <!--Card content-->
                        <div class="card-block text-xs-center">
                            <!--Title-->
                            <h4 class="card-title"><i class="fa fa-user animated rotateIn"></i><strong>Informações Pessoais</strong></h4>
                            <div class="col-md-12">
                                <div class="md-form">                
                                    <input type="text" id="conta-usuario-nome" class="form-control" required=""
                                           maxlength="35">
                                    <label for="conta-usuario-nome">Nome</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="md-form">  
                                    <input  type="text" id="conta-usuario-cpf" class="form-control" required=""
                                            maxlength="30">
                                    <label for="conta-usuario-cpf">CPF</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="md-form">                
                                    <input type="text" id="conta-usuario-dataNacimento" class="form-control" required=""
                                           maxlength="18">
                                    <label for="conta-usuario-dataNacimento">Data de Nascimento</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">  
                                    <input  type="text" id="conta-usuario-telefone" class="form-control" required=""
                                            maxlength="12">
                                    <label for="conta-usuario-telefone">Telefone </label>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="conta-usuario-celular" class="form-control" required=""
                                           maxlength="12">
                                    <label for="conta-usuario-celular">Celular</label>
                                </div>
                            </div>


                        </div>
                        <!--/.Card content-->

                    </div>
                    <!--/.Card-->
                    <div class="col-md-12">

                        <!--Card content-->
                        <div class="card-block text-xs-center">
                            <!--Title-->
                            <h4 class="card-title"><i class="fa fa-map-marker animated rotateIn"></i><strong>Endereço</strong></h4>
                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="evento-cep" class="form-control">
                                    <label for="conta-usuario-cep">CEP</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">  
                                    <input  type="text" id="evento-numero" class="form-control">
                                    <label for="conta-usuario-numero">Número</label>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="evento-rua" class="form-control">
                                    <label for="conta-usuario-rua">Rua</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">  
                                    <input  type="text" id="evento-bairro" class="form-control">
                                    <label for="conta-usuario-bairro">Bairro</label>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="evento-complemento" class="form-control">
                                    <label for="conta-usuario-complemento">Complemento</label>
                                </div>
                            </div>
                            <div class="col-md-6 ">
                                <div class="md-form">  
                                    <input  type="text" id="evento-cidade" class="form-control">
                                    <label for="conta-usuario-cidade">Cidade</label>
                                </div>
                            </div>

                        </div>
                        <!--/.Card content-->
                        <div >
                            <button class="btn btn-lg btn-warning"><i class="fa fa-check"></i> Cadastrar</button>
                        </div>
                    </div>

                </div>
            </form>
        </div>

    </div>



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



</body>

</html>