<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Pagina de cadastro , consulta, atualização e exclusão de conta, que seria contas administrativas apenas
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
        <link href="../css/styleAdm.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

       <script type="text/javascript" src="../js/app.js"></script>

        <script src="../js/util.js" type="text/javascript"></script>
        
        <script src="../js/services/loginService.js" type="text/javascript"></script>
        
        <script src="../js/services/validacaoService.js" type="text/javascript"></script>
        
        <script src="../js/controller/loginController.js" type="text/javascript"></script>

        <script src="../js/controller/contaController.js" type="text/javascript"></script>     

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

    </head>

    <body>
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

    <ng-include src="'../View/nav-adm.html'" ng-controller="loginController"></ng-include>
    <div ng-controller="contaController">
        <div ng-if="permicao">

            <div class="espaco"></div>


            <div class="head-pagina">

                <h1>Contas</h1>

            </div>

            <!-- Nav tabs -->
            <ul class="nav nav-tabs md-pills pills-default nav-tab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#panel31" role="tab"><i class="fa fa-user"></i>Administrador</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel32" role="tab"><i class="fa fa-users"></i>Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel33" role="tab"><i class="fa fa-building-o"></i>Empresa</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel34" role="tab"><i class="fa fa-user-plus"></i>Cadastrar Administrador</a>
                </li>

            </ul>

            <!-- Tab panels -->
            <div class="tab-content conteudo-tab">

                <!--Panel 1 Administrador -->
                <div class="tab-pane fade in active" id="panel31" role="tabpanel">
                    <br>
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs md-pills pills-ins" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#panel11" role="tab"><i class="fa fa-users animated lightSpeedIn"></i>Ativos/Desbloqueados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#panel12" role="tab"><i class="fa fa-lock animated lightSpeedIn "></i>Bloqueados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#panel13" role="tab"><i class="fa fa-remove animated lightSpeedIn "></i>Inativos</a>
                        </li>
                    </ul>
                    <!-- Tab panels -->
                    <div class="tab-content">

                        <!--/.Panel 1.1 administradores 'livres'-->
                        <div class="tab-pane fade in active" id="panel11" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>         
                                        </tr>
                                    </thead>
                                    <tbody id="myTable">
                                        <tr ng-repeat="admin in admintradores">
                                            <th scope="row">{{$index + 1}}</th>

                                            <td ng-bind="admin.email"></td>
                                            <td ng-bind="admin.dataCadastro"></td>
                                            <td ng-bind="admin.ultimoAcesso"></td>
                                            <td align=right >    
                                                <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalBloquear" ng-click="prepararBloqueamento(admin.id)"><i class="fa fa-toggle-on"></i> Bloquear</a>                                    
                                                <a class="btn btn-sm btn-warning" data-toggle="modal" data-target="#modalInativar" ng-click="prepararInativacao(admin.id)"><i class="fa fa-toggle-on"></i> Inativar</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager"></ul>
                            </div>
                        </div><!--/.Panel 1.1 administradores 'livres'-->         
                        <!--Panel 1.2administradores bloqueados -->
                        <div class="tab-pane fade" id="panel12" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>                                   
                                        </tr>
                                    </thead>
                                    <tbody id="myTable2">
                                        <tr ng-repeat="admin in admintradoresBloqueados">
                                            <th scope="row">{{$index + 1}}</th>
                                            <!--<td>{{admin.email | limitTo:15}}{{admin.email.length >=15 ? '...':''}}</td>-->
                                            <td ng-bind="admin.email"></td>
                                            <td ng-bind="admin.dataCadastro"></td>
                                            <td ng-bind="admin.ultimoAcesso"></td>
                                            <td>
                                                <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalDesbloquear" ng-click="prepararDesbloqueamento(admin.id)"><i class="fa fa-toggle-on"></i> Desbloquear</a>                                    </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager2"></ul>
                            </div>

                        </div><!--/.Panel 1.2 administradores bloqueados-->
                        <!--Panel 1.3 administradores inativos-->
                        <div class="tab-pane fade" id="panel13" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>                                   
                                        </tr>
                                    </thead>
                                    <tbody id="myTable3">
                                        <tr ng-repeat="admin in admintradoresInativos">
                                            <th scope="row">{{$index + 1}}</th>
                                            <td ng-bind="admin.email"></td>
                                            <td ng-bind="admin.dataCadastro"></td>
                                            <td ng-bind="admin.ultimoAcesso"></td>                                    
                                            <td align=right>
                                                <a ng-if="!isBloqueado(admin)" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalBloquear" ng-click="prepararBloqueamento(admin.id)"><i class="fa fa-toggle-on"></i> Bloquear</a>
                                                <a ng-if="isBloqueado(admin)" class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalDesbloquear" ng-click="prepararDesbloqueamento(admin.id)"><i class="fa fa-toggle-on"></i> Desbloquear</a>
                                            </td>
                                            <td>
                                                <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalAtivar" ng-click="prepararAtivacao(admin.id)"><i class="fa fa-toggle-on"></i> Ativar</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager3"></ul>
                            </div>

                        </div><!--/.Panel 1.3 administradores inativos-->                
                    </div><!--/.div tab content -->
                </div><!--/.Panel 1 Administrador-->

                <!-- Painel 2 Usuario -->
                <div class="tab-pane fade" id="panel32" role="tabpanel">
                    <br>
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs md-pills pills-ins" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#panel14" role="tab"><i class="fa fa-users animated lightSpeedIn"></i>Ativos/Desbloqueados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#panel15" role="tab"><i class="fa fa-lock animated lightSpeedIn "></i>Bloqueados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#panel16" role="tab"><i class="fa fa-remove animated lightSpeedIn "></i>Inativos</a>
                        </li>
                    </ul>
                    <!-- Tab panels -->
                    <div class="tab-content">

                        <!--/.Panel 2.1 usuarios 'livres'-->
                        <div class="tab-pane fade in active" id="panel14" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>                                   
                                        </tr>
                                    </thead>
                                    <tbody id="myTable4">
                                        <tr ng-repeat="usuario in usuarios">
                                            <th scope="row">{{$index + 1}}</th>

                                            <td ng-bind="usuario.email"></td>
                                            <td ng-bind="usuario.dataCadastro"></td>
                                            <td ng-bind="usuario.ultimoAcesso"></td>
                                            <td>                                        
                                                <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalBloquear" ng-click="prepararBloqueamento(usuario.id)"><i class="fa fa-toggle-on"></i> Bloquear</a>
                                                <a class="btn btn-sm btn-warning" data-toggle="modal" data-target="#modalInativar" ng-click="prepararInativacao(usuario.id)"><i class="fa fa-toggle-on"></i> Inativar</a>
                                                <a class="btn btn-sm btn-default" data-toggle="modal" data-target="#modalVerDetalhesUsuario" ng-click="getUsuario(usuario.id)" ><i class="fa fa-user"></i> Ver Mais Detalhes</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager4"></ul>
                            </div>
                        </div><!--/.Panel 2.1 usuarios 'livres'-->         
                        <!--Panel 2.2 usuarios bloqueados -->
                        <div class="tab-pane fade" id="panel15" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>                                   
                                        </tr>
                                    </thead>
                                    <tbody id="myTable5">
                                        <tr ng-repeat="usuario in usuariosBloqueados">
                                            <th scope="row">{{$index + 1}}</th>

                                            <td ng-bind="usuario.email"></td>
                                            <td ng-bind="usuario.dataCadastro"></td>
                                            <td ng-bind="usuario.ultimoAcesso"></td> 
                                            <td>
                                                <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalDesbloquear" ng-click="prepararDesbloqueamento(usuario.id)"><i class="fa fa-toggle-on"></i> Desbloquear</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager5"></ul>
                            </div>

                        </div><!--/.Panel 2.2 usuarios bloqueados-->
                        <!--Panel 2.3 usuarios inativos-->
                        <div class="tab-pane fade" id="panel16" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>                                   
                                        </tr>
                                    </thead>
                                    <tbody id="myTable6">
                                        <tr ng-repeat="usuario in usuariosInativos">
                                            <th scope="row">{{$index + 1}}</th>

                                            <td ng-bind="usuario.email"></td>
                                            <td ng-bind="usuario.dataCadastro"></td>
                                            <td ng-bind="usuario.ultimoAcesso"></td>
                                            <td align=right>
                                                <a ng-if="!isBloqueado(usuario)" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalBloquear" ng-click="prepararBloqueamento(usuario.id)"><i class="fa fa-toggle-on"></i> Bloquear</a>
                                                <a ng-if="isBloqueado(usuario)" class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalDesbloquear" ng-click="prepararDesbloqueamento(usuario.id)"><i class="fa fa-toggle-on"></i> Desbloquear</a>
                                            </td>                                    
                                            <td align=right>
                                                <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalAtivar"><i class="fa fa-toggle-on" ng-click="prepararAtivacao(usuario.id)"></i> Ativar</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager6"></ul>
                            </div>

                        </div><!--/.Panel 2.3 usuarios inativos-->                
                    </div><!--/.div tab content -->
                </div><!--/.Panel 2 Usuario-->

                <!-- Painel 3 empresa -->
                <div class="tab-pane fade" id="panel33" role="tabpanel">
                    <br>
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs md-pills pills-ins" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#panel17" role="tab"><i class="fa fa-users animated lightSpeedIn"></i>Ativos/Desbloqueados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#panel18" role="tab"><i class="fa fa-lock animated lightSpeedIn "></i>Bloqueados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#panel19" role="tab"><i class="fa fa-remove animated lightSpeedIn "></i>Inativos</a>
                        </li>
                    </ul>
                    <!-- Tab panels -->
                    <div class="tab-content">

                        <!--/.Panel 3.1 empresas 'livres'-->
                        <div class="tab-pane fade in active" id="panel17" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>                                   
                                        </tr>
                                    </thead>
                                    <tbody id="myTable7">
                                        <tr ng-repeat="empresa in empresas">
                                            <th scope="row">{{$index + 1}}</th>

                                            <td ng-bind="empresa.email"></td>
                                            <td ng-bind="empresa.dataCadastro"></td>
                                            <td ng-bind="empresa.ultimoAcesso"></td>
                                            <td>                                        
                                                <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalBloquear" ng-click="prepararBloqueamento(empresa.id)"><i class="fa fa-toggle-on" ></i> Bloquear </a>
                                                <a class="btn btn-sm btn-warning" data-toggle="modal" data-target="#modalInativar" ng-click="prepararInativacao(empresa.id)"><i class="fa fa-toggle-on" ></i> Inativar </a>
                                                <a class="btn btn-sm btn-default" data-toggle="modal" data-target="#modalVerDetalhesEmpresa" ng-click="getEmpresa(empresa.id)" ><i class="fa fa-user"></i> Ver Mais Detalhes</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager7"></ul>
                            </div>
                        </div><!--/.Panel 3.1 empresas 'livres'-->         
                        <!--Panel 3.2 empresas bloqueados -->
                        <div class="tab-pane fade" id="panel18" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>                                   
                                        </tr>
                                    </thead>
                                    <tbody id="myTable8">
                                        <tr ng-repeat="empresa in empresasBloqueados">
                                            <th scope="row">{{$index + 1}}</th>

                                            <td ng-bind="empresa.email"></td>
                                            <td ng-bind="empresa.dataCadastro"></td>
                                            <td ng-bind="empresa.ultimoAcesso"></td>
                                            <td>                                        
                                                <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalDesbloquear" ng-click="prepararDesbloqueamento(empresa.id)"><i class="fa fa-toggle-on"></i> Desbloquear</a>                                        
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager8"></ul>
                            </div>

                        </div><!--/.Panel 3.2 empresas bloqueados-->
                        <!--Panel 3.3 empresas inativos-->
                        <div class="tab-pane fade" id="panel19" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                        <tr>
                                            <th>#</th>                            
                                            <th>Email</th>          
                                            <th>Cadastro em</th>         
                                            <th>Ultimo acesso em</th>                                   
                                        </tr>
                                    </thead>
                                    <tbody id="myTable9">
                                        <tr ng-repeat="empresa in empresasInativos">
                                            <th scope="row">{{$index + 1}}</th>

                                            <td ng-bind="empresa.email"></td>
                                            <td ng-bind="empresa.dataCadastro"></td>
                                            <td ng-bind="empresa.ultimoAcesso"></td>
                                            <td align=right>
                                                <a ng-if="!isBloqueado(empresa)" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalBloquear" ng-click="prepararBloqueamento(empresa.id)"><i class="fa fa-toggle-on"></i> Bloquear</a>
                                                <a ng-if="isBloqueado(empresa)" class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalDesbloquear" ng-click="prepararDesbloqueamento(empresa.id)"><i class="fa fa-toggle-on"></i> Desbloquear</a>
                                            </td>    
                                            <td>                                                                              
                                                <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modalAtivar" ng-click="prepararAtivacao(empresa.id)"><i class="fa fa-toggle-on"></i> Ativar</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                            <div class="col-md-12 text-center">
                                <ul class="pagination pager" id="myPager9"></ul>
                            </div>

                        </div><!--/.Panel 3.3 empresa inativos-->                
                    </div><!--/.div tab content -->
                </div><!--/.Panel 3 Empresa-->


                <!--Panel 4 Cadastro -->
                <div class="tab-pane fade" id="panel34" role="tabpanel">

                    <!--Form with header-->
                    <div class="card">
                        <div class="card-block">

                            <form>
                                <!--Body-->

                                <div class="col-md-12">

                                    <!--Card content-->
                                    <div class="card-block text-xs-center">
                                        <!--Title-->
                                        <h4 class="card-title"><i class="fa fa-lock"></i><strong>Informações Conta</strong></h4>

                                        <div class="col-md-12">
                                            <div class="md-form">  
                                                <input  type="email" id="conta-email" class="form-control" required=""
                                                        maxlength="50" ng-model="contaCadastro.email">
                                                <label for="conta-email">Email</label>
                                            </div>
                                        </div>

                                        <div class="col-md-12">
                                            <div class="md-form">                
                                                <input type="password" id="conta-senha" class="form-control" required=""
                                                       maxlength="20" ng-model="contaCadastro.senha">
                                                <label for="conta-senha">Senha</label>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="md-form">  
                                                <input  type="password" id="conta-confirma-senha" class="form-control"
                                                        required="" maxlength="20" ng-model="contaCadastro.senha2"> 
                                                <label for="conta-confirma-senha"> Confirmar a Senha </label>
                                            </div>
                                        </div>                      

                                    </div>
                                    <!--/.Card content-->

                                </div>
                                <div class="text-xs-center">
                                    <button type="submit" class="btn btn-warning" ng-click="cadastrar(contaCadastro)"> Cadastrar </button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--/Form with header-->
                </div>
                <!--/.Panel 4 cadastro-->
            </div>

            <!-- Modal Inativar -->
            <div class="modal fade" id="modalInativar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Inativar Conta</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <h3>Tem Certeza que deseja inativar essa Conta? </h3>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" data-dismiss="modal"  ng-click="inativar()"> Sim </button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarInativacao()"> Não </button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->

            <!-- Modal Ativar -->
            <div class="modal fade" id="modalAtivar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Inativar Conta</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <h3>Tem Certeza que Deseja Ativar essa Conta? </h3>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" data-dismiss="modal"  ng-click="ativar()"> Sim </button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarAtivacao()"> Não </button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->


            <!-- Modal Bloquear -->
            <div class="modal fade" id="modalBloquear" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Bloquear Conta</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <h3>Tem Certeza que deseja bloquear essa Conta? </h3>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" data-dismiss="modal" ng-click="bloquear()">Sim</button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarBloqueamento()">Não</button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->


            <!-- Modal Ver detalhes usuario -->
            <div class="modal fade" id="modalVerDetalhesUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Detalhes Usuario</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <div class="list-group lista">

                                <a href="#" class="list-group-item imagem-perfil">
                                    <img class="img-responsive img-circle"  ng-src="{{imagemUsuario}}" onerror="this.src='../img/outras/teste-perfil.jpg'">
                                </a>


                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"><i class="fa fa-user"></i>  Nome</h5>
                                    <p class="list-group-item-text" ng-bind="usuarioDetalhe.nome"> </p>
                                </a>
                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"><i class="fa fa-envelope"></i> Email </h5>
                                    <p class="list-group-item-text" ng-bind="usuarioDetalhe.conta.email">  </p>
                                </a>
                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-calendar"></i> Data de Nascimento</h5>
                                    <p class="list-group-item-text" ng-bind="usuarioDetalhe.dataNascimento">  </p>
                                </a>

                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-list-alt"></i> CPF</h5>
                                    <p class="list-group-item-text" ng-bind="usuarioDetalhe.cpf">  </p>
                                </a>

                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-phone"></i> Telefone</h5>
                                    <p class="list-group-item-text" ng-bind="usuarioDetalhe.telefone"> </p>
                                </a>
                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-phone"></i>  Ultimo Acesso</h5>
                                    <p class="list-group-item-text" ng-bind="usuarioDetalhe.conta.ultimoAcesso"> </p>
                                </a>
                                <hr/>

                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-map-marker"></i> Endereço</h5>
                                    <p class="list-group-item-text"> {{usuarioDetalhe.endereco.bairro}}, {{usuarioDetalhe.endereco.cidade.nome}} - {{usuarioDetalhe.endereco.cidade.estado.uf}} </p>
                                </a>

                            </div>

                            <div class="text-xs-center">
                                <button class="btn btn-warning" data-dismiss="modal"> Fechar </button>
                            </div>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->

            <!-- Modal Ver detalhes usuario -->
            <div class="modal fade" id="modalVerDetalhesEmpresa" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Detalhes Empresa</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <div class="list-group lista">

                                <a href="#" class="list-group-item imagem-perfil">
                                    <img class="img-responsive img-circle" ng-src="{{imagemEmpresa}}" onerror="this.src='../img/outras/teste-perfil.jpg'">
                                </a>

                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"><i class="fa fa-building-o"></i> Razão Social</h5>
                                    <p class="list-group-item-text" ng-bind="empresaDetalhe.razaoSocial">  </p>
                                </a>
                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"><i class="fa fa-building"></i> Nome Fantasia</h5>
                                    <p class="list-group-item-text" ng-bind="empresaDetalhe.nomeFantasia">  </p>
                                </a>
                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"><i class="fa fa-envelope"></i> Email </h5>
                                    <p class="list-group-item-text" ng-bind="empresaDetalhe.conta.email">  </p>
                                </a>
                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-list-alt"></i> CNPJ</h5>
                                    <p class="list-group-item-text" ng-bind="empresaDetalhe.cnpj">  </p>
                                </a>

                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-phone-square"></i> Telefone 1</h5>
                                    <p class="list-group-item-text" ng-bind="empresaDetalhe.telefone">  </p>
                                </a>

                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-phone"></i> Telefone 2</h5>
                                    <p class="list-group-item-text" ng-bind="empresaDetalhe.telefone2">  </p>
                                </a>

                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-calendar"></i> Ultimo Acesso</h5>
                                    <p class="list-group-item-text" ng-bind="empresaDetalhe.conta.ultimoAcesso">  </p>
                                </a>

                                <hr/>

                                <a href="#" class="list-group-item">
                                    <h5 class="list-group-item-heading"> <i class="fa fa-map-marker"></i> Endereço</h5>
                                    <p class="list-group-item-text"> {{empresaDetalhe.endereco.bairro}}, {{empresaDetalhe.endereco.cidade.nome}} - {{empresaDetalhe.endereco.cidade.estado.uf}} </p>
                                </a>

                            </div>

                            <div class="text-xs-center">
                                <button class="btn btn-warning" data-dismiss="modal"> Fechar </button>
                            </div>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->

            <!-- Modal Desbloquear -->
            <div class="modal fade" id="modalDesbloquear" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Desbloquear Conta</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <h3>Tem Certeza que Deseja Desbloquear essa Conta? </h3>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" data-dismiss="modal" ng-click="desbloquear()">Sim</button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarDesbloqueamento()">Não</button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->

            <div class="espaco"></div>
            <!-- /. fim do html-->
        </div>
    </div>

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
    <script type="text/javascript" src="../js/outros/tabela-responsiva.js"></script>

    <!-- link Angular -->
    <script src="../lib/angular/angular-animate.js" type="text/javascript"></script>

    <!-- link Angular -->
    <link href="../css/angular-toastr.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../lib/angular/angular-toastr.tpls.js"></script>

    <!-- aside -->
    <script src="../js/outros/aside.js" type="text/javascript"></script>
</body>

</html>
