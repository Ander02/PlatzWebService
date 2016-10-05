angular.module("platz").controller("contaController", function ($scope, $http, toastr) {
    $scope.listarAdministradores = function () {
        $http.get(webService + "/contas").then(function (response) {
            $scope.admintradores = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar Admintradores"));
        })
    }
});