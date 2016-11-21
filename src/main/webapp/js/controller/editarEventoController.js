angular.module("platz").controller("editarEventoController", function ($scope, $http, toastr, loginService) {
    id = document.getElementById("idEvento").value;

    $scope.eventoEspecifico = function () {
        $http.get(webService + "/evento/" + id).then(function (response) {
            $scope.evento = response.data;
            $scope.imagemCapa = webService + "/evento/imagemCapa/" + id;
        }, function (response) {
            console.log(response.data);
        });
    };

    $scope.buscaEmpresa = function () {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.empresa = response.data;
            $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
        }, function (response) {
        });
    };


    $scope.deslogar = function () {
        $http.post(webService + "/logoff", null, loginService.getHeaders()).then(function (response) {
            info(toastr, "logoff efetuado");
            location.href = "../index.jsp";
        }, function (response) {

        });
    };

    function atualizar() {
        loginService.verificarToken($http, toastr, "Empresa", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
            $scope.buscaEmpresa();
        });
    }

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };

});
