angular.module("platz").controller("perfilEmpresaController", function ($scope, $http, toastr) {

//funções de atualizações
    function atualizar() {
        console.log("atualizar");
        verificarToken($http, $scope, toastr, function () {
            $scope.buscaEmpresa();
        });
    }

    $scope.buscaEmpresa = function (conta) {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id).then(function (response) {
            $scope.empresa = response.data;
        }, function (response) {
            console.log(response.data);
        });
    };

    window.onload = function () {
        console.log("onload");
        $scope.permicao = false;
        atualizar();

    };
});
