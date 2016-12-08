angular.module("platz").controller("sobreController", function ($scope, $http, toastr, loginService) {

    //métodos que envolvem outros tipos de objetos
    $scope.listarAssuntosNaoDeletados = function () {
        $http.get(webService + "/assuntos/naoDeletados").then(function (response) {
            $scope.assuntos = response.data;
        }, function () {
        });
    };
    $scope.cadastrar = function () {
        $http.post(webService + "/mensagem", $scope.mensagemCadastro).then(function (response) {
            atualizar();
            $scope.mensagemCadastro = null;
            sucesso(toastr, "Mensagem Enviada com sucesso");
        }, function () {
            erro(toastr, "Erro ao enviar Mensagem ");
        });
    };

    function atualizar() {
        loginService.verificarToken($http, toastr, "Livre", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.listarAssuntosNaoDeletados();
    }

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };
});