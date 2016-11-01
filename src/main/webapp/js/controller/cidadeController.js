
angular.module("platz").controller("cidadeController", function ($scope, $http, toastr, loginService) {

    $scope.listarTodos = function () {
        $http.get(webService + "/cidades", loginService.getHeaders()).then(function (response) {
            $scope.cidades = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar cidades"));
        });
    };

    //funções de atualizações e avisos
    function atualizar() {
        loginService.verificarToken($http, toastr, "Administrador", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.listarTodos();
    }

    window.onload = function () {

        console.log("onload");
        $scope.permicao = false;
        atualizar();
    };
});