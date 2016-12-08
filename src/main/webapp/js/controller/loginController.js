angular.module("platz").controller("loginController", function ($scope, $http, toastr, loginService, validacaoService) {

    $scope.logar = function () {
        login = {
            email: document.getElementById("email").value,
            senha: document.getElementById("senha").value
        };
        $http.post(webService + "/login", login).then(function (response) {
            $scope.contaLogin = response.data;
            location.href = "sessao.jsp?token=" + $scope.contaLogin.token + "&perfil=" + $scope.contaLogin.perfil;
        }, function () {
            erro(toastr, "usuario ou senha incorreto");
        });
    };

    $scope.logarPageLogin = function () {
        login = {
            email: document.getElementById("emailLogin").value,
            senha: document.getElementById("senhaLogin").value
        };

        $http.post(webService + "/login", login).then(function (response) {
            $scope.contaLogin = response.data;
            location.href = "sessao.jsp?token=" + $scope.contaLogin.token + "&perfil=" + $scope.contaLogin.perfil;
        }, function () {
            erro(toastr, "usuario ou senha incorreto");
        });
    };

    $scope.deslogar = function () {
        $http.post(webService + "/logoff", null, loginService.getHeaders()).then(function () {
            info(toastr, "logoff efetuado");
            location.href = "/quebraSessao.jsp";
        }, function () {
        });
    };


    $scope.getConta = function () {

        var token = document.getElementById("token").value;
        if (token !== null && token !== "") {
            $http.get(webService + "/conta/token/" + token, {
                headers: {
                    Authorization: "Bearer " + token
                }
            }).then(function (response) {
                $scope.conta = response.data;

                if ($scope.conta.perfil === "Empresa") {
                    $http.get(webService + "/empresa/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
                        $scope.empresa = response.data;
                        $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
                    }, function () {
                    });
                } else if ($scope.conta.perfil === "Usuario") {
                    $http.get(webService + "/usuario/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
                        $scope.usuario = response.data;
                        $scope.imagemPerfil = webService + "/usuario/imagem/" + $scope.usuario.id;
                    }, function () {
                    });
                }

            }, function () {
                $scope.conta = null;
            });
        }
    };
    $scope.getConta();

    $scope.alterarSenha = function (contaEditada) {
        if (contaEditada.senha === contaEditada.confirmaSenha) {
            if (!validacaoService.vazio(toastr, contaEditada.senha, "Senha") && validacaoService.comprimento(toastr, contaEditada.senha, 4, 40, "Senha")) {
                $http.put(webService + "/conta/senha/" + $scope.conta.id, contaEditada, loginService.getHeaders()).then(function (response) {
                    $scope.conta = response.data;
                    sucesso(toastr, "senha editada com sucesso");
                }, function () {
                    aviso(toastr, "Erro ao editar senha, tente novamente mais tarde");
                });
            }
        } else {
            contaEditada.senha = "";
            contaEditada.confirmaSenha = "";
            aviso(toastr, "A senha não são iguais, por favor digite-as novamente");
        }
    };

});