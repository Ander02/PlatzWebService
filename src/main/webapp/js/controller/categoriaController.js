angular.module("platz").controller("categoriaController", function ($scope, $http, toastr) {

    //Faz um Get no Web Service recebendo uma URL

    //funções que realizam consulta no webService
    $scope.listarTodos = function () {
        $http.get(webService + "/categorias").then(function (response) {
            $scope.categoriasAll = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar categorias"));
        });
    };
    $scope.listarExcluidas = function () {
        $http.get(webService + "/categorias/excluidas").then(function (response) {
            $scope.categoriasExcluidas = response.data;
        }, function (response) {            
            erro(errorManager(response.config.url, response.status, "Erro ao listar categorias excluidas"));
        });
    };
    $scope.listarNaoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas").then(function (response) {
            $scope.categorias = response.data;
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao listar categorias"));
        });
    };

    $scope.alterar = function () {
        $http.put(webService + "/categoria/" + $scope.categoriaEdicao.id, $scope.categoriaEditada).then(function (response) {
            atualizar();
            $scope.categoriaEditada = null;
            alterado("Categoria editar com sucesso");
            sleep(1000);
            location.reload();
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao alterar categoria"));
        });
    }
    $scope.cadastrar = function () {

        $http.post(webService + "/categoria", $scope.categoriaCadastro).then(function (response) {
            atualizar();
            $scope.categoriaCadastro = null;
            sucesso("Categoria cadastrada com sucesso");
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro ao cadastrar categoria"));
        });
    };
    $scope.recuperar = function () {
        $http.put(webService + "/categoria/recuperar/" + $scope.categoriaRecuperacaoId).then(function (response) {
            atualizar();
            sucesso("Categoria recuperada com sucesso");
        }, function (reponse) {
            erro(errorManager(response.config.url, response.status, "Erro ao recuperar categoria"));
        });
    };

    $scope.deletar = function () {
        $http.delete(webService + "/categoria/" + $scope.categoriaExclusaoId).then(function (response) {
            atualizar();
            excluido("Categoria deletada com sucesso");
        }, function (response) {
            erro(errorManager(response.config.url, response.status, "Erro deletar categoria"));
        });
    };

    //funções de preparação de variaveis

    $scope.prepararRecuperacao = function (id) {
        $scope.categoriaRecuperacaoId = id;
    };

    $scope.cancelarRecuperacao = function () {
        $scope.categoriaRecuperacaoId = null;
    };
    $scope.prepararEdicao = function (categoria) {
        $scope.categoriaEdicao = categoria;
    };

    $scope.cancelarEdicao = function () {
        $scope.categoriaEdicao = null;
    };

    $scope.prepararExclusao = function (id) {
        $scope.categoriaExclusaoId = id;
    };

    $scope.cancelarExclusao = function () {
        $scope.categoriaExclusaoId = null;
    };

    //funções de atualizações e avisos
    function atualizar() {
        $scope.listarTodos();
        $scope.listarExcluidas();
        $scope.listarNaoExcluidas();
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