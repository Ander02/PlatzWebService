angular.module("platz").controller("eventosCategoriaController", function ($scope, $http, toastr) {

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
    window.onload = function () {
        $scope.eventosPorCategoria();
        $scope.categoriaId();
    };

});
