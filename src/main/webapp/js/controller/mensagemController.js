angular.module("platz").controller("mensagemController", function ($scope, $http, toastr) {

    $scope.listarTodos = function () {
        $http.get(webService + "/mensagens").then(function (response) {
            $scope.mensagensAll = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };

    $scope.listarFavoritas = function () {
        $http.get(webService + "/mensagens/marcadas").then(function (response) {
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
        $http.get(webService + "/mensagens/naoLidas").then(function (response) {
            $scope.mensagensNaoLidas = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };

    $scope.listarLidas = function () {
        $http.get(webService + "/mensagens/lidas").then(function (response) {
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
    
    $scope.listarAssuntosNaoDeletados = function () {
        $http.get(webService + "/assuntos/naoDeletados").then(function (response) {
            $scope.assuntos = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar assuntos"));
        });
    };

    //funções de atualizações e avisos
    function atualizar() {
        $scope.listarAssuntosNaoDeletados();
        $scope.listarLidas();
        $scope.listarNaoExcluidas();
        $scope.listarExcluidas();
        $scope.listarNaoLidas();
        $scope.listarTodos();
        $scope.listarFavoritas();
        //$scope.desfavoritar("57c5e2eb05b3981944dbe3e7");
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
    function confirmacao(mensagem) {
        toastr.success(mensagem, 'Sucesso', {
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

