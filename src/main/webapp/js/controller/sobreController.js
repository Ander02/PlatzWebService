angular.module("platz").controller("sobreController", function ($scope, $http, toastr) {


    //métodos que envolvem outros tipos de objetos
    $scope.listarAssuntosNaoDeletados = function () {
        $http.get(webService + "/assuntos/naoDeletados").then(function (response) {
            $scope.assuntos = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar assuntos"));
        });
    };
    $scope.cadastrar = function () {
        $http.post(webService + "/mensagem", $scope.mensagemCadastro).then(function (response) {
            atualizar();
            $scope.mensagemCadastro = null;
            sucesso(toastr, "Mensagem Enviada com sucesso");
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao enviar Mensagem "));
        });
    };

//funções de atualizações
    function atualizar() {
        $scope.listarAssuntosNaoDeletados();
    }

    window.onload = function () {
        atualizar();
    };

});