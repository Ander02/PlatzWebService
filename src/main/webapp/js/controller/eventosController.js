angular.module("platz").controller("eventosController", function ($scope, $http, toastr) {

    $scope.listarCategoriasNaoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas").then(function (response) {
            $scope.categorias = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar categorias"));
        });
    };

    $scope.listarEventos = function () {
        $http.get(webService + "/eventos").then(function (response) {
            console.log(response.data);
        }, function (response) {
            console.log(response.data);
        });
    };

    $scope.baixarImagem = function (id) {
        return webService + "/categoria/imagem/" + id;
    };

    window.onload = function () {
        $scope.listarEventos();
        $scope.listarCategoriasNaoExcluidas();
    };

});