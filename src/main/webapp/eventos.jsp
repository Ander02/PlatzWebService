<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Pagina onde ira exibir os eventos, onde sera possivel filtrar os eventos
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
        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

        <!-- Material Design Bootstrap -->
        <link href="css/bootstrap/mdb.min.css" rel="stylesheet">

        <!-- Your custom styles (optional) -->
        <link href="css/style.css" rel="stylesheet">

        <!-- Your custom styles (efeito) -->
        <link href="css/efeitos/indexEmpresa.css" rel="stylesheet">

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">


    </head>

    <body >
        <!-- inicio do projeto aqui-->
    <ng-include src="'View/nav.html'"></ng-include>
    <div class="espaco"></div>

    <div class="head-pagina">
        <h1 class="titulo-meusEventos">Eventos</h1>
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
    </div><!-- fim da div head-pagina -->

    <input type='hidden' id='current_page' />  
    <input type='hidden' id='show_per_page' />  

    <div class="head-pagina">
        <h1>Nome da Categoria</h1>
    </div>
    <!--div content. Os elementos filho serão utilizados para paginar (eles não têm de ser todos iguais, 
    você pode usar divs, parágrafos, vãos, ou o que quiser misturados). '-->  
    <div id='content'> 
        <div class="col-md-3 eventos-categoria">
            <div class="card">
                <div class="hovereffect">
                    <img class=" img-responsive img-evento-efeito" src="img/outras/plano-fundo.jpg" alt="Imagem do Evento" >
                    <div class="overlay">
                        <h2>Nome do Evento</h2>
                        <hr/>

                        <p> 
                            <a class="btn btn-warning-outline">
                                <i class="fa fa-check left animated bounceInUp "></i>
                                <span class="hidden-md-down ">Vou</span>
                            </a>         

                            <!--Comentarios-->

                            <a class="btn btn-default-outline ">
                                <i class="fa fa-minus left animated bounceInUp "></i>
                                <span class="hidden-md-down ">Talvez</span>
                            </a>

                            <!-- galeria de imagens -->

                            <a class="btn btn-warning-outline">
                                <i class="fa fa-remove left animated bounceInUp "></i>
                                <span class="hidden-md-down ">Não vou</span>
                            </a>

                        </p> 
                        <a class="info" href="#">Ver Mais</a>
                    </div><!--  /. div overlay -->

                </div><!-- /. div hovereffect  -->

                <h4 class="card-title">Nome do evento</h4>
                <h5><i class="fa fa-building-o animated bounceInDown"></i> Empresa</h5>
                <p><i class="fa fa-calendar animated bounceInDown"></i> Data: </p>
                <p><i class="fa fa-map-marker animated bounceInDown"></i> Local: </p>
                <p><a class="btn btn-warning " href="evento-especifico.html" role="button">Ver Mais Detalhes &raquo;</a></p>
            </div>
        </div> <!-- /. div col-md-3 evento -->

 

    </div>  <!-- /. div content -->

    <!-- uma div vazia que sera preenchida usando jquery --> 
    <div class="col-md-12 paginator-itens">
        <ul class="pagination">
            <li id='page_navigation'></li> 
        </ul>
    </div>

    <ng-include src="'View/footer.html'"></ng-include>
    <!-- /fim do projeto-->


    <!-- SCRIPTS -->

    <!-- JQuery -->
    <script type="text/javascript" src="lib/jquery/jquery-2.2.3.min.js"></script>

    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="lib/bootstrap/tether.min.js"></script>

    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="lib/bootstrap/bootstrap.min.js"></script>

    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="lib/bootstrap/mdb.min.js"></script>

    <!-- angular app script -->
    <script type="text/javascript" src="js/app.js"></script>

    <!-- Paginação da tabela -->
    <script type="text/javascript" src="js/outros/paginacao-eventos-categoria.js"></script>
    <!-- link Angular -->
    <script src="lib/angular/angular-animate.js" type="text/javascript"></script>
    <!-- link Angular -->
    <link href="css/angular-toastr.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="lib/angular/angular-toastr.tpls.js"></script>



</body>

</html>
