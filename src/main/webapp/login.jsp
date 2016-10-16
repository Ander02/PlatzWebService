<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
pagina de login, onde o usuario é redirecionado se errar o login pela nav
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

        <!-- link Angular -->
        <script type="text/javascript" src="lib/angular/angular.js"></script>

        <!-- angular app script -->
        <script type="text/javascript" src="js/app.js"></script>

        <!-- UTIL -->
        <script src="js/util.js" type="text/javascript"></script>

        <!-- Controller -->

        <!-- link com o icone que fica no inicio do navegador -->
        <link rel="icon" href="img/logo.png">


    </head>

    <body>
        <!-- inicio do projeto aqui-->
    <ng-include src="'View/nav.html'"></ng-include>
    <div class="espaco"></div>


    <!--Naked Form-->
    <div class="col-md-6 form-login">
        <div class="card-block ">

            <!--Header-->
            <div class="text-xs-center">
                <h3><i class="fa fa-lock"></i> Login:</h3>
                <hr class="m-t-2 m-b-2">
            </div>

            <!--Body-->
            <form>
                <div class="md-form">
                    <i class="fa fa-envelope prefix"></i>
                    <input type="text" id="email" class="form-control">
                    <label for="email">Email</label>
                </div>

                <div class="md-form">
                    <i class="fa fa-lock prefix"></i>
                    <input type="password" id="senha" class="form-control">
                    <label for="senha">Senha</label>
                </div>

                <div class="text-xs-center">
                    <input type="submit" class="btn btn-warning btn-sm" value="Login">                    
                </div>

            </form>

        </div>


        <div class="modal-footer">
            <div class="options">
                <p>Não possui uma conta? <a href="#">Cadastre-se</a></p>
                <p>Esqueceu a <a href="#"> senha?</a></p>
            </div>
        </div>

    </div>
    <!--Naked Form-->


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



</body>

</html>