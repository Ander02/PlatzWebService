angular.module("platz").controller("mensagemController", function ($scope, $http, toastr) {

    $scope.listarTodos = function () {
        $http.get(webService + "/mensagens").then(function (response) {
            console.log(response.data);
            $scope.mensagens = response.data;
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

    //funções de atualizações e avisos
    function atualizar() {
        $scope.listarTodos();
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

