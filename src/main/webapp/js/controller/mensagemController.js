angular.module("platz").controller("mensagemController", function ($scope, $http, toastr) {

    $scope.listarTodos = function () {
        $http.get(webService + "/mensagens").then(function (response) {
            $scope.mensagens = response.data;
            console.log("muda saporra");
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };
    $scope.listarNaoDeletados = function () {
        $http.get(webService + "/------------").then(function (response) {
            $scope.mensagensAll = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
        });
    };
    $scope.listarDeletados = function () {
        $http.get(webService + "/mensagens/excluidas").then(function (response) {
            $scope.mensagensExcluidas = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar mensagens"));
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
    $scope.listarAssuntosNaoDeletados = function () {
        $http.get(webService + "/assuntos/naoDeletados").then(function (response) {
            $scope.assuntos = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar assuntos"));
        });
    };


    //funções de atualizações e avisos
    function atualizar() {
        $scope.listarTodos();
        $scope.listarAssuntosNaoDeletados();
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

