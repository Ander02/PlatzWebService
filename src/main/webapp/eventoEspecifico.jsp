<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Pagina de evento especifico 
-->
<html lang="pt-br" ng-app="platz">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- meta para compatibilidade no IE -->
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <title>Platz - Suas rotas, Seus Eventos</title>

        <!-- Font Awesome -->
        <link href="css/font/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

        <!-- Material Design Bootstrap -->
        <link href="css/bootstrap/mdb.min.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="css/style.css" rel="stylesheet">

        <!-- Your custom styles (efeito) -->
        <link href="css/efeitos/eventoEspecifico.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="js/app.js"></script>

        <!-- angular app UTIL -->
        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/services/loginService.js" type="text/javascript"></script>

        <!-- angular app CONTROLLER LOGIN -->
        <script src="js/controller/loginController.js" type="text/javascript"></script>

        <!-- CONTROLLER EVENTO ESPECIFICO -->
        <script src="js/controller/eventoEspecificoController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">

        <!-- script com o arquivo do mapa -->
        <script type="text/javascript"
        src="http://maps.google.com/maps/api/js?key=AIzaSyBdWHheIGYeuOEDTS4iYFI1lbIbq7-W7Hw"></script>
        <script src="js/outros/mapa-index.js"></script>

    </head>

    <body onload="initialize()" id="corpo-evento-epecifico">

        <%
            try {
                String token = session.getAttribute("token").toString();
                if (token == null) {
                    out.print("<input type='hidden' id='token' name='token' value ='' >");
                } else {
                    out.print("<input type='hidden' id='token' name='token' value ='" + token + "' >");
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar sessão " + e.getMessage());
                out.print("<input type='hidden' id='token' name='token' value ='' >");
            }
            String evento = request.getParameter("evento");

            out.print("<input type='hidden' id='idEvento' name='idEvento' value ='" + evento + "' >");
        %>

        <!-- inicio do projeto aqui-->

        <div ng-controller="loginController">
            <ng-include ng-if="conta == null" src="'View/nav.html'" ></ng-include>
            <ng-include ng-if="conta.perfil === 'Administrador'" src="'View/nav-adm.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Empresa'" src="'View/nav-empresa.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Usuario'" src="'View/nav-usuario.html'"  ></ng-include>
        </div>

        <div class="espaco"></div>
        <div ng-controller="eventoEspecificoController">

            <section class="section section-blog-fw">
                <!--First row-->
                <div class="row">    
                    <!--Post data-->
                    <div class="jumbotron caixa-informacao-especifico">

                        <div class="col-md-10" id="section-sem-evento">
                            <div class="jumbotron animated fadeInUp">
                                <h1> Este evento foi bloqueado</h1>
                                <p>Por motivos de inapropriação, os administradores decidiram bloquear este evento. 
                                    Para mais informações entre em contato com os administradores </p>
                                <p><a class="btn btn-amber" href="/sobre.jsp">Clique aqui</a></p>
                            </div>
                        </div>

                        <!-- Imagem do evento -->
                        <div class="col-lg-12 col-md-4 col-sm-6 col-xs-12 imagem-evento-especifico">
                            <div class="hovereffect">
                                <img class="img-responsive" ng-src="{{imagemCapa}}" onerror="this.src='img/placeholder.png'" >
                                <div class="ev-cancelado">
                                    <p>Cancelado</p>
                                </div>

                                <div class="curtir-ev">
                                    <a class="btn btn-pink">
                                        <i class="fa fa-heart-o left animated bounceInUp fa-2x "></i> 
                                    </a> 
                                </div>

                                <div class="overlay">
                                    <h2>Curta Nossos Eventos</h2>
                                    <p> 
                                        <a class="btn btn-warning-outline">
                                            <i class="fa fa-check left animated bounceInUp "></i>
                                            <span class="hidden-md-down ">Vou</span>
                                        </a>         
                                        <!--Opção de talvez ir-->
                                        <a class="btn btn-default-outline ">
                                            <i class="fa fa-minus left animated bounceInUp "></i>
                                            <span class="hidden-md-down ">Talvez</span>
                                        </a>
                                        <!-- Opção de não vou -->
                                        <a class="btn btn-warning-outline">
                                            <i class="fa fa-remove left animated bounceInUp "></i>
                                            <span class="hidden-md-down ">Não vou</span>
                                        </a>
                                    </p> 
                                </div><!-- overlay -->
                            </div><!-- hoverEffect-->
                        </div><!-- /.imagemEventoEspecifico -->

                        <div class="col-md-12 informacao-evento-especifico">
                            <div class="col-md-12">
                                <div class="col-md-5"></div>
                                <div class="col-md-2">  <h1 ng-bind="evento.nome"></h1> </div>
                                <div class="col-md-5"> 

                                </div>
                            </div>
                            <hr>
                            <div class="col-md-12 estrelas">
                                <div class="col-md-6 media-ev">
                                    <h3>Média: {{media}}</h3>
                                </div>
                                <div class="stars">
                                    <form action="">                                
                                        <input class="star star-5" id="star-5-2" type="radio" name="star" ng-click="avaliar(5)" ng-checked=" notaUsuario === 5"/>
                                        <label class="star star-5" for="star-5-2"></label>
                                        <input class="star star-4" id="star-4-2" type="radio" name="star" ng-click="avaliar(4)" ng-checked=" notaUsuario === 4"/>
                                        <label class="star star-4" for="star-4-2"></label>
                                        <input class="star star-3" id="star-3-2" type="radio" name="star" ng-click="avaliar(3)" ng-checked=" notaUsuario === 3"/>
                                        <label class="star star-3" for="star-3-2"></label>
                                        <input class="star star-2" id="star-2-2" type="radio" name="star" ng-click="avaliar(2)" ng-checked=" notaUsuario === 2"/>
                                        <label class="star star-2" for="star-2-2"></label>
                                        <input class="star star-1" id="star-1-2" type="radio" name="star" ng-click="avaliar(1)" ng-checked=" notaUsuario === 1"/>
                                        <label class="star star-1" for="star-1-2"></label>
                                    </form>
                                </div> 
                            </div>


                            <div class="col-md-12">    

                                <h3><i class="fa fa-building-o"></i><a href="perfilEmpresa.jsp?empresa={{evento.empresa.id}}" ng-bind="evento.empresa.nomeFantasia"></a></h3>

                                <!--botoes para rota , galeria e comentarios-->
                                <div class="social-counters ">


                                    <!--calculo de rotas-->
                                    <button type="button"  data-toggle="modal" data-target="#myModal">
                                        <a class="btn btn-warning btn-rota ">
                                            <i class="fa fa-map-marker left animated bounceInDown "></i>
                                            <span class="hidden-md-down  ">Calcular Rota</span>
                                        </a></button>              

                                    <!--Comentarios-->
                                    <button type="button"  data-toggle="modal" data-target="#myModal2">
                                        <a class="btn btn-default ">
                                            <i class="fa fa-comments-o left animated bounceInDown"></i>
                                            <span class="hidden-md-down ">Comentarios</span>
                                        </a></button>

                                    <!-- galeria de imagens -->
                                    <button type="button"  data-toggle="modal" data-target="#myModal3">
                                        <a class="btn btn-warning btn-rota ">
                                            <i class="fa fa-photo left animated bounceInDown "></i>
                                            <span class="hidden-md-down ">fotos</span>
                                        </a></button>

                                    <button type="button" >
                                        <a class="btn btn-default" >
                                            <i class="fa fa-edit left animated bounceInDown "></i>
                                            <span class="hidden-md-down ">Editar Evento</span>
                                        </a></button>
                                </div>
                            </div>
                        </div>


                        <!--/.botoes -->

                        <div class="excerpt">
                            <div class="info-especifico">
                                <div class="col-md-6">
                                    <h3>Informações</h3>
                                    <p>Data Inicio {{evento.dataInicio}} , Data Termino {{evento.dataFim}}</p>
                                    <hr/> 
                                    <p>Lotação Minima: {{evento.lotacaoMin}} , Lotação Maxima: {{evento.lotacaoMax}}</p>
                                    <hr/>
                                    <p>Preço: {{evento.preco}}</p>
                                </div>
                                <div class="col-md-6">
                                    <h3>Endereço</h3>
                                    <p>Endereço: {{evento.endereco.rua}}, {{evento.endereco.numero}} </p>
                                    <p ng-if="evento.endereco.cidade.nome == null"> Complemento: {{evento.endereco.complemento}} </p>
                                    <hr/>
                                    <p> Bairro: {{evento.endereco.bairro}}, Cidade: {{evento.endereco.cidade.nome}}  - {{evento.endereco.cidade.estado.uf}}  </p>
                                    <hr/>

                                </div>
                            </div>

                            <div class="col-md-12 descricao-especifico">
                                <h3>Descrição</h3>
                                <hr/>
                                <p ng-bind="evento.detalhes"></p>
                                <hr/>
                            </div>

                            <div class="col-md-12">
                                <!-- mapa com os eventos -->
                                <div class="mapa-especifico">
                                    <div id="map_canvas" ></div>
                                </div>
                                <!-- /. mapa -->

                            </div>


                        </div>


                    </div>
                    <!--/Post data-->




                </div>
                <!--/First row-->


            </section>
            <!--/Section evento especifico-->

            <!-- Modal de calculo de rota-->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Calculo de rotas</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">
                            <form>
                                <div class="form-group row">
                                    <label for="inputCheck3" class="col-sm-4 col-form-label">Calcular pela minha localização</label>
                                    <div class="col-sm-4">
                                        <input type="checkbox" class="form-control input-contato" id="inputCheck3" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputText3" class="col-sm-4 col-form-label">Ponto de Partida</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control input-contato" id="inputText3" placeholder="Rua , Av." required maxlength="50" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputTransporte3" class="col-sm-4 col-form-label">Selecione Um Tipo de Transporte</label>
                                    <div class="col-sm-8">
                                        <select class="form-control input-contato">
                                            <option> -- Selecione um Tipo de Transporte --</option>
                                        </select>
                                    </div>
                                </div>


                            </form>
                        </div>
                        <!--Footer-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-dismiss="modal">Fechar</button>
                            <button type="button" class="btn btn-default">Calcular Rota</button>
                        </div>
                    </div>
                    <!--/.Content-->
                </div>
            </div>
            <!-- /. modal de calculo de rotas-->

            <!-- Modal de cometarios-->
            <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Comentarios</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">

                            <!--Section: Team v.3-->
                            <section class="section team-section section-comentarios">

                                <h1 class="section-heading">Faça seu  comentario</h1>
                                <!--Section sescription-->
                                <div class="section-description">
                                    <form>
                                        <div class="md-form">                                 
                                            <textarea id="form8" class="md-textarea" placeholder="comentario"></textarea>

                                            <button type="submit" class="btn btn-warning btn-comentar">Comentar</button>
                                        </div>
                                    </form>
                                </div> 

                                <!--First column-->
                                <div class="col-md-12 m-b-r" ng-repeat="postagem in postagens">

                                    <div class="col-md-3">
                                        <img onerror='this.src = "/img/outras/teste-perfil.jpg"' class="img-circle img-responsive">
                                    </div>

                                    <div class="col-md-9">
                                        <h4>Nome usuario</h4>
                                        <p>{{postagem.conteudo}}</p>                                          
                                    </div>

                                </div>
                                <!--/First column-->

                            </section>
                            <!--/Section: Team v.3-->
                        </div>
                        <!--Footer-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                    <!--/.Content-->
                </div>
            </div>

            <!-- /. modal de comentarios-->

            <!-- Modal Galeria-->
            <div class="modal fade modal-galeria" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <!--Content-->
                    <div class="modal-content">
                        <!--Header-->
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Galeria</h4>
                        </div>
                        <!--Body-->
                        <div class="modal-body">

                            <!--Carousel Wrapper-->
                            <div id="multi-item-example" class="carousel slide carousel-multi-item" data-ride="carousel">

                                <!--Controls-->
                                <div class="controls-top">
                                    <a class="btn-floating btn-small" href="#multi-item-example" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
                                    <a class="btn-floating btn-small" href="#multi-item-example" data-slide="next"><i class="fa fa-chevron-right"></i></a>
                                </div>
                                <!--/.Controls-->



                                <!--Slides-->
                                <div class="carousel-inner" role="listbox">

                                    <!--First slide-->
                                    <div class="carousel-item active">

                                        <div class="col-md-4">
                                            <div class="card">
                                                <img class="img-fluid" src="http://mdbootstrap.com/images/regular/nature/img%20(1).jpg" alt="Card image cap">                                               
                                            </div>
                                        </div>

                                        <div class="col-md-4 hidden-sm-down">
                                            <div class="card">
                                                <img class="img-fluid" src="http://mdbootstrap.com/images/regular/nature/img%20(2).jpg" alt="Card image cap">

                                            </div>
                                        </div>

                                        <div class="col-md-4 hidden-sm-down">
                                            <div class="card">
                                                <img class="img-fluid" src="http://mdbootstrap.com/images/regular/nature/img%20(3).jpg" alt="Card image cap">

                                            </div>
                                        </div>

                                    </div>
                                    <!--/.First slide-->



                                </div>
                                <!--/.Slides-->

                            </div>
                            <!--/.Carousel Wrapper-->

                        </div>
                        <!--Footer-->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                    <!--/.Content-->
                </div>
            </div>
            <!-- /.Galeria-->

            <ng-include src="'View/footer.html'"></ng-include>

            <!-- /. fim do html-->


            <!-- SCRIPTS -->

            <!-- JQuery -->
            <script type="text/javascript" src="lib/jquery/jquery-2.2.3.min.js"></script>

            <!-- Bootstrap tooltips -->
            <script type="text/javascript" src="lib/bootstrap/tether.min.js"></script>

            <!-- Bootstrap core JavaScript -->
            <script type="text/javascript" src="lib/bootstrap/bootstrap.min.js"></script>

            <!-- MDB core JavaScript -->
            <script type="text/javascript" src="lib/bootstrap/mdb.min.js"></script>

            <!-- link Angular -->
            <script src="lib/angular/angular-animate.js" type="text/javascript"></script>

            <!-- link Angular -->
            <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>

            <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>

            <!-- Aside para quando estiver logado -->
            <script src="js/outros/aside.js" type="text/javascript"></script>
        </div>

    </body>

</html>
