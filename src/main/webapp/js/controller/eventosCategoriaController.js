angular.module("platz").controller("eventosCategoriaController", function ($scope, $http, toastr, loginService) {

    idCategoria = document.getElementById("idCategoria").value;

    $scope.eventosPorCategoria = function () {
        $http.get(webService + "/eventos/categoria/" + idCategoria).then(function (response) {
            $scope.eventos = response.data;
        }, function (response) {
        });
    };

    $scope.categoriaId = function () {
        $http.get(webService + "/categoria/" + idCategoria).then(function (response) {
            $scope.categoria = response.data;
        }, function (response) {
            info(toastr, "falha ao carrregar categoria");
        });
    };

    function atualizar() {
        loginService.verificarToken($http, toastr, "Livre", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.eventosPorCategoria();
        $scope.categoriaId();

    }

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };

    $scope.buscarImagemCapa = function (id) {
        return webService + "/evento/imagemCapa/" + id;
    };


});
