angular.module("platz").controller("empresaEspecificaController", function ($scope, $http, toastr, loginService) {

    var idEmpresa = document.getElementById("idEmpresa").value;

    $scope.empresaId = function () {
        $http.get(webService + "/empresa/" + idEmpresa).then(function (response) {
            $scope.empresa = response.data;
            $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
        }, function () {
            erro(toastr, "erro ao buscar empresa");
        });
    };

    $scope.eventosEmpresa = function () {
        $http.get(webService + "/eventos/empresa/" + idEmpresa).then(function (response) {
            $scope.eventos = response.data;
        }, function () {
            erro(toastr, "erro ao buscar eventos da empresa");
        });
    };


    $scope.buscarImagemCapa = function (id) {
        return webService + "/evento/imagemCapa/" + id;
    };


    //funções de atualizações e avisos
    function atualizar() {
        loginService.verificarToken($http, toastr, "Livre", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });

        $scope.empresaId();
        $scope.eventosEmpresa();

    }

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };
});
