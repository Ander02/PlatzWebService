<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Tela de cadastro de evento
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
        <script type="text/javascript" src="../lib/jquery/jquery-2.2.3.min.js"></script>

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

        <script src="../js/services/loginService.js" type="text/javascript"></script>

        <!-- Link Controller -->
        <script src="../js/controller/eventoCadastroController.js"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

        <!--links para o funcionamento do datetimepicker-->


    </head>

    <body ng-controller="eventoCadastroController">
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

    <ng-include src="'../View/nav-empresa.html'"></ng-include>
    <div class="espaco"></div>
    <div class="head-pagina">
        <h1 class="titulo-meusEventos">Cadastro de Evento</h1>        
    </div>
    <!--Form de cadastro de evento-->
    <div>
        <form >

            <div class="col-md-8 corpo-form-cadastro-evento card">

                <div class="col-md-12">

                    <!--Card content-->
                    <div class="card-block text-xs-center">
                        <!--Title-->
                        <h4 class="card-title"><i class="fa fa-calendar-plus-o animated rotateIn"></i><strong> Evento</strong></h4>
                        <div class="col-md-12">
                            <label for="cadastro-evento-img" class="label-modificado-imagens">Imagem de Capa</label>
                            <div class="md-form">                
                                <input id="cadastro-evento-img-capa" type="file" >
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="md-form">  
                                <input  type="text" id="evento-nome" class="form-control" required ng-model="evento.nome"
                                        maxlength="75">
                                <label for="evento-nome">Nome do Evento</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="md-form">                
                                <textarea id="evento-descricao" class="md-textarea" ng-model="evento.detalhes"></textarea>
                                <label for="evento-descricao">Descrição</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="md-form">                
                                <input type="text" id="evento-lotacao-minima" class="form-control" ng-model="evento.lotacaoMin">
                                <label for="evento-lotacao-minima">Lotação Minima</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="md-form">  
                                <input type="text" id="evento-lotacao-maxima" class="form-control" ng-model="evento.lotacaoMax">
                                <label for="evento-lotacao-maxima">Lotação Maxima</label>
                            </div>
                        </div>
                        <div class="col-md-6">

                            <div class="col-md-12">
                                <div class="form-control-wrapper">
                                    <input type="text" value="" id="datetimepicker-start" placeholder="YYYY-MM-DD HH:MM:SS" class="linecons-calendar"/>

                                </div>
                            </div>
                        </div> 
                        <div class="col-md-6">                        
                            <div class="col-md-12">
                                <div class="form-control-wrapper">
                                    <input type="text" value="" id="datetimepicker-end" placeholder="YYYY-MM-DD HH:MM:SS" class="linecons-calendar"/>
                                    <p id="p-alerta" style="display: none;">Corrigir data, data de termino deve ser depois da data atual</p>
                                </div>
                            </div>
                        </div>

                        <div     
                            isteven-multi-select
                            input-model="categorias"
                            output-model="categoriasSelecionadas"
                            button-label="nome"
                            item-label="nome"
                            tick-property="ticked"
                            >
                        </div>
                        <!--</div>-->
                        <div class="col-md-4">
                            <div class="md-form">                        
                                <input type="text" id="evento-idade-minima" class="form-control" ng-model="evento.idade">
                                <label for="evento-idade-minima">Idade Minima </label>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="md-form">   
                                <section title=".squaredFour">
                                    <!-- .squaredFour -->
                                    <div class="squaredFour">
                                        <input type="checkbox" value="None" id="evento-gratuito" name="check" ng-click="alterarPreco()"/>

                                    </div>
                                    <!-- end .squaredFour -->
                                </section>

                                <label for="evento-gratuito">Evento Gratuito</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="md-form">                        
                                <input type="text" id="evento-preco" class="form-control" ng-model="evento.preco">
                                <label for="evento-preco">Preço</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <label for="cadastro-evento-imagem-galeria" class="label-modificado-imagens"> Galeria de Imagens sobre o evento, local ..</label>
                            <div class="md-form">                
                                <input id="cadastro-evento-imagem-galeria" type="file" multiple >
                            </div>
                        </div>
                    </div>
                    <!--/.Card content-->

                </div>

                <div class="col-md-12">

                    <!--Card content-->
                    <div class="card-block text-xs-center">
                        <!--Title-->
                        <h4 class="card-title"><i class="fa fa-map-marker animated rotateIn"></i><strong> Endereço</strong></h4>
                        <div class="col-md-4">
                            <div class="md-form col-md-8">                
                                <input type="text" id="evento-cep" class="form-control"  ng-model="evento.endereco.cep" ng-blur="onblurCepEvento()">
                                <label for="evento-cep">CEP</label>
                            </div>
                            <div class="col-md-4 link-cep"><a title="clique aqui e saiba seu cep" class="text-info">Não Sei meu cep</a></div>
                        </div>
                        <div class="col-md-4">
                            <div class="md-form">  
                                <input  type="text" id="evento-numero" class="form-control">
                                <label for="evento-numero"   ng-model="evento.endereco.numero">Número</label>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="md-form">                
                                <input type="text" id="evento-complemento" class="form-control" ng-model="evento.endereco.complemento">
                                <label for="evento-complemento">Complemento</label>
                            </div>
                        </div>

                        <div class="col-md-12" ng-if="evento.endereco.rua != null">
                            <label for="evento-rua" class="label-form">Rua</label>
                            <p>{{evento.endereco.rua}}</p>
                            <hr>
                        </div>

                        <div class="col-md-12" ng-if="evento.endereco.bairro != null">
                            <label for="evento-bairro" class="label-form">Bairro</label>
                            <p>{{evento.endereco.bairro}}</p>   
                            <hr>
                        </div>

                        <div class="col-md-12 " ng-if="evento.endereco.cidade != null">
                            <label for="evento-cidade" class="label-form">Cidade</label>
                            <p>{{evento.endereco.cidade}}</p>   
                            <hr>
                        </div> 


                        <div class="col-md-12">
                            <button class="btn btn-lg btn-warning" ng-click="cadastrar()"><i class="fa fa-check"></i> Cadastrar</button>
                        </div>

                    </div>
                    <!--/.Card content-->

                </div>



            </div>
        </form>
    </div>
    <!--/Form fim da div do form-->

    <ng-include src="'../View/footer.html'"></ng-include>
    <!-- /.fim do projeto-->

    <!-- SCRIPTS -->

    <!-- JQuery -->


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

    <link href="../css/bootstrap/isteven-multi-select.css" rel="stylesheet" type="text/css"/>

    <script src="../lib/angular/isteven-multi-select.js" type="text/javascript"></script>
    
    <link href="../lib/jquery/jquery.datetimepicker.min.css" rel="stylesheet" type="text/css"/>

    <script src="../lib/jquery/jquery.datetimepicker.full.js" type="text/javascript"></script>
    
    <script src="../js/outros/datetimepickerJS.js" type="text/javascript"></script>


</body>

</html>
