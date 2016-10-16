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

        <!-- link app -->
        <script type="text/javascript" src="../js/app.js"></script>

        <!-- link util -->

        <!-- Link Controller -->

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="../img/logo.png">

        <!-- Your custom styles (efeito) -->
        <link href="../css/efeitos/indexEmpresa.css" rel="stylesheet">
    </head>

    <body>
        <!-- inicio do projeto aqui-->
    <ng-include src="'../View/nav-empresa.html'"></ng-include>
    <div class="espaco"></div>

    <div class="head-pagina">
        <h1 class="titulo-meusEventos">Meus Eventos</h1>
        <form class="form-inline form-pesquisa">
            <div class="form-group">                
                <select class="form-control btn btn-lg btn-warning-outline hovereffect" id="selecione1">
                    <option>Selecione </option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                </select>
            </div>
            <div class="form-group">
                <input id="nome-evento" type="text" class="form-control" maxlength="30" placeholder="Nome do Evento">
            </div>
            <button class="btn btn-default" type="button">Pesquisar</button>
        </form><!-- /. form-inline form-pesquisa -->
    </div>

    <input type='hidden' id='current_page' />  
    <input type='hidden' id='show_per_page' />  

    <!--div content. Os elementos filho serão utilizados para paginar
    (eles não têm de ser todos iguais, você pode usar divs, parágrafos, vãos, ou o que quiser misturados). '-->  
    <div id='content'> 

        <div class="col-md-3 eventos-categoria">
            <div class="card">
                <div class="hovereffect">
                    <img class=" img-responsive " src="../img/outras/plano-fundo.jpg" alt="Imagem do Evento" >
                    <div class="overlay">
                        <h2>Nome do Evento</h2>
                        <hr/>                                             
                        <div class="col-md-3 estrelas">
                            <div class="stars">
                                <form action="">
                                    <input class="star star-5" id="star-5-2" type="radio" name="star"/>
                                    <label class="star star-5" for="star-5-2"></label>
                                    <input class="star star-4" id="star-4-2" type="radio" name="star"/>
                                    <label class="star star-4" for="star-4-2"></label>
                                    <input class="star star-3" id="star-3-2" type="radio" name="star"/>
                                    <label class="star star-3" for="star-3-2"></label>
                                    <input class="star star-2" id="star-2-2" type="radio" name="star"/>
                                    <label class="star star-2" for="star-2-2"></label>
                                    <input class="star star-1" id="star-1-2" type="radio" name="star"/>
                                    <label class="star star-1" for="star-1-2"></label>
                                </form>

                            </div> 
                        </div>                    
                    </div><!--  /. div overlay -->

                </div><!-- /. div hovereffect  -->

                <h4 class="card-title">Nome do evento</h4>
                <h5><i class="fa fa-building-o animated bounceInDown"></i> Empresa</h5>
                <p><i class="fa fa-calendar animated bounceInDown"></i> Data: </p>
                <p><i class="fa fa-map-marker animated bounceInDown"></i> Local: </p>
                <p><a class="btn btn-warning " href="evento-especifico.html" role="button">Ver Mais Detalhes &raquo;</a></p>
            </div>
        </div> <!-- /. div col-md-3 evento -->       
    </div><!-- /. div content -->

    <!-- uma div vazia que sera preenchida usando jquery --> 
    <div class="col-md-12 paginator-itens">
        <ul class="pagination">
            <li id='page_navigation'></li> 
        </ul>
    </div>
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