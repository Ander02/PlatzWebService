angular.module("platz").controller("empresaEspecificaController", function ($scope, $http, toastr) {

    var idEmpresa = document.getElementById("idEmpresa").value;

    $scope.empresaId = function () {
        $http.get(webService + "/empresa/" + idEmpresa).then(function (response) {
            $scope.empresa = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao buscar empresa"));
        });
    };

    $scope.eventosEmpresa = function () {
        $http.get(webService + "/eventos/empresa/" + idEmpresa).then(function (response) {
            $scope.eventos = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao buscar eventos da empre"));
        });
    };
    
    window.onload = function () {
        $scope.empresaId();
        $scope.eventosEmpresa();
    };

});
