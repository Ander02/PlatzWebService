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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">

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

        <!-- Link app -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- link util -->

        <!-- link controller -->

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

        <!--links para o funcionamento do datetimepicker-->
        <link href="../css/bootstrap/bootstrap-material-datetimepicker.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-1.12.3.min.js" integrity="sha256-aaODHAgvwQW1bFOGXMeX+pC4PZIPsvn2h1sArYOhgXQ=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="http://momentjs.com/downloads/moment-with-locales.min.js"></script>

    </head>

    <body>
        <!-- inicio do projeto aqui-->

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
                            <label for="conta-empresa-img">Imagem de Capa</label>
                            <div class="md-form">                
                                <input id="conta-empresa-img" type="file" multiple>

                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="md-form">  
                                <input  type="text" id="evento-nome" class="form-control" required=""
                                        maxlength="75">
                                <label for="evento-nome">Nome do Evento</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="md-form">                
                                <textarea id="evento-descricao" class="md-textarea"></textarea>
                                <label for="evento-descricao">Descrição</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="md-form">                
                                <input type="text" id="evento-lotacao-minima" class="form-control">
                                <label for="evento-lotacao-minima">Lotação Minima</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="md-form">  
                                <input type="text" id="evento-lotacao-maxima" class="form-control">
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

                        <div class="col-md-3">
                            <div class="md-form">                
                                <select class="form-control btn btn-lg btn-warning-outline hovereffect" id="selecione1">
                                    <option>Selecione </option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="md-form">                        
                                <input type="text" id="evento-idade-minima" class="form-control">
                                <label for="evento-idade-minima">Idade Minima</label>
                            </div>
                        </div>

                        <div class="col-md-3">
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
                        <div class="col-md-3">
                            <div class="md-form">                        
                                <input type="text"  id="evento-preco" class="form-control">
                                <label for="evento-preco">Preço</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <label for="conta-empresa-galeria"> Galeria de Imagens sobre o evento, local ..</label>
                            <div class="md-form">                
                                <input id="conta-empresa-galeria" type="file" multiple>

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
                        <div class="col-md-6">
                            <div class="md-form">                
                                <input type="text" id="evento-cep" class="form-control">
                                <label for="conta-empresa-cep">CEP</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="md-form">  
                                <input  type="text" id="evento-numero" class="form-control">
                                <label for="conta-empresa-numero">Número</label>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="md-form">                
                                <input type="text" id="evento-rua" class="form-control">
                                <label for="conta-empresa-rua">Rua</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="md-form">  
                                <input  type="text" id="evento-bairro" class="form-control">
                                <label for="conta-empresa-bairro">Bairro</label>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="md-form">                
                                <input type="text" id="evento-complemento" class="form-control">
                                <label for="conta-empresa-complemento">Complemento</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="md-form">  
                                <input  type="text" id="evento-cidade" class="form-control">
                                <label for="conta-empresa-cidade">Cidade</label>
                            </div>
                        </div>

                    </div>
                    <!--/.Card content-->

                    <div>
                        <button class="btn btn-lg btn-warning"><i class="fa fa-check"></i> Cadastrar</button>
                    </div>
                </div>



            </div>
        </form>
    </div>

    <!--/Form fim da div do form-->

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


</body>

</html>
