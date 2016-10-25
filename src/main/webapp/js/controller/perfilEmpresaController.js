angular.module("platz").controller("perfilEmpresaController", function ($scope, $http, toastr) {

//funções de atualizações
    function atualizar() {
        console.log("atualizar");
        verificarToken($http, $scope, toastr, function () {
            $scope.buscaEmpresa();
        });
    }

    $scope.buscaEmpresa = function () {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id).then(function (response) {
            $scope.empresa = response.data;
            $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
            $scope.eventosEmpresa();
        }, function (response) {
            console.log(response.data);
        });
    };

    $scope.eventosEmpresa = function () {
        $http.get(webService + "/eventos/empresa/" + $scope.empresa.id).then(function (response) {
            $scope.eventos = response.data;
            console.log($scope.eventos);
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao buscar eventos da empresa"));
        });
    };

    $scope.deslogar = function () {
        $http.post(webService + "/logoff", null, gerarHeaders(document.getElementById("token").value)).then(function (response) {
            info(toastr, "logoff efetuado");
            location.href = "../index.jsp";
        }, function (response) {

        });
    };

    window.onload = function () {
        console.log("onload");
        $scope.permicao = false;
        atualizar();
    };




});
