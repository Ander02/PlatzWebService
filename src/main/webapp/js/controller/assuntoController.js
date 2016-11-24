angular.module("platz").controller("assuntoController", function ($scope, $http, toastr, loginService, validacaoService) {

    $scope.listarDeletados = function () {
        $http.get(webService + "/assuntos/deletados", loginService.getHeaders()).then(function (response) {
            $scope.assuntosDeletados = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar assuntos excluidos"));
        });
    };
    $scope.listarNaoDeletados = function () {
        $http.get(webService + "/assuntos/naoDeletados", loginService.getHeaders()).then(function (response) {
            $scope.assuntos = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar assuntos"));
        });
    };

    $scope.alterar = function (assuntoEditado) {
        if (validacaoService.comprimento($scope.assuntoEdicao.nome, 0, 35) && validacaoService.conteudo($scope.assuntoEdicao.nome)) {
            $http.put(webService + "/assunto/" + $scope.assuntoEdicao.id, assuntoEditado, loginService.getHeaders()).then(function () {

                atualizar();
                $scope.assuntoEdicao = new Object();
                alterado(toastr, "Assunto editado com sucesso");
                sleep(1000);
                //location.reload();
            }, function (response) {
                erro(toastr, errorManager(response.config.url, response.status, "Erro ao alterar assunto"));
            });
        } else {
            aviso(toastr, "o nome do assunto deve ter no maximo 35 digitos");
        }

    };

    $scope.cadastrar = function (assuntoCadastro) {
        if (validacaoService.comprimento(assuntoCadastro.nome, 0, 35) && validacaoService.conteudo(assuntoCadastro.nome)) {
            $http.post(webService + "/assunto", assuntoCadastro, loginService.getHeaders()).then(function () {
                atualizar();
                $scope.assuntoCadastro = "";
                sucesso(toastr, "Assunto cadastrado com sucesso");
            }, function (response) {
                erro(toastr, errorManager(response.config.url, response.status, "Erro ao cadastrar assunto"));
            });
        } else {
            aviso(toastr, "o nome do assunto deve ter no maximo 35 digitos");
        }
    };
    
    $scope.deletar = function () {
        $http.delete(webService + "/assunto/" + $scope.assuntoExclusaoId, loginService.getHeaders()).then(function (response) {
            atualizar();
            excluido(toastr, "Assunto deletado com sucesso");
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro deletar assunto"));
        });
    };
    $scope.recuperar = function () {
        $http.put(webService + "/assunto/recuperar/" + $scope.assuntoRecuperacaoId, null, loginService.getHeaders()).then(function (response) {
            atualizar();
            sucesso(toastr, "Assunto restaurado com sucesso");
        }, function (reponse) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao recuperar assunto"));
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
        loginService.verificarToken($http, toastr, "Administrador", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.listarDeletados();
        $scope.listarNaoDeletados();
        $scope.cancelarEdicao();
        $scope.cancelarExclusao();
        $scope.cancelarRecuperacao();

    }
    window.onload = function () {
        console.log("onload");
        $scope.permicao = false;
        atualizar();
    };
});
