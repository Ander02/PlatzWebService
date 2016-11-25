<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Pagina de edição de eventos, onde sera possivel cancelar os eventos e editar suas informações
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

        <script src="../js/services/loginService.js" type="text/javascript"></script>
        
        <script src="../js/services/validacaoService.js" type="text/javascript"></script>

        <!-- link util -->
        <script src="../js/util.js" type="text/javascript"></script>
        <!-- Link Controller -->
        <script src="../js/controller/editarEventoController.js" type="text/javascript"></script>
        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

    </head>

    <body ng-controller="editarEventoController">
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

            String evento = request.getParameter("evento");

            out.print("<input type='hidden' id='idEvento' name='idEvento' value ='" + evento + "' >");
        %>

    <ng-include src="'../View/nav-empresa.html'"></ng-include>
    <div class="espaco"></div>

    <div class="head-pagina">
        <h1 class="titulo-meusEventos">Editar Evento</h1>        
    </div>
    <!--Form de cadastro de evento-->
    <div>
        <form>

            <div class="col-md-8 corpo-form-cadastro-evento edicao-evento card">

                <div class="col-md-12">

                    <!--Card content-->
                    <div class="card-block text-xs-center">
                        <!--Title-->
                        <h4 class="card-title"><i class="fa fa-calendar-check-o animated rotateIn"></i><strong> Evento</strong></h4>

                        <div class="col-md-12">
                            <div class="md-form">
                                <p ng-if="evento.cancelado !== undefined"> Evento Cancelado, para editar sua informações Ative-o </p>
                                <button class="btn btn-amber" ng-click="cancelar_Ativar()"><i class="fa fa-check-square-o"></i>{{evento.cancelado !== undefined?'Reativar o Evento':'Cancelar o Evento'}}</button>
                            </div>
                        </div>

                        <div ng-show="evento.cancelado === undefined">
                            <div class="col-md-12">
                                <label for="cadastro-evento-img" class="label-modificado-imagens">Imagem de Capa</label>
                                <div class="md-form">                
                                    <input id="edicao-evento-img-capa" type="file" >
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="md-form">  
                                    <input  type="text" id="evento-nome" class="form-control" required ng-model="eventoEdicao.nome"
                                            maxlength="75">
                                    <label for="evento-nome">Nome do Evento</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="md-form">                
                                    <textarea id="evento-descricao" class="md-textarea" ng-model="eventoEdicao.detalhes"></textarea>
                                    <label for="evento-descricao">Descrição</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">                
                                    <input type="text" id="evento-lotacao-minima" class="form-control" ng-model="eventoEdicao.lotacaoMin">
                                    <label for="evento-lotacao-minima">Lotação Minima</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="md-form">  
                                    <input type="text" id="evento-lotacao-maxima" class="form-control" ng-model="eventoEdicao.lotacaoMax">
                                    <label for="evento-lotacao-maxima">Lotação Maxima</label>
                                </div>
                            </div>
                            <div class="col-md-6">

                                <div class="col-md-12">
                                    <div class="form-control-wrapper">
                                        <input type="text" id="datetimepicker-start-edit" placeholder="YYYY-MM-DD HH:MM:SS" class="linecons-calendar" ng-value='eventoEdicao.dataInicio'/>
                                    </div>
                                </div>
                            </div> 
                            <div class="col-md-6">                        
                                <div class="col-md-12">
                                    <div class="form-control-wrapper">
                                        <input type="text" id="datetimepicker-end-edit" placeholder="YYYY-MM-DD HH:MM:SS" class="linecons-calendar"  ng-value='eventoEdicao.dataFim'/>
                                        <p id="p-alerta-edit" style="display: none;">Corrigir data, data de termino deve ser depois da data atual</p>
                                    </div>
                                </div>
                            </div>                  
                            <div class="col-md-4">
                                <div class="md-form">                        
                                    <input type="text" id="evento-idade-minima" class="form-control" ng-model="eventoEdicao.idade">
                                    <label for="evento-idade-minima">Idade Minima</label>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="md-form">   
                                    <section title=".squaredFour">
                                        <!-- .squaredFour -->
                                        <div class="squaredFour">
                                            <input type="checkbox" value="None" id="evento-gratuito" name="check"  />

                                        </div>
                                        <!-- end .squaredFour -->
                                    </section>

                                    <label for="evento-gratuito">Evento Gratuito</label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="md-form">                        
                                    <input type="text"  id="evento-preco" class="form-control" ng-model="eventoEdicao.preco">
                                    <label for="evento-preco">Preço</label>
                                </div>
                            </div>
