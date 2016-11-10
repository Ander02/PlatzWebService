<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
index da empresa , quando logada vai ser redirecionada para esta pagina , quando estiver navegando ela sera
considerada pagina de eventos que a empresa postou
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

        <!-- link util -->
        <script src="../js/util.js" type="text/javascript"></script>
        <!-- Link Controller -->
        <script src="../js/controller/perfilEmpresaController.js" type="text/javascript"></script>
        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

        <!-- Your custom styles (efeito) -->
        <link href="../css/efeitos/indexEmpresa.css" rel="stylesheet">
    </head>

    <body ng-controller="perfilEmpresaController">
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
        <h1 class="titulo-meusEventos">Meus Eventos</h1>
    </div>

    <input type='hidden' id='current_page' />  
    <input type='hidden' id='show_per_page' />  

    <!--div content. Os elementos filho serão utilizados para paginar
    (eles não têm de ser todos iguais, você pode usar divs, parágrafos, vãos, ou o que quiser misturados). '-->  
    <div id='content' ng-if="permicao"> 

        <div class="col-md-3 eventos-empresa" ng-repeat="evento in eventos">
            <div class="card">
                <div class="hovereffect">
                    <img class=" img-responsive " ng-src="{{buscarImagemCapa(evento.id)}}" onerror = "this.src = '../img/placeholder.png'"  alt="Imagem do Evento" >
                    <div class="ev-cancelado">
                                        <p>Cancelado</p>
                                    </div>
                    <!--<img class=" img-responsive " src="../img/outras/plano-fundo.jpg" alt="Imagem do Evento" >-->
                    <div class="overlay">
                        <h2>{{evento.nome}}</h2>
                        <hr/>                                             
                        <div class="col-md-3 estrelas">
                            <div class="stars">
                                <form action="">
                                    <input class="star star-5" id="star-5-2" type="radio" name="star" ng-checked=" evento.mediaArredondada == 5" disabled/>
                                    <label class="star star-5" for="star-5-2"></label>
                                    <input class="star star-4" id="star-4-2" type="radio" name="star" ng-checked=" evento.mediaArredondada == 4" disabled/>
                                    <label class="star star-4" for="star-4-2"></label>
                                    <input class="star star-3" id="star-3-2" type="radio" name="star" ng-checked=" evento.mediaArredondada == 3" disabled/>
                                    <label class="star star-3" for="star-3-2"></label>
                                    <input class="star star-2" id="star-2-2" type="radio" name="star" ng-checked=" evento.mediaArredondada == 2" disabled/>
                                    <label class="star star-2" for="star-2-2"></label>
                                    <input class="star star-1" id="star-1-2" type="radio" name="star" ng-checked=" evento.mediaArredondada == 1" disabled/>
                                    <label class="star star-1" for="star-1-2"></label>
                                </form>

                            </div> 
                        </div>                    
                    </div><!--  /. div overlay -->

                </div><!-- /. div hovereffect  -->

                <h4 class="card-title">{{evento.nome}}</h4>
                <p><i class="fa fa-calendar animated bounceInDown"></i> {{evento.dataInicio}} </p>
                <p><i class="fa fa-map-marker animated bounceInDown"></i> {{evento.endereco.bairro}}, {{evento.endereco.cidade.nome}} - {{evento.endereco.cidade.estado.uf}} </p>
                <p><a class="btn btn-warning " href="../eventoEspecifico.jsp?evento={{evento.id}}" role="button">Ver Mais Detalhes &raquo;</a></p>
                <p><a class="btn btn-default " href="../Empresa/editarEvento.jsp" role="button">Editar Evento</a></p>
            </div>
        </div> <!-- /. div col-md-3 evento -->       
    </div><!-- /. div content -->

    <!-- uma div vazia que sera preenchida usando jquery --> 
    <div class="col-md-12 paginator-itens">
        <ul class="pagination">
            <li id='page_navigation'></li> 
        </ul>
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
    

</body>

</html>
