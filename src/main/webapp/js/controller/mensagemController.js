angular.module("platz").controller("mensagemController", function ($scope, $http, toastr, loginService, validacaoService) {

    $scope.listarFavoritas = function () {
        $http.get(webService + "/mensagens/marcadasNaoExcluidas", loginService.getHeaders()).then(function (response) {
            $scope.mensagemFavoritas = response.data;
        }, function () {
        });
    };
    $scope.listarNaoExcluidas = function () {
        $http.get(webService + "/mensagens/naoExcluidas", loginService.getHeaders()).then(function (response) {
            $scope.mensagens = response.data;
        }, function () {
        });
    };
    $scope.listarExcluidas = function () {
        $http.get(webService + "/mensagens/excluidas", loginService.getHeaders()).then(function (response) {
            $scope.mensagensExcluidas = response.data;
        }, function () {
        });
    };
    $scope.listarNaoLidas = function () {
        $http.get(webService + "/mensagens/naoLidasNaoExcluidas", loginService.getHeaders()).then(function (response) {
            $scope.mensagensNaoLidas = response.data;
        }, function () {
        });
    };
    $scope.listarLidas = function () {
        $http.get(webService + "/mensagens/lidasNaoExcluidas", loginService.getHeaders()).then(function (response) {
            $scope.mensagensLidas = response.data;
        }, function () {
        });
    };
    $scope.buscarPeloId = function (id) {
        $http.get(webService + "/mensagem/" + id, loginService.getHeaders()).then(function (response) {
            $scope.mensagem = response.data;
        }, function () {
            info(toastr, "Erro ao buscar mensagem, tente novamente mais tarde");
        });
    };

    $scope.favoritar = function (id) {
        $http.put(webService + "/mensagem/marcar/" + id, null, loginService.getHeaders()).then(function () {
            confirmacao(toastr, "Favoritado");
            atualizar();
        }, function () {
            aviso(toastr, "Erro ao favoritar mensagem");
        });
    };
    $scope.desfavoritar = function (id) {
        $http.put(webService + "/mensagem/desmarcar/" + id, null, loginService.getHeaders()).then(function () {
            confirmacao(toastr, "Desfavoritado");
            atualizar();
        }, function () {
            aviso(toastr, "Erro ao desmarcar mensagem");
        });
    };
    $scope.excluirDefinitivamente = function (id) {
        $http.delete(webService + "/mensagem/" + $scope.mensagemExclusaoDefinitivaId, loginService.getHeaders()).then(function () {
            info(toastr, "Mensagem excluida definitivamente");
            atualizar();
        }, function () {
            erro(toastr, "Erro ao excluir mensagem, tente novamente mais tarde");
        });
    };
    $scope.marcarExcluida = function () {
        $http.put(webService + "/mensagem/excluir/" + $scope.mensagemExclusaoId, null, loginService.getHeaders()).then(function () {
            sucesso(toastr, "Mensagem excluida com sucesso");
            atualizar();
        }, function () {
            erro(toastr, "Erro ao excluir mensagem, tente novamente mais tarde");
        });
    };
    $scope.visualizar = function (id) {
        $http.put(webService + "/mensagem/visualizar/" + id, null, loginService.getHeaders()).then(function () {
            atualizar();
        }, function () {
        });
    };

    $scope.restaurar = function () {
        $http.put(webService + "/mensagem/restaurar/" + $scope.mensagemRecuperacaoId, null, loginService.getHeaders()).then(function () {
            atualizar();
            sucesso(toastr, "Mensagem restaurada com sucesso");
        }, function () {
            erro(toastr, "Erro ao recuperar mensagem, tente novamente mais tarde");
        });
    };

    $scope.alterarFavorito = function (mensagem) {
        if (mensagem.marcado === true) {
            $scope.desfavoritar(mensagem.id);
        } else {
            $scope.favoritar(mensagem.id);
        }
        atualizar();
    };

    $scope.lerMensagem = function (id) {
        $scope.buscarPeloId(id);
        $scope.visualizar(id);
        atualizar();
    };

    $scope.responder = function (id, resposta) {
        if (validacaoService.comprimento(toastr, resposta, 10, 4096, "resposta") && validacaoService.conteudo(toastr, resposta, "resposta")) {

            espere(toastr, "Enviando e-mail, por favor aguarde...");
            $http.post(webService + "/mensagem/" + id, resposta, loginService.getHeaders()).then(function () {
                sucesso(toastr, "mensagem respondida");
                $scope.resposta = null;
            }, function () {
                aviso(toastr, "falha ao reponder mensagem");
            });

        }
    };

    $scope.prepararExclusaoDefinitiva = function (id) {
        $scope.mensagemExclusaoDefinitivaId = id;
    };
    $scope.cancelarExclusaoDefinitiva = function () {
        $scope.mensagemExclusaoDefinitivaId = null;
    };
    $scope.prepararExclusao = function (id) {
        $scope.mensagemExclusaoId = id;
    };
    $scope.cancelarExclusao = function () {
        $scope.mensagemExclusaoId = null;
    };
    $scope.prepararRecuperacao = function (id) {
        $scope.mensagemRecuperacaoId = id;
    };

    $scope.cancelarRecuperacao = function () {
        $scope.mensagemRecuperacaoId = null;
    };

//funções de atualizações
    function atualizar() {
        loginService.verificarToken($http, toastr, "Administrador", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.listarLidas();
        $scope.listarNaoExcluidas();
        $scope.listarExcluidas();
        $scope.listarNaoLidas();
        $scope.listarFavoritas();
        $scope.cancelarExclusao();
        $scope.cancelarExclusaoDefinitiva();
        $scope.cancelarRecuperacao();
    }

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };

});