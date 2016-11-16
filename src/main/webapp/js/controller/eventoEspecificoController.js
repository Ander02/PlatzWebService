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

        if ($scope.usuario !== null && $scope.usuario !== "" && typeof $scope.usuario !== undefined) {
            console.log($scope.usuario.id);
            avaliacao = {
                nota: nota,
                eventoId: id,
                usuarioId: $scope.usuario.id
            };

            $http.post(webService + "/avaliar", avaliacao, loginService.getHeaders()).then(function (response) {
                atualizar();
            }, function () {

            });

        } else {
            pedidoLogin();
        }

    };

    var pedidoLogin = function () {
        generateToastr(toastr, "Por favor, realize o login como usuario para ter acesso a essa funcionalidade", "info", "Realize o login", true, true, 0, 2500);
    };

    $scope.listarPostagem = function () {
        $http.get(webService + "/postagens", loginService.getHeaders()).then(function (response) {
            $scope.postagens = response.data;
        }, function () {

        });
    };

    $scope.buscaUsuario = function () {
        $http.get(webService + "/usuario/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.usuario = response.data;

            $http.get(webService + "/avaliacao/evento/" + id + "/usuario/" + $scope.usuario.id, loginService.getHeaders()).then(function (response) {
                $scope.notaUsuario = response.data.nota;
            }, function () {
            });

        }, function () {
        });
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
            if ($scope.conta.perfil === "Usuario") {
                $scope.buscaUsuario();
            }
        });
        $scope.eventoEspecifico();
        $scope.getMedia();
        $scope.listarPostagem();
    }
    ;

    window.onload = function () {
        $scope.usuario = "";
        $scope.permicao = false;
        atualizar();
    };


});
