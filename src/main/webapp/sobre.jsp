<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br" ng-app="platz">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="js/app.js"></script>

        <script src="js/util.js" type="text/javascript"></script>

        <script src="js/services/loginService.js" type="text/javascript"></script>
        
        <script src="js/services/validacaoService.js" type="text/javascript"></script>

        <script src="js/controller/loginController.js" type="text/javascript"></script>

        <script src="js/controller/sobreController.js" type="text/javascript"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">


    </head>

    <body >
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
        %>

        <!-- inicio do projeto aqui-->

        <div ng-controller="loginController">
            <ng-include ng-if="conta == null" src="'View/nav.html'" ></ng-include>
            <ng-include ng-if="conta.perfil === 'Administrador'" src="'View/nav-adm.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Empresa'" src="'View/nav-empresa.html'"  ></ng-include>
            <ng-include ng-if="conta.perfil === 'Usuario'" src="'View/nav-usuario.html'"  ></ng-include>
        </div>
        <div ng-controller="sobreController">
            <div class="espaco"></div>

            <!-- sobre o projeto platz -->
            <div class="col-md-10" id="section-projeto">

                <div class="jumbotron animated fadeInUp">
                    <h1> Platz - Suas Rotas, Seus Eventos!</h1>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                    </p>
                </div>


            </div>
            <!-- /.sobre platz -->




            <div class="col-md-12" id="section-integrantes">




                <!--Section: Team v.3-->
                <section class="section team-section">

                    <!--Section heading-->
                    <h1 class="section-heading"><i class="fa fa-users animated fadeInLeftBig"></i> Integrantes</h1>
                    <!--Section sescription-->
                    <p class="section-description">Aqui se encontra a descrição de cada um dos integrantes da equipe platz,e quais as
                        dificuldades enfrentadas.</p>

                    <!--First row-->
                    <div class="row center-on-small-only">

                        <!--First column-->
                        <div class="col-lg-6 col-md-12 m-b-r">

                            <div class="col-md-3">
                                <div class="avatar">
                                    <img src="img/sobre/sobreAndy.png" >
                                </div>
                            </div>

                            <div class="col-md-9">
                                <h4>Anderson Pessoa</h4>
                                <h5>Back-End</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod eos id officiis hic tenetur quae quaerat ad velit ab.</p>
                            </div>

                        </div>
                        <!--/First column-->

                        <!--Second column-->
                        <div class="col-lg-6 col-md-12 m-b-r">

                            <div class="col-md-3">
                                <div class="avatar">
                                    <img src="img/sobre/sobreChris.png">
                                </div>
                            </div>

                            <div class="col-md-9">
                                <h4>Christian Xavier</h4>
                                <h5>Back-End</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod eos id officiis hic tenetur quae quaerat ad velit ab.</p>
                            </div>

                        </div>
                        <!--/Second column-->

                    </div>
                    <!--/First row-->

                    <div style="height:20px" class="hidden-md-down"></div>

                    <!--Second row-->
                    <div class="row center-on-small-only">

                        <!--Third column-->
                        <div class="col-lg-6 col-md-12 m-b-r">

                            <div class="col-md-3">
                                <div class="avatar">
                                    <img src="img/sobre/sobreGabs.png">
                                </div>
                            </div>

                            <div class="col-md-9">
                                <h4>Gabriela Nunes</h4>
                                <h5>Front-end Developer</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod eos id officiis hic tenetur quae quaerat ad velit ab.</p>


                            </div>


                        </div>
                        <!--/Third column-->

                        <!--Fourth column-->
                        <div class="col-lg-6 col-md-12 m-b-r">

                            <div class="col-md-3">
                                <div class="avatar">
                                    <img src="img/sobre/sobreJuh.png">
                                </div>
                            </div>

                            <div class="col-md-9">
                                <h4>Juliana Cerqueira</h4>
                                <h5>Android </h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod eos id officiis hic tenetur quae quaerat ad velit ab.</p>

                            </div>

                        </div>
                        <!--/Fourth column-->
                    </div>
                    <!--/Second row-->

                </section>

            </div>


            <!--Section: Contact v.1-->
            <section class="section m-b-4">           

                <div class="row">

                    <!--First column-->
                    <div class="col-md-2"></div>
                    <div class="col-md-8">

                        <!--Form with header-->
                        <div class="card">

                            <div class="card-block form-sobre">
                                <!--Header-->
                                <div class="form-header cabecalho-form-sobre">
                                    <h3><i class="fa fa-envelope"></i> Escreva Para Nós:</h3>
                                </div>

                                <p>Entre em contato com nossa equipe, nos dê sua opinião</p>
                                <br>

                                <!--Body-->

                                <div class="md-form">
                                    <i class="fa fa-envelope prefix"></i>
                                    <input type="text" id="form2" class="form-control" ng-model="mensagemCadastro.email">
                                    <label for="form2">Email</label>
                                </div>

                                <div class="md-form ">                        

                                    <select ng-model="mensagemCadastro.assuntoId" ng-options="assunto.id as assunto.nome for assunto in assuntos" class="select-assunto btn btn-warning-outline">
                                        <option value=""> --Selecione um Assunto--</option>
                                    </select>
                                </div>

                                <div class="md-form">
                                    <i class="fa fa-pencil prefix"></i>
                                    <textarea id="form8" class="md-textarea" ng-model="mensagemCadastro.conteudo"></textarea>
                                    <label for="form8">Mensagem</label>
                                </div>

                                <div class="text-xs-center">
                                    <button class="btn btn-primary" ng-click="cadastrar()">Enviar</button>
                                </div>

                            </div>

                        </div>
                        <!--/Form with header-->

                    </div>
                    <!--/First column-->
                </div>

            </section>
            <!--/Section: Contact v.1-->

            <ng-include src="'View/footer.html'"></ng-include>
            <!-- /Start your project here-->


            <!-- SCRIPTS -->

            <!-- JQuery -->
            <script type="text/javascript" src="lib/jquery/jquery-2.2.3.min.js"></script>

            <!-- Bootstrap tooltips -->
            <script type="text/javascript" src="lib/bootstrap/tether.min.js"></script>

            <!-- Bootstrap core JavaScript -->
            <script type="text/javascript" src="lib/bootstrap/bootstrap.min.js"></script>

            <!-- MDB core JavaScript -->
            <script type="text/javascript" src="lib/bootstrap/mdb.min.js"></script>


            <!-- esconder formularios -->
            <script type="text/javascript" src="js/outros/esconder-form-cad.js"></script>
            <!-- link Angular -->
            <script src="lib/angular/angular-animate.js" type="text/javascript"></script>
            <!-- link Angular -->
            <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>
            <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>
            <script src="js/outros/aside.js" type="text/javascript"></script>

        </div>


    </body>

</html>

