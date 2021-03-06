<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Pagina de consulta de cidade e estados
-->
<html lang="pt-br" ng-app="platz" >
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

        <script src="../js/controller/cidadeController.js" type="text/javascript"></script>
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
    <ng-include src="'../View/nav-adm.html'"  ng-controller="loginController"></ng-include>
    <div  ng-controller="cidadeController">
        <div class="espaco"></div>


        <div class="head-pagina">

            <h1>Cidades</h1>
            <p>A Aplicação cadastra novas cidades automaticamente, de acordo com o cadastro de contas e eventos.</p>

        </div>

        <ul class="nav nav-tabs md-pills pills-ins nav-tab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#panel11" role="tab"><i class="fa fa-list-ul"></i>Consulta</a>
            </li>
        </ul>


        <div class="tab-content conteudo-tab" ng-if="permicao">

            <!--Panel 1 caixa de entrada de mensagens-->
            <div class="tab-pane fade in active" id="panel11" role="tabpanel">
                <br>

                <div class="table-responsive">
                    <table class="table table-hover table-striped ">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Cidade</th>
                                <th>Estado</th>                            
                                <th>Data de Cadastro</th>
                            </tr>
                        </thead>
                        <tbody id="myTable">
                            <tr ng-repeat="cidade in cidades">
                                <td>{{$index + 1}}</td>
                                <td>{{cidade.nome}}</td>                            
                                <td>{{cidade.estado.nome}} - {{cidade.estado.uf}}</td>                            
                                <td>{{cidade.dataCadastro}}</td>
                            </tr>

                        </tbody>
                    </table>

                </div><!-- fim da div table-responsive -->
                <div class="col-md-12 text-center">
                    <ul class="pagination pager" id="myPager"></ul>
                </div>

            </div>
            <!--/.Panel 1-->
        </div>

        <div class="espaco"></div>
    </div>
    <!-- /Fim do Projeto aqui-->


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

