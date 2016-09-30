
angular.module("platz").controller("cidadeController", function ($scope, $http, toastr) {

    $scope.listarTodos = function () {
        $http.get(webService + "/cidades").then(function (response) {
            console.log(response.data);
            $scope.cidades = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar cidades"));
        });
    };

    //funções de atualizações e avisos
    function atualizar() {
        $scope.listarTodos();
    }
    window.onload = atualizar();

    function erro(mensagem) {
        toastr.error(mensagem, 'Erro', {
            progressBar: true,
            closeButton: true,
            timeOut: 0,
            extendTimeOut: 3000
        });
    }

});

