angular.module("platz").controller("assuntoController", function ($scope, $http, toastr) {

    $scope.listarTodos = function () {
        $http.get(webService + "/assuntos").then(function (response) {
            $scope.assuntosAll = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar assuntos"));
        });
    };
    $scope.listarDeletados = function () {
        $http.get(webService + "/assuntos/deletados").then(function (response) {
            $scope.assuntosDeletados = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar assuntos excluidos"));
        });
    };
    $scope.listarNaoDeletados = function () {
        $http.get(webService + "/assuntos/naoDeletados").then(function (response) {
            $scope.assuntos = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar assuntos"));
        });
    };

    $scope.alterar = function () {
        console.log($scope.assuntoEdicao);
        console.log($scope.assuntoEditado);
        $http.put(webService + "/assunto/" + $scope.assuntoEdicao.id, $scope.assuntoEditado).then(function (response) {

            atualizar();
            $scope.assuntoEdicao = null;
            alterado("Assunto editado com sucesso");
            sleep(1000);
            location.reload();
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao alterar assunto"));
        });
    }
    $scope.cadastrar = function () {

        $http.post(webService + "/assunto", $scope.assuntoCadastro).then(function (response) {
            atualizar();
            $scope.assuntoCadastro = null;
            sucesso("Assunto cadastrado com sucesso");
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao cadastrar assunto"));
        });
    };
    $scope.deletar = function () {
        $http.delete(webService + "/assunto/" + $scope.assuntoExclusaoId).then(function (response) {
            atualizar();
            excluido("Assunto deletado com sucesso");
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro deletar assunto"));
        });
    };
    $scope.recuperar = function () {
        $http.put(webService + "/assunto/recuperar/" + $scope.assuntoRecuperacaoId).then(function (response) {
            atualizar();
            sucesso("Assunto restaurado com sucesso");
        }, function (reponse) {
            erro(errorManager(response.config.url, response.status, "Erro ao recuperar assunto"));
        });
    };

    $scope.prepararRecuperacao = function (id) {
        $scope.assuntoRecuperacaoId = id;
    };

    $scope.cancelarRecuperacao = function () {
        $scope.assuntoRecuperacaoId = null;
    };
    $scope.prepararExclusao = function (id) {
        $scope.assuntoExclusaoId = id;
    };

    $scope.cancelarExclusao = function () {
        $scope.assuntoExclusaoId = null;
    };
    $scope.prepararEdicao = function (assunto) {
        $scope.assuntoEdicao = assunto;
    };

    $scope.cancelarEdicao = function () {
        $scope.assuntoEdicao = null;
    };

    function atualizar() {
        $scope.listarTodos();
        $scope.listarDeletados();
        $scope.listarNaoDeletados();
        $scope.cancelarEdicao();
        $scope.cancelarExclusao();
        $scope.cancelarRecuperacao();

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