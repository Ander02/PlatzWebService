<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Pagina de cadastro , consulta, atualização e exclusão de assunto de mensagem
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

        <!-- styles GLOBAL -->
        <link href="../css/style.css" rel="stylesheet">

        <!-- styles ADMINISTRADOR -->
        <link href="../css/styleAdm.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- link Angular APP-->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- link Angular UTIL -->
        <script src="../js/util.js" type="text/javascript"></script>

        <!-- link CONTROLLER -->
        <script src="../js/controller/loginController.js" type="text/javascript"></script>

        <script src="../js/controller/assuntoController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">
    </head>

    <body>
        <!-- inicio do projeto aqui-->
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
    <ng-include src="'../View/nav-adm.html'"  ng-controller="loginController"></ng-include>
    
    <div class="espaco"></div>
    
    <div ng-controller="assuntoController">
        <div ng-if="permicao">

            <div class="head-pagina">
                <h1>Assunto</h1>
            </div>

            <!-- Nav tabs -->
            <ul class="nav nav-tabs md-pills pills-default nav-tab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#panel31" role="tab"><i class="fa fa-list-ul "></i>Consultar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel32" role="tab"><i class="fa fa-plus"></i>Cadastrar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel33" role="tab"><i class="fa fa-trash"></i>Excluidos</a>
                </li>

            </ul>

            <!-- Tab panels -->
            <div class="tab-content conteudo-tab">

                <!--Panel 1 consulta-->
                <div class="tab-pane fade in active" id="panel31" role="tabpanel">
                    <br>

                    <div class="table-responsive ">
                        <table class="table table-hover table-striped  ">
                            <thead>
                                <tr>
                                    <th>#</th>                            
                                    <th>Nome</th>                                                 
                                    <th>Data de cadastro</th>      

                                </tr>
                            </thead>
                            <tbody id="myTable2">
                                <tr ng-repeat="assunto in assuntos">
                                    <th scope="row">{{$index + 1}}</th>

                                    <td>{{assunto.nome}}</td>  
                                    <td>{{assunto.dataCadastro}}</td>  
                                    <td  align=right>
                                        <a class="btn btn-sm btn-default" data-toggle="modal" data-target="#modalEdita" ng-click="prepararEdicao(assunto)"><i class="fa fa-pencil"></i> Editar</a>
                                        <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalExcluir" ng-click="prepararExclusao(assunto.id)"><i class="fa fa-trash"></i>Excluir</a>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-12 text-center">
                        <ul class="pagination  pager" id="myPager2"></ul>
                    </div>

                </div>
                <!--/.Panel 1-->

                <!--Panel 2 cadastro -->
                <div class="tab-pane fade" id="panel32" role="tabpanel">

                    <!--Form with header-->
                    <div class="card">
                        <div class="card-block">
                            <form>
                                <!--Body-->
                                <div class="md-form form-cad-input">                  
                                    <input type="text" id="assunto-nome" class="form-control"  ng-model="assuntoCadastro.nome" required maxlength="35">
                                    <label for="assunto-nome" >Nome do Assunto</label>
                                </div>                       

                                <div class="text-xs-center">
                                    <button type="submit" class="btn btn-warning" ng-click="cadastrar()">Cadastrar</button>
                                </div>
                            </form>

                        </div>


                    </div>
                    <!--/Form with header-->

                </div>
                <!--/.Panel 2-->

                <!-- Painel 3 painel da lixeira-->
                <div class="tab-pane fade" id="panel33" role="tabpanel">

                    <br>

                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>                            
                                    <th>Nome</th>                                                 
                                    <th>Data de exclusão</th>                           
                                </tr>
                            </thead>
                            <tbody id="myTable3">
                                <tr ng-repeat=" assunto in assuntosDeletados">
                                    <th scope="row">{{$index + 1}}</th>

                                    <td>{{assunto.nome}}</td>                                                   
                                    <td>{{assunto.deletado}}</td>   
                                    <td align=right >                          
                                        <a class="btn btn-sm btn-default-outline" data-toggle="modal" data-target="#modalRestaurar" ng-click="prepararRecuperacao(assunto.id)"><i class="fa fa-undo"></i> Restaurar </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-12 text-center">
                        <ul class="pagination  pager" id="myPager3"></ul>
                    </div>

                </div>
                <!-- /.Painel 3 painel da lixeira-->



            </div>

            <!-- Modal de edição -->
            <div class="modal fade" id="modalEdita" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Editar Assunto</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <div class="md-form form-cad-input">                  
                                    <input type="text" id="assunto-nome-edicao" class="form-control" ng-model="assuntoEditado.nome" ng-value="assuntoEdicao.nome" required maxlength="35">
                                    <label for="assunto-nome-edicao">Nome do Assunto</label>
                                </div>


                                <div class="text-xs-center">
                                    <button type="submit" class="btn btn-warning" data-dismiss="modal" ng-click="alterar()"> Editar </button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarEdicao()"> Fechar </button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->


            <!-- Modal Excluir -->
            <div class="modal fade" id="modalExcluir" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Excluir Assunto</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <h3>Tem Certeza que deseja excluir esse Assunto? </h3>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" ng-click="deletar()" data-dismiss="modal"> Sim </button>
                                    <button type="button" class="btn btn-warning" ng-click="cancelarExclusao()" data-dismiss="modal"> Não </button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->

            <!-- Modal Restaurar -->
            <div class="modal fade" id="modalRestaurar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Restaurar Assunto</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <h3>Tem Certeza que deseja restaurar esse Assunto? </h3>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning"  data-dismiss="modal" ng-click="recuperar()"> Sim </button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarRecuperacao()"> Não </button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->
            <div class="espaco"></div>
            <!-- /fim do html do projeto-->
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

    <!-- link Angular ANIMATE -->
    <script src="../lib/angular/angular-animate.js" type="text/javascript"></script>

    <!-- link Angular CSS TOASTR -->
    <link href="../css/angular-toastr.css" rel="stylesheet" type="text/css"/>

    <!-- link Angular ANIMATE -->
    <script type="text/javascript" src="../lib/angular/angular-toastr.tpls.js"></script>

    <!-- aside -->
    <script src="../js/outros/aside.js" type="text/javascript"></script>
</body>

</html>

