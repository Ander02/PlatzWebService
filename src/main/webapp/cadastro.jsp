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

        <!-- link app -->
        <script src="js/app.js" type="text/javascript"></script>

        <!-- link util -->
        <script src="js/util.js" type="text/javascript"></script>

        <!-- link controller -->
        <script src="js/controller/cadastroController.js" type="text/javascript"></script>

        <!-- link controller -->
        <script src="js/controller/loginController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">

        <!--links para o funcionamento do datetimepicker-->
        <link href="css/bootstrap/bootstrap-material-datetimepicker.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-1.12.3.min.js" integrity="sha256-aaODHAgvwQW1bFOGXMeX+pC4PZIPsvn2h1sArYOhgXQ=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="http://momentjs.com/downloads/moment-with-locales.min.js"></script>
    </head>

    <body >
        <!-- inicio do projeto aqui-->

    <ng-include src="'View/nav.html'" ng-controller="loginController"></ng-include>

    <div ng-controller="cadastroController">
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
                                <div class="col-md-4">
                                    <div class="md-form">                
                                        <input type="text" id="empresa-cep" class="form-control" ng-model="empresa.endereco.cep" ng-blur="onblurCepEmpresa()">
                                        <label for="conta-empresa-cep" >CEP</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="md-form">  
                                        <input  type="text" id="empresa-numero" class="form-control" ng-model="empresa.endereco.numero">
                                        <label for="conta-empresa-numero">Número</label>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="md-form">                
                                        <input type="text" id="empresa-complemento" class="form-control" ng-model="empresa.endereco.complemento"complemento>
                                        <label for="conta-empresa-complemento">Complemento</label>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    
                                        <label for="conta-empresa-rua" class="label-form">Rua</label>
                                        <p>{{empresa.endereco.rua}}</p>
                                        <hr>
                                    
                                </div>
                                <div class="col-md-12">
                                    
                                        <label for="conta-empresa-bairro" class="label-form">Bairro</label>
                                        <p>{{empresa.endereco.bairro}}</p>   
                                        <hr>
                                    
                                </div>

                                <div class="col-md-12 ">
                                    
                                        <label for="conta-empresa-cidade" class="label-form">Cidade</label>
                                        <p>{{empresa.endereco.cidade}}</p>   
                                        <hr>
                                    
                                </div>                               

                            </div>
                            <!--/.Card content-->

                            <div>
                                <button class="btn btn-lg btn-warning" data-toggle="modal" data-target="#modalLogar"  ng-click="cadastrarEmpresa()"><i class="fa fa-check"></i> Cadastrar</button>                                
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
                                        <input  type="email" id="conta-usuario-email" class="form-control" required ng-model="usuario.conta.email"
                                                maxlength="75">
                                        <label for="conta-usuario-email">Email</label>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="md-form">                
                                        <input type="password" id="conta-usuario-senha" class="form-control" required ng-model="usuario.conta.senha"
                                               maxlength="20">
                                        <label for="conta-usuario-senha">Senha</label>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="md-form">  
                                        <input  type="password" id="conta-usuario-confirma-senha" class="form-control" ng-model="usuario.conta.confirmaSenha"
                                                required maxlength="20"> 
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
                                        <input type="text" id="conta-usuario-nome" class="form-control" required ng-model="usuario.nome"
                                               maxlength="35">
                                        <label for="conta-usuario-nome">Nome</label>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="md-form">  
                                        <input  type="text" id="conta-usuario-cpf" class="form-control" required ng-model="usuario.cpf"
                                                maxlength="30">
                                        <label for="conta-usuario-cpf">CPF</label>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-control-wrapper">
                                        <input type="text" id="date" class="form-control floating-label" placeholder="Data de nascimento">
                                    </div>

                                </div>
                                <div class="col-md-6">
                                    <div class="md-form">  
                                        <input  type="text" id="conta-usuario-telefone" class="form-control" required ng-model="usuario.telefone"
                                                maxlength="12">
                                        <label for="conta-usuario-telefone">Celular</label>
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
                                <div class="col-md-4">
                                    <div class="md-form">                
                                        <input type="text" id="usuario-cep" class="form-control" ng-model="usuario.endereco.cep" ng-blur="onblurCepUsuario()">
                                        <label for="conta-usuario-cep">CEP</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="md-form">  
                                        <input  type="text" id="usuario-numero" class="form-control" ng-model="usuario.endereco.numero">
                                        <label for="conta-usuario-numero">Número</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="md-form">                
                                        <input type="text" id="usuario-complemento" class="form-control" ng-model="usuario.endereco.complemento">
                                        <label for="conta-usuario-complemento">Complemento</label>
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <label for="conta-usuario-rua" class="label-form">Rua</label>
                                    <p>{{usuario.endereco.rua}}</p>
                                    <hr>
                                </div>
                                <div class="col-md-12"> 
                                    <label for="conta-usuario-bairro" class="label-form">Bairro</label>                                    
                                    <p>{{usuario.endereco.bairro}}</p>
                                    <hr>
                                </div>

                                <div class="col-md-12 ">
                                    <label for="conta-usuario-cidade"class="label-form" >Cidade</label>
                                    <p>{{usuario.endereco.cidade}}</p>
                                    <hr>
                                </div>                              

                            </div>
                            <!--/.Card content-->
                            <div>
                                <button class="btn btn-lg btn-warning " data-toggle="modal" data-target="#modalLogar" ng-click="cadastrarUsuario()"><i class="fa fa-check"></i> Cadastrar </button>
                            </div>
                        </div>

                    </div>
                </form>            
            </div>
        </div>

        <!-- modal de login após o cadastro -->
        <div class="modal fade" id="modalLogar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!--Content-->
                <div class="modal-content">
                    <!--Header-->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Login</h4>
                    </div>
                    <!--Body-->
                    <div class="modal-body">
                        <form>
                            <!--Body-->
                            <h3> Deseja se logar agora? </h3>

                            <div class="text-xs-center">
                                <button class="btn btn-warning"  data-dismiss="modal" ng-click="logar()"> Sim </button>
                                <button type="button" class="btn btn-warning" data-dismiss="modal"> Não </button>
                            </div>
                        </form>
                    </div><!-- modal body -->
                </div><!--/.Content-->            
            </div><!-- modal-dialog -->
        </div> <!--/.modal-->


        <!-- fim da modal -->

        <ng-include src="'View/footer.html'"></ng-include>
        <!-- /. fim do html -->


        <!-- SCRIPTS -->

        <!-- JQuery -->
        <script type="text/javascript" src="lib/jquery/jquery-2.2.3.min.js"></script>

        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="lib/bootstrap/tether.min.js"></script>

        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="lib/bootstrap/bootstrap.min.js"></script>

        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="lib/bootstrap/mdb.min.js"></script>

        <!--data e horario (iniciando) -->
        <script src="lib/bootstrap/dataehorario.js" type="text/javascript"></script>

        <script src="lib/bootstrap/bootstrap-material-datetimepicker.js" type="text/javascript"></script>

        <!-- esconder formularios -->
        <script type="text/javascript" src="js/outros/esconder-form-cad.js"></script>

        <!-- link Angular -->
        <script src="lib/angular/angular-animate.js" type="text/javascript"></script>

        <!-- link TOASTER CSS -->
        <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>

        <!-- link TOASTR JS -->
        <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>

    </div>

</body>

</html>
