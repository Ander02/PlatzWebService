angular.module("platz").controller("eventoEspecificoController", function ($scope, $http, toastr, loginService) {
    id = document.getElementById("idEvento").value;

    $scope.eventoEspecifico = function () {

        $http.get(webService + "/evento/" + id).then(function (response) {
            $scope.evento = response.data;
            $scope.imagemCapa = webService + "/evento/imagemCapa/" + id;
        }, function (response) {
            console.log(response.data);
        });
    };
    $scope.avaliar = function (nota) {
        console.log(nota);
    };

    $scope.listarPostagem = function () {
        $http.get(webService + "/postagens", loginService.getHeaders()).then(function (response) {
            $scope.postagens = response.data;            
        }, function () {

        });
    };

    $scope.imagemPostagem = function (conta) {

        if (conta.perfil === "Usuario") {
            console.log("A");
            $http.get(webService + "/usuario/conta/" + conta.id, loginService.getHeaders()).then(function (response) {
                console.log("B");
                console.log(response.data);
                return webService + "/usuario/imagem/" + response.data.id;
            }, function () {

            });
        }
    };
    
    
    $scope.nomePostagem = function (conta) {

    };

    $scope.getMedia = function () {
        $http.get(webService + "/avaliacao/evento/media/" + id).then(function (response) {
            $scope.media = parseFloat(response.data);
            $scope.mediaArredondada = Math.round($scope.media);
        }, function (response) {
            console.log(response.data);
        });
    };
    function atualizar() {
        loginService.verificarToken($http, toastr, "Livre", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.eventoEspecifico();
        $scope.getMedia();
        $scope.listarPostagem();

    }

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };


});
