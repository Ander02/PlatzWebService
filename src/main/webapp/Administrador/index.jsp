<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Pagina index do administrador, quando ele se logar vem direto para esta pagina
porem quando ele tiver navegando pelas paginas , ela vai ter como "nome" pagina de mensagem
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
        <link href="../css/styleAdm.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- angular util -->
        <script src="../js/util.js" type="text/javascript"></script>

        <!-- angular controller  -->
        <script src="../js/controller/mensagemController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

        <link href="../css/efeitos/efeitoEstrelaFavorita.css" rel="stylesheet" type="text/css"/>

    </head>

    <body ng-controller="mensagemController">


        <!-- inicio do projeto aqui-->

    <ng-include src="'../View/nav-adm.html'"></ng-include>

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

    <div class="espaco"></div>

    <div class="head-pagina">
        <h1>Mensagens</h1>
    </div>


    <!-- Nav tabs -->
    <ul class="nav nav-tabs md-pills pills-ins nav-tab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#panel11" role="tab"><i class="fa fa-list-ul"></i> Inicio</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel12" role="tab"><i class="fa fa-star-o"></i> Favoritas</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel13" role="tab"><i class="fa fa-envelope-o"></i>Mensagens Lidas</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel14" role="tab"><i class="fa fa-envelope"></i>Mensagens Não Lidas</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel15" role="tab"><i class="fa fa-trash"></i> Excluidas</a>
        </li>
    </ul>

    <!-- Tab panels -->
    <div class="tab-content conteudo-tab">

        <!--Panel 1 caixa de entrada de mensagens-->
        <div class="tab-pane fade in active" id="panel11" role="tabpanel">
            <br>

            <div class="table-responsive">
                <table class="table table-hover table-striped ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Favoritar</th>
                            <th>Email</th>
                            <th>Enviado em</th>                            
                            <th>Assunto</th>                            
                            <th>Excluir</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="myTable">
                        <tr ng-repeat="mensagem in mensagens">
                            <td>{{$index + 1}}</td>
                            <td>
                                <input type="checkbox" name="checkboxG{{$index + 1}}" id="checkboxG{{$index + 1}}"  class="css-checkbox" ng-checked="{{mensagem.marcado}}" ng-click="alterarFavorito(mensagem)"/>
                                <label for="checkboxG{{$index + 1}}" class="css-label"></label>
                            </td>
                            <td>{{mensagem.email}}</td>
                            <td>{{mensagem.dataCadastro}}</td>
                            <td>{{mensagem.assunto.nome}}</td>                            
                            <td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalExcluir" ng-click="prepararExclusao(mensagem.id)">
                                    <i class="fa fa-trash"></i>Excluir</button></td>
                            <td ><button type="button" class="btn btn-warning btn-md" data-toggle="modal" data-target="#modalDetalhes"  ng-click="lerMensagem(mensagem.id)">
                                    Ler Mensagem
                                </button></td>
                        </tr>

                    </tbody>
                </table>

            </div><!-- fim da div table-responsive -->
            <div class="col-md-12 text-center">
                <ul class="pagination pager" id="myPager"></ul>
            </div>

        </div>
        <!--/.Panel 1-->

        <!--Panel 2 mensagens favoritas -->
        <div class="tab-pane fade" id="panel12" role="tabpanel">
            <br>

            <div class="table-responsive">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Favoritar</th>
                            <th>Email</th>
                            <th>Enviado em</th>                            
                            <th>Assunto</th>                            
                            <th>Excluir</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="myTable2">
                        <tr ng-repeat="mensagem in mensagemFavoritas">
                            <td>{{$index + 1}}</td>
                            <td>
                                <input type="checkbox" name="checkboxF{{$index + 1}}" id="checkboxF{{$index + 1}}"  class="css-checkbox" ng-checked="{{mensagem.marcado}}" ng-click="alterarFavorito(mensagem)"/>
                                <label for="checkboxF{{$index + 1}}" class="css-label"></label>
                            </td>

                            <td>{{mensagem.email}}</td>
                            <td>{{mensagem.dataCadastro}}</td>
                            <td>{{mensagem.assunto.nome}}</td>
                            <td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalExcluir" >
                                    <i class="fa fa-trash"></i>Excluir</button></td>
                            <td><button type="button" class="btn btn-warning btn-md" data-toggle="modal" data-target="#modalDetalhes" ng-click="lerMensagem(mensagem.id)">
                                    Ler Mensagem
                                </button></td>
                        </tr>                               
                    </tbody>
                </table>

            </div><!-- fim da div table-responsive -->
            <div class="col-md-12 text-center">
                <ul class="pagination pager" id="myPager2"></ul>
            </div>


        </div>
        <!--/.Panel 2-->

        <!--Panel 3 mensagens lidas-->
        <div class="tab-pane fade" id="panel13" role="tabpanel">
            <br>

            <div class="table-responsive">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Favoritar</th>
                            <th>Email</th>
                            <th>Enviado em</th>                            
                            <th>Assunto</th>                            
                            <th>Excluir</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="myTable2">
                        <tr ng-repeat="mensagem in mensagensLidas">
                            <td>{{$index + 1}}</td>
                            <td>
                                <input type="checkbox" name="checkboxE{{$index + 1}}" id="checkboxE{{$index + 1}}"  class="css-checkbox" ng-checked="{{mensagem.marcado}}" ng-click="alterarFavorito(mensagem)"/>
                                <label for="checkboxE{{$index + 1}}" class="css-label"></label>
                            </td>
                            <td>{{mensagem.email}}</td>
                            <td>{{mensagem.dataCadastro}}</td>
                            <td>{{mensagem.assunto.nome}}</td>

                            <td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalExcluir" >
                                    <i class="fa fa-trash"></i>Excluir</button></td>
                            <td><button type="button" class="btn btn-warning btn-md" data-toggle="modal" data-target="#modalDetalhes"  ng-click="lerMensagem(mensagem.id)">
                                    Ler Mensagem
                                </button></td>
                        </tr>                              
                    </tbody>
                </table>

            </div><!-- fim da div table-responsive -->
            <div class="col-md-12 text-center">
                <ul class="pagination pager" id="myPager2"></ul>
            </div>


        </div>
        <!--/.Panel 3-->

        <!--Panel 4 mensagens não lidas-->
        <div class="tab-pane fade" id="panel14" role="tabpanel">
            <br>

            <div class="table-responsive">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Favoritar</th>
                            <th>Email</th>
                            <th>Enviado em</th>                            
                            <th>Assunto</th>                            
                            <th>Excluir</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="myTable2">
                        <tr ng-repeat="mensagem in mensagensNaoLidas">
                            <td>{{$index + 1}}</td>
                            <td>
                                <input type="checkbox" name="checkboxD{{$index + 1}}" id="checkboxD{{$index + 1}}"  class="css-checkbox" ng-checked="{{mensagem.marcado}}" ng-click="alterarFavorito(mensagem)"/>
                                <label for="checkboxD{{$index + 1}}" class="css-label"></label>
                            </td>
                            <td>{{mensagem.email}}</td>
                            <td>{{mensagem.dataCadastro}}</td>
                            <td>{{mensagem.assunto.nome}}</td>

                            <td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modalExcluir">
                                    <i class="fa fa-trash"></i>Excluir</button></td>
                            <td><button type="button" class="btn btn-warning btn-md" data-toggle="modal" data-target="#modalDetalhes" ng-click="lerMensagem(mensagem.id)">
                                    Ler Mensagem
                                </button></td>
                        </tr>                             
                    </tbody>
                </table>

            </div><!-- fim da div table-responsive -->
            <div class="col-md-12 text-center">
                <ul class="pagination pager" id="myPager2"></ul>
            </div>


        </div>
        <!--/.Panel 4-->

        <!--Panel 5 lixeira -->
        <div class="tab-pane fade" id="panel15" role="tabpanel">
            <br>

            <div class="table-responsive">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Email</th>                            
                            <th>Enviado em</th>  
                            <th>Excluido em</th> 
                            <th>Assunto</th>
                            <th>Opções</th>                            
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="myTable3">
                        <tr ng-repeat="mensagemExcluida in mensagensExcluidas">
                            <td>{{$index + 1}}</td>
                            <td>{{mensagemExcluida.email}}</td>
                            <td>{{mensagemExcluida.dataCadastro}}</td>
                            <td>{{mensagemExcluida.deletado}}</td>
                            <td>{{mensagemExcluida.assunto.nome}}</td>  
                            <td><button data-toggle="modal" data-target="#modalExcluirDefinitivo" class="btn btn-sm btn-danger" ng-click="prepararExclusaoDefinitiva(mensagemExcluida.id)"><i class="fa fa-trash"></i>Apagar</button>
                                <button data-toggle="modal" data-target="#modalRestaurar" class="btn btn-sm btn-default" ng-click="prepararRecuperacao(mensagemExcluida.id)"><i class="fa fa-undo"></i>Restaurar</button></td>                            
                        </tr>                        
                    </tbody>
                </table>

            </div><!-- fim da div table-responsive -->
            <div class="col-md-12 text-center">
                <ul class="pagination pager" id="myPager3"></ul>
            </div>

        </div>
        <!--/.Panel 3-->

    </div>

    <!-- Modal para ver detalhes das mensagens -->
    <div class="modal fade" id="modalDetalhes" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <!--Content-->
            <div class="modal-content">
                <!--Header-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">Mensagem</h4>
                </div>
                <!--Body-->
                <div class="modal-body">

                    <div class="col-md-12" >
                        <div class="form-group">
                            <label for="message-text" class="form-control-label label-modal-mensagem">Email:</label>
                            <p>{{mensagem.email}}</p>                            
                        </div>
                    </div>
                    <div class="col-md-6" >
                        <div class="form-group">
                            <label for="message-text" class="form-control-label label-modal-mensagem">Data:</label>
                            <p>{{mensagem.dataCadastro}}</p>
                        </div>
                    </div>
                    <div class="col-md-6" >
                        <div class="form-group">
                            <label for="message-text" class="form-control-label label-modal-mensagem">Assunto:</label>
                            <p>{{mensagem.assunto.nome}}</p>                            
                        </div>
                    </div>
                    <div class="col-md-12" >
                        <div class="form-group">
                            <label for="message-text" class="form-control-label label-modal-mensagem">Mensagem:</label>
                            <p>{{mensagem.conteudo}}</p>                            
                        </div>
                    </div>
                    <div class="col-md-12" >
                        <div class="form-group">                        
                            <label for="message-text" class="form-control-label label-modal-mensagem">Envie um e-mail de resposta:</label>
                            <textarea class="form-control" id="message-text" ng-model="resposta"> 
                            </textarea>
                        </div>
                    </div>
                    <!--Footer-->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                        <button type="button" class="btn btn-warning" ng-click="responder(mensagem.id)">Responder</button>
                    </div>
                </div>
                <!--/.Content-->
            </div>
        </div>
        <!-- /.Live preview-->
    </div> 
    <!-- /.modal para ver detalhes mensagens-->

    <!-- modal de excluir -->
    <div class="modal fade" id="modalExcluir" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <!--Content-->
            <div class="modal-content">
                <!--Header-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">Excluir</h4>
                </div>
                <!--Body-->
                <div class="modal-body">
                    <p>tem Certeza que deseja Excluir essa Mensagem?</p>
                </div>
                <!--Footer-->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="marcarExcluida()"> Sim </button>
                    <button type="button" class="btn btn-amber" data-dismiss="modal" ng-click="cancelarExclusao()"> Não </button>
                </div>
            </div>
            <!--/.Content-->
        </div>
    </div>
    <!-- /.modal de excluir -->

    <!-- modal de restaurar -->

    <div class="modal fade" id="modalRestaurar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <!--Content-->
            <div class="modal-content">
                <!--Header-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">Restaurar</h4>
                </div>
                <!--Body-->
                <div class="modal-body">
                    <p>tem Certeza que deseja Restaurar essa Mensagem?</p>
                </div>
                <!--Footer-->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="restaurar()"> Sim </button>
                    <button type="button" class="btn btn-amber" data-dismiss="modal" ng-click="cancelarRecuperacao()"> Não </button>
                </div>
            </div>
            <!--/.Content-->
        </div>
    </div>
    <!-- /.modal de restaurar -->

    <!-- modal de excluir definitivamente -->

    <div class="modal fade" id="modalExcluirDefinitivo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <!--Content-->
            <div class="modal-content">
                <!--Header-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">Excluir Definitivamente</h4>
                </div>
                <!--Body-->
                <div class="modal-body">
                    <p>tem Certeza que Deseja Excluir essa Mensagem <strong>Definitivamente</strong>?</p>
                </div>
                <!--Footer-->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"  ng-click="excluirDefinitivamente()"> Sim </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" ng-click="cancelarExclusaoDefinitiva()"> Não </button>
                </div>
            </div>
            <!--/.Content-->
        </div>
    </div>
    <!-- /.modal de excluir definitivamente -->







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