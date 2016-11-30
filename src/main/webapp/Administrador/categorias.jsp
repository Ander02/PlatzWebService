<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Pagina de Cadastro, consulta , atualização e exclusão de Categorias dos eventos
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

        <!-- angular app script -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- UTIL -->
        <script src="../js/util.js" type="text/javascript"></script> 

        <script src="../js/services/loginService.js" type="text/javascript"></script>
        
        <script src="../js/services/validacaoService.js" type="text/javascript"></script>
        
        <!-- angular controller script -->
        <script src="../js/controller/loginController.js" type="text/javascript"></script>

        <script src="../js/controller/categoriaController.js" type="text/javascript"></script>

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
    <ng-include src="'../View/nav-adm.html'"  ng-controller="loginController"></ng-include>

    <div  ng-controller="categoriaController">
        <div ng-if="permicao">

            <div class="espaco"></div>
            <div class="head-pagina">
                <h1>Categoria dos eventos</h1>
            </div>

            <!-- Nav tabs -->
            <ul class="nav nav-tabs md-pills pills-default nav-tab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#panel31" role="tab"><i class="fa fa-list-ul"></i> Consultar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel32" role="tab"><i class="fa fa-plus"></i> Cadastrar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#panel33" role="tab"><i class="fa fa-trash"></i> Excluidos</a>
                </li>

            </ul>

            <!-- Tab panels -->
            <div class="tab-content conteudo-tab">

                <!--Panel 1 consulta-->
                <div class="tab-pane fade in active" id="panel31" role="tabpanel">
                    <br>

                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Imagem</th>     
                                    <th>Nome</th>
                                    <th>Data de cadastro</th>

                                </tr>
                            </thead>
                            <tbody id="myTable">                    
                                <tr ng-repeat="categoria in categorias">
                                    <th scope="row">{{$index + 1}}</th>
                                    <td>
                                        <div >
                                            <img ng-src="{{baixarImagem(categoria.id)}}" alt="Imagem sobre a categoria" class="img-categoria">                                  
                                        </div>
                                    </td>    
                                    <td>{{categoria.nome}}</td>   
                                    <td>{{categoria.dataCadastro}}</td>
                                    <td align=right>
                                        <a class="btn btn-sm btn-default" data-toggle="modal" ng-click="prepararEdicao(categoria)" data-target="#modalEdita"><i class="fa fa-pencil"></i>Editar</a>
                                        <a class="btn btn-sm btn-amber" data-toggle="modal" ng-click="prepararEdicaoImagem(categoria.id)" data-target="#modalEditaImagem"><i class="fa fa-camera animated flash"></i>Trocar imagem </a>
                                        <a class="btn btn-sm btn-danger" data-toggle="modal" ng-click="prepararExclusao(categoria.id)" data-target="#modalExcluir"><i class="fa fa-trash"></i>Excluir</a>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-12 text-center">
                        <ul class="pagination  pager" id="myPager"></ul>
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
                                    <input type="text" id="categoria-nome" class="form-control" required maxlength="35" ng-model="cadastroCategoria.nome">
                                    <label for="categoria-nome"> Nome da Categoria </label>
                                </div>

                                <div class="md-form form-cad-input">
                                    <input type="file" id="InputIconeCategoriaCadastro" />
                                </div>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" ng-click="cadastrar(cadastroCategoria)">Cadastrar</button>
                                </div>
                            </form>

                        </div>


                    </div>
                    <!--/Form with header-->

                </div>
                <!--/.Panel 2-->

                <!-- Painel 3 -->
                <div class="tab-pane fade" id="panel33" role="tabpanel">
                    <br>

                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Imagem</th>     
                                    <th>Nome</th>
                                    <th>Data de exclusão</th>
                                </tr>
                            </thead>
                            <tbody id="myTable">                    
                                <tr ng-repeat="categoria in categoriasExcluidas">
                                    <th scope="row">{{$index + 1}}</th>
                                    <td>
                                        <div >
                                            <img src="{{baixarImagem(categoria.id)}}" alt="Imagem sobre a categoria" class="img-categoria">                                  
                                        </div>
                                    </td>    
                                    <td>{{categoria.nome}}</td>   
                                    <td>{{categoria.deletado}}</td>
                                    <td align=right >
                                        <a class="btn btn-sm btn-default-outline" data-toggle="modal" data-target="#modalRestaurar" ng-click="prepararRecuperacao(categoria.id)"><i class="fa fa-undo"></i> Restaurar</a>                           
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-12 text-center">
                        <ul class="pagination  pager" id="myPager"></ul>
                    </div>

                </div>
                <!-- /.painel 3 -->

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
                            <h4 class="modal-title" id="myModalLabel">Editar Categoria</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <div class="md-form form-cad-input">                  
                                    <input type="text" id="categoria-edicao" class="form-control"  required maxlength="35" ng-value="categoriaEdicao.nome" ng-model="categoriaEditada.nome">
                                    <label for="categoria-edicao">Nome da Categoria</label>
                                </div>


                                <div class="text-xs-center">
                                    <button class="btn btn-warning" data-dismiss="modal" ng-click="alterar(categoriaEditada)">Editar</button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarEdicao()">Fechar</button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->

            <!-- Modal de edição de imagem -->
            <div class="modal fade" id="modalEditaImagem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Editar Imagem da Categoria</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->

                                <div class="md-form form-cad-input">   
                                    <input id="InputIconeCategoriaEdicao" type="file" /> 
                                </div>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" data-dismiss="modal" ng-click="alterarImagem()"> Alterar Imagem </button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarEdicaoImagem()"> Fechar </button>
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
                            <h4 class="modal-title" id="myModalLabel">Excluir Categoria</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <h3>Tem Certeza que deseja excluir essa categoria? </h3>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" data-dismiss="modal"  ng-click="deletar()">Sim</button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarExclusao()">Não</button>
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
                            <h4 class="modal-title" id="myModalLabel">Restaurar Categoria</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <!--Body-->
                                <h3>Tem Certeza que deseja Restaurar essa categoria? </h3>

                                <div class="text-xs-center">
                                    <button class="btn btn-warning" ng-click="recuperar()">Sim</button>
                                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="cancelarRecuperacao()">Não</button>
                                </div>
                            </form>
                        </div>

                    </div>
                    <!--/.Content-->
                </div>
            </div> <!--/.modal-->
        </div>
    </div>
    <div class="espaco"></div>

    <!-- /Fim  do html do projeto-->


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
