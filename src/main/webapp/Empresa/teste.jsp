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

        <!-- Your custom styles (optional) -->
        <link href="../css/style.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="../css/styleEmpresa.css" rel="stylesheet">

        <link href="../css/efeitos/dropEvento.css" rel="stylesheet" type="text/css"/>

        <!-- link Angular -->
        <script type="text/javascript" src="../lib/angular/angular.js"></script>

        <!-- Link app -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- link util -->
        <script src="../js/util.js" type="text/javascript"></script>

        <!-- link controller -->
        <script src="../js/controller/eventoCadastroController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

        <!--links para o funcionamento do datetimepicker-->
        <link href="../css/bootstrap/bootstrap-material-datetimepicker.css" rel="stylesheet" type="text/css"/>

        <script src="../lib/jquery/jquery-1.12.3.min.js" type="text/javascript"></script>

        <script src="../lib/bootstrap/moment-with-locales.js" type="text/javascript"></script>

        <link href="../css/bootstrap/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>

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
        <form>

            <div class="col-md-8 corpo-form-cadastro-evento card">

                <div class="col-md-12">

                    <!--Card content-->
                    <div class="card-block text-xs-center">
                        <!--Title-->
                        <h4 class="card-title"><i class="fa fa-spinner animated rotateIn"></i><strong> Evento</strong></h4>
                        <div class="col-md-12">
                            <label for="cadastro-evento-img">Imagem de Capa</label>
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
                                    <input type="text" id="date-start" class="form-control floating-label" placeholder="Data de Inicio">
                                </div>
                            </div>
                        </div> 
                        <div class="col-md-6">                        
                            <div class="col-md-12">
                                <div class="form-control-wrapper">
                                    <input type="text" id="date-end" class="form-control floating-label" placeholder="Data de Termino">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12">

                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="maxOption2" class="col-lg-2 control-label">Selecione Algumas catergorias</label>

                                    <div class="col-lg-10">
                                        <select id="maxOption2" class="selectpicker show-menu-arrow form-control" multiple data-max-options="3" ng-model="evento.categoriasId ">
                                            <option ng-repeat="categoria in categorias" >{{categoria.nome}}</option>
                                            <option>lala</option>
                                            <option>lala</option>
                                            <option>lala</option>                                            
                                        </select>
                                    </div>
                                </div>
                            </form>
                            
                            <label for="select-categoria">Selecione uma categoria</label>
                            <select class="form-control select-categoria"  multiple ng-model="evento.categoriasId" 
                                    ng-options="categoria.id as categoria.nome for categoria in categorias">
                            </select>
                        </div>

                        <div class="col-md-4">
                            <div class="md-form">                        
                                <input type="text" id="evento-idade-minima" class="form-control" ng-model="evento.idade">
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
                                <input type="text"  id="evento-preco" class="form-control" ng-model="evento.preco">
                                <label for="evento-preco">Preço</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <label for="cadastro-evento-imagem-galeria"> Galeria de Imagens sobre o evento, local ..</label>
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

                        <div class="col-md-12">
                            <label for="evento-rua" class="label-form">Rua</label>
                            <p>{{evento.endereco.rua}}</p>
                            <hr>
                        </div>

                        <div class="col-md-12">
                            <label for="evento-bairro" class="label-form">Bairro</label>
                            <p>{{evento.endereco.bairro}}</p>   
                            <hr>
                        </div>

                        <div class="col-md-12 ">
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
    <script type="text/javascript" src="../lib/jquery/jquery-2.2.3.min.js"></script>

    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="../lib/bootstrap/tether.min.js"></script>

    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="../lib/bootstrap/bootstrap.min.js"></script>

    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="../lib/bootstrap/mdb.min.js"></script>

    <!-- Paginação da tabela -->
    <script type="text/javascript" src="../js/outros/paginacao-empresa.js"></script>

    <!--data e horario (iniciando) -->
    <script src="../lib/bootstrap/dataehorario.js" type="text/javascript"></script>

    <script src="../lib/bootstrap/bootstrap-material-datetimepicker.js" type="text/javascript"></script>

    <script src="../js/outros/desabilitarPreco.js" type="text/javascript"></script>

    <!-- link Angular animate -->
    <script src="../lib/angular/angular-animate.js" type="text/javascript"></script>

    <!-- link Angular css toastr -->
    <link href="../css/angular-toastr.css" rel="stylesheet" type="text/css"/>

    <!-- link TOASTR -->
    <script type="text/javascript" src="../lib/angular/angular-toastr.tpls.js"></script>

    <!-- aside -->
    <script src="../js/outros/aside.js" type="text/javascript"></script>

    <script src="../lib/jquery/jquery-1.11.3.js" type="text/javascript"></script>

    <script src="../lib/bootstrap/bootstrap-select.min.js" type="text/javascript"></script>
</body>

</html>
