angular.module("platz").controller("mensagemController", function ($scope, $http, toastr) {

    $scope.listarTodos = function () {
        $http.get(webService + "/mensagens").then(function (response) {
            $scope.mensagensAll = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };

    $scope.listarFavoritas = function () {
        $http.get(webService + "/mensagens/marcadasNaoExcluidas").then(function (response) {
            $scope.mensagemFavoritas = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };

    $scope.listarNaoExcluidas = function () {
        $http.get(webService + "/mensagens/naoExcluidas").then(function (response) {
            $scope.mensagens = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };

    $scope.listarExcluidas = function () {
        $http.get(webService + "/mensagens/excluidas").then(function (response) {
            $scope.mensagensExcluidas = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };

    $scope.listarNaoLidas = function () {
        $http.get(webService + "/mensagens/naoLidasNaoExcluidas").then(function (response) {
            $scope.mensagensNaoLidas = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };

    $scope.listarLidas = function () {
        $http.get(webService + "/mensagens/lidasNaoExcluidas").then(function (response) {
            $scope.mensagensLidas = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };

    $scope.buscarPeloId = function (id) {
        $http.get(webService + "/mensagem/" + id).then(function (response) {
            $scope.mensagem = response.data;
        }, function (response) {
            info(errorManager(response.config.url, response.status, "Erro ao buscar mensagem pelo id"));
        });
    };

    $scope.cadastrar = function () {
        $http.post(webService + "/mensagem", $scope.mensagemCadastro).then(function (response) {
            atualizar();
            $scope.mensagemCadastro = null;
            sucesso("Mensagem Enviada com sucesso");
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao enviar Mensagem "));
        });
    };

    $scope.favoritar = function (id) {
        $http.put(webService + "/mensagem/marcar/" + id).then(function (response) {
            confirmacao("Favoritado");
        }, function (response) {
            info(errorManager(response.config.url, response.status, "Erro ao favoritar mensagem"));
        });
    };

    $scope.desfavoritar = function (id) {
        $http.put(webService + "/mensagem/desmarcar/" + id).then(function (response) {
            confirmacao("Desfavoritado");
        }, function (response) {
            info(errorManager(response.config.url, response.status, "Erro ao desmarcar mensagem"));
        });
    };

    $scope.excluirDefinitivamente = function (id) {
        $http.delete(webService + "/mensagem/" + $scope.mensagemExclusaoDefinitivaId).then(function (response) {
            info("Mensagem excluida definitivamente");
            atualizar();
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao excluir mensagem"));
        });
    };

    $scope.marcarExcluida = function () {
        $http.put(webService + "/mensagem/excluir/" + $scope.mensagemExclusaoId).then(function (response) {
            sucesso("Mensagem excluida com sucesso");
            atualizar();
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao excluir mensagem"));
        });
    };
    $scope.visualizar = function (id) {
        $http.put(webService + "/mensagem/visualizar/" + id).then(function (response) {

        }, function (response) {

        });
    }
    $scope.Cancelarvisualizacao = function (id) {
        $http.put(webService + "/mensagem/cancelarVisualizar/" + id).then(function (response) {

        }, function (response) {

        });
    }
    $scope.restaurar = function () {
        $http.put(webService + "/mensagem/restaurar/" + $scope.mensagemRecuperacaoId).then(function (response) {
            atualizar();
            sucesso("Mensagem restaurada com sucesso");
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao recuperar mensagem"));
        });
    };

    //métodos que envolvem outros tipos de objetos
    $scope.listarAssuntosNaoDeletados = function () {
        $http.get(webService + "/assuntos/naoDeletados").then(function (response) {
            $scope.assuntos = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar assuntos"));
        });
    };

    $scope.alterarFavorito = function (mensagem) {
        if (mensagem.marcado == true) {
            $scope.desfavoritar(mensagem.id);
        } else {
            $scope.favoritar(mensagem.id);
        }
        atualizar();
    }

    $scope.lerMensagem = function (id) {
        $scope.buscarPeloId(id);
        $scope.visualizar(id);
        atualizar();
    }
    $scope.responder = function (id) {
        espere("Enviando e-mail, por favor aguarde...");
        $http.post(webService + "/mensagem/" + id, $scope.resposta).then(function (response) {
            sucesso("mensagem respondida");
            $scope.resposta = null;
        }, function (response) {
            aviso("falha ao reponder mensagem");
        });
    }

    $scope.prepararExclusaoDefinitiva = function (id) {
        $scope.mensagemExclusaoDefinitivaId = id;
    }
    $scope.cancelarExclusaoDefinitiva = function () {
        $scope.mensagemExclusaoDefinitivaId = null;
    }
    $scope.prepararExclusao = function (id) {
        $scope.mensagemExclusaoId = id;
    }
    $scope.cancelarExclusao = function () {
        $scope.mensagemExclusaoId = null;
    }
    $scope.prepararRecuperacao = function (id) {
        $scope.mensagemRecuperacaoId = id;
    }

    $scope.cancelarRecuperacao = function () {
        $scope.mensagemRecuperacaoId = null;
    }

    //funções de atualizações e avisos
    function atualizar() {
        $scope.listarAssuntosNaoDeletados();
        $scope.listarLidas();
        $scope.listarNaoExcluidas();
        $scope.listarExcluidas();
        $scope.listarNaoLidas();
        $scope.listarTodos();
        $scope.listarFavoritas();
    }
    window.onload = atualizar();

    function sucesso(mensagem) {
        toastr.success(mensagem, 'Sucesso', {
            progressBar: true,
            closeButton: true,
            timeOut: 5000,
            extendTimeOut: 2000
        });
    }
    function espere(mensagem) {
        toastr.info(mensagem, 'Enviando', {
            progressBar: true,
            closeButton: true,
            timeOut: 0,
            extendTimeOut: 5000
        });
    }
    function confirmacao(mensagem) {
        toastr.success(mensagem, 'Confirmação', {
            progressBar: true,
            closeButton: true,
            timeOut: 1000,
            extendTimeOut: 500
        });
    }
    function excluido(mensagem) {
        toastr.success(mensagem, 'Deletado', {
            progressBar: true,
            closeButton: true,
            timeOut: 5000,
            extendTimeOut: 2000
        });
    }
    function alterado(mensagem) {
        toastr.success(mensagem, 'Alterado', {
            progressBar: true,
            closeButton: true,
            timeOut: 5000,
            extendTimeOut: 2000
        });
    }

    function info(mensagem) {
        toastr.info(mensagem, 'Informações', {
            progressBar: true,
            closeButton: true,
            timeOut: 5000,
            extendTimeOut: 2000
        });
    }
    function erro(mensagem) {
        toastr.error(mensagem, 'Erro', {
            progressBar: true,
            closeButton: true,
            timeOut: 0,
            extendTimeOut: 3000
        });
    }
    function aviso(mensagem) {
        toastr.warning(mensagem, 'Aviso', {
            progressBar: true,
            closeButton: true,
            timeOut: 0,
            extendTimeOut: 3000
        });
    }
});