<!--
                            <div class="col-md-12">
                                <label for="cadastro-evento-imagem-galeria" class="label-modificado-imagens"> Galeria de Imagens sobre o evento, local ..</label>
                                <div class="md-form">                
                                    <input id="cadastro-evento-imagem-galeria" type="file" multiple >
                                </div>
                            </div>-->
                        </div>
                    </div>
                    <!--/.Card content-->

                </div>

                <div class="col-md-12">
                    <div ng-show="evento.cancelado === undefined">

                        <!--Card content-->
                        <div class="card-block text-xs-center">
                            <!--Title-->
                            <h4 class="card-title"><i class="fa fa-map-marker animated rotateIn"></i><strong> Endereço</strong></h4>
                            <div class="col-md-4">
                                <div class="md-form col-md-8">                
                                    <input type="text" id="evento-cep" class="form-control"  ng-model="eventoEdicao.endereco.cep" ng-blur="onblurCepEvento()">
                                    <label for="evento-cep">CEP</label>
                                </div>
                                <div class="col-md-4 link-cep"><a title="clique aqui e saiba seu cep" class="text-info">Não Sei meu cep</a></div>
                            </div>
                            <div class="col-md-4">
                                <div class="md-form">  
                                    <input  type="text" id="evento-numero" class="form-control" ng-model="eventoEdicao.endereco.numero">
                                    <label for="evento-numero">Número</label>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="md-form">                
                                    <input type="text" id="evento-complemento" class="form-control" ng-model="eventoEdicao.endereco.complemento">
                                    <label for="evento-complemento">Complemento</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <label for="evento-rua" class="label-form">Rua</label>
                                <p>{{eventoEdicao.endereco.rua}}</p>
                                <hr>
                            </div>

                            <div class="col-md-12">
                                <label for="evento-bairro" class="label-form">Bairro</label>
                                <p>{{eventoEdicao.endereco.bairro}}</p>   
                                <hr>
                            </div>

                            <div class="col-md-12 ">
                                <label for="evento-cidade" class="label-form">Cidade</label>
                                <p>{{ eventoEdicao.endereco.cidade}}</p>   
                                <hr>
                            </div> 


                            <div class="col-md-12">
                                <button class="btn btn-lg btn-warning" ng-click="editar(eventoEdicao)"><i class="fa fa-check"></i> Editar </button>
                            </div>

                        </div>
                        <!--/.Card content-->

                    </div>
                </div>



            </div>
        </form>
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

    <!-- Desabilitar o preço -->
    <script src="../js/outros/desabilitarPreco.js" type="text/javascript"></script>

    <script src="../lib/jquery/jquery.mask.min.js" type="text/javascript"></script>

    <link href="../css/bootstrap/isteven-multi-select.css" rel="stylesheet" type="text/css"/>

    <script src="../lib/angular/isteven-multi-select.js" type="text/javascript"></script>

    <script src="../js/outros/aside.js" type="text/javascript"></script>

    <link href="../lib/jquery/jquery.datetimepicker.min.css" rel="stylesheet" type="text/css"/>

    <script src="../lib/jquery/jquery.datetimepicker.full.js" type="text/javascript"></script>

    <script src="../js/outros/datetimepickerJS.js" type="text/javascript"></script>


</body>

</html>
