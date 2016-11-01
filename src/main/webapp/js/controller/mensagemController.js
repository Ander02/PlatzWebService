angular.module("platz").controller("mensagemController", function ($scope, $http, toastr, loginService, $rootScope) {

      $scope.listarFavoritas = function () {
        $http.get(webService + "/mensagens/marcadasNaoExcluidas").then(function (response) {
            $scope.mensagemFavoritas = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };
    $scope.listarNaoExcluidas = function () {
        $http.get(webService + "/mensagens/naoExcluidas").then(function (response) {
            $scope.mensagens = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };
    $scope.listarExcluidas = function () {
        $http.get(webService + "/mensagens/excluidas").then(function (response) {
            $scope.mensagensExcluidas = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };
    $scope.listarNaoLidas = function () {
        $http.get(webService + "/mensagens/naoLidasNaoExcluidas").then(function (response) {
            $scope.mensagensNaoLidas = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };
    $scope.listarLidas = function () {
        $http.get(webService + "/mensagens/lidasNaoExcluidas").then(function (response) {
            $scope.mensagensLidas = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };
    $scope.buscarPeloId = function (id) {
        $http.get(webService + "/mensagem/" + id).then(function (response) {
            $scope.mensagem = response.data;
        }, function (response) {
            info(toastr, errorManager(response.config.url, response.status, "Erro ao buscar mensagem pelo id"));
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
    $scope.favoritar = function (id) {
        $http.put(webService + "/mensagem/marcar/" + id).then(function (response) {
            confirmacao(toastr, "Favoritado");
        }, function (response) {
            info(toastr, errorManager(response.config.url, response.status, "Erro ao favoritar mensagem"));
        });
    };
    $scope.desfavoritar = function (id) {
        $http.put(webService + "/mensagem/desmarcar/" + id).then(function (response) {
            confirmacao(toastr, "Desfavoritado");
        }, function (response) {
            info(toastr, errorManager(response.config.url, response.status, "Erro ao desmarcar mensagem"));
        });
    };
    $scope.excluirDefinitivamente = function (id) {
        $http.delete(webService + "/mensagem/" + $scope.mensagemExclusaoDefinitivaId).then(function (response) {
            info(toastr, "Mensagem excluida definitivamente");
            atualizar();
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao excluir mensagem"));
        });
    };
    $scope.marcarExcluida = function () {
        $http.put(webService + "/mensagem/excluir/" + $scope.mensagemExclusaoId).then(function (response) {
            sucesso(toastr, "Mensagem excluida com sucesso");
            atualizar();
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao excluir mensagem"));
        });
    };
    $scope.visualizar = function (id) {
        $http.put(webService + "/mensagem/visualizar/" + id).then(function (response) {

        }, function (response) {

        });
    };
    $scope.Cancelarvisualizacao = function (id) {
        $http.put(webService + "/mensagem/cancelarVisualizar/" + id).then(function (response) {

        }, function (response) {

        });
    };
    $scope.restaurar = function () {
        $http.put(webService + "/mensagem/restaurar/" + $scope.mensagemRecuperacaoId).then(function (response) {
            atualizar();
            sucesso(toastr, "Mensagem restaurada com sucesso");
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao recuperar mensagem"));
        });
    };

    //métodos que envolvem outros tipos de objetos
    $scope.listarAssuntosNaoDeletados = function () {
        $http.get(webService + "/assuntos/naoDeletados").then(function (response) {
            $scope.assuntos = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar assuntos"));
        });
    };
    $scope.alterarFavorito = function (mensagem) {
        if (mensagem.marcado == true) {
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

    $scope.responder = function (id) {
        espere(toastr, "Enviando e-mail, por favor aguarde...");
        $http.post(webService + "/mensagem/" + id, $scope.resposta).then(function (response) {
            sucesso(toastr, "mensagem respondida");
            $scope.resposta = null;
        }, function (response) {
            aviso(toastr, "falha ao reponder mensagem");
        });
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
        console.log("atualizar");
        loginService.verificarToken($http, toastr, "Administrador", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();            
        });
        $scope.listarAssuntosNaoDeletados();
        $scope.listarLidas();
        $scope.listarNaoExcluidas();
        $scope.listarExcluidas();
        $scope.listarNaoLidas();
        $scope.listarFavoritas();
    }

    window.onload = function () {
        console.log("onload");
        $scope.permicao = false;
        atualizar();
    };

});