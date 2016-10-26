
angular.module("platz").controller("cidadeController", function ($scope, $http, toastr) {

    $scope.listarTodos = function () {
        $http.get(webService + "/cidades").then(function (response) {            
            $scope.cidades = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar cidades"));
        });
    };

    //funções de atualizações e avisos
    function atualizar() {
         verificarToken($http, $scope, toastr, function () {
        });
        $scope.listarTodos();
    }
 window.onload = function () {
        console.log("onload");
        $scope.permicao = false;
        atualizar();
    };});