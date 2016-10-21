angular.module("platz").controller("eventosCategoriaController", function ($scope, $http, toastr) {

    idCategoria = document.getElementById("idCategoria").value;

    $scope.eventosPorCategoria = function () {
        $http.get(webService + "/eventos/categoria/" + idCategoria).then(function (response) {
            $scope.eventos = response.data;
            console.log($scope.eventos);
        }, function (response) {
            console.log(response.data);
        });
    };

    $scope.categoriaId = function () {
        $http.get(webService + "/categoria/" + idCategoria).then(function (response) {
            $scope.categoria = response.data;
        }, function (response) {
            console.log(response.data);
        });
    };

    $scope.eventosPorCategoria();
    $scope.categoriaId();
    ;
});
