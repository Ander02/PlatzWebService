angular.module("platz").controller("categoriaController", function ($scope, $http, toastr) {

    //funções que realizam consulta no webService
    $scope.listarTodos = function () {
        $http.get(webService + "/categorias").then(function (response) {
            $scope.categoriasAll = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar categorias"));
        });
    };
    $scope.listarExcluidas = function () {
        $http.get(webService + "/categorias/excluidas").then(function (response) {
            $scope.categoriasExcluidas = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar categorias excluidas"));
        });
    };
    $scope.listarNaoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas").then(function (response) {

            $scope.categorias = response.data;

        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar categorias"));
        });
    };

    $scope.alterar = function () {
        $http.put(webService + "/categoria/" + $scope.categoriaEdicao.id, $scope.categoriaEditada).then(function (response) {
            atualizar();
            $scope.categoriaEditada = null;
            alterado(toastr, "Categoria editar com sucesso");
            sleep(1000);
            location.reload();
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao alterar categoria"));
        });
    };

    $scope.alterarImagem = function () {

        var input = document.getElementById("InputIconeCategoriaEdicao");
        var icone = input.files[0];

        if (!(!icone.type.match('image.*'))) {
            enviarArquivo($http ,icone, 'icone' ,webService + "/categoria/imagem/" + $scope.categoriaImagemEdicaoId);
        }
        input.value = null;
        atualizar();
    };

    $scope.cadastrar = function () {

        $http.post(webService + "/categoria", $scope.categoriaCadastro).then(function (response) {
            atualizar();

            var input = document.getElementById("InputIconeCategoriaCadastro");

            var icone = input.files[0];

            if (!(!icone.type.match('image.*'))) {
                enviarArquivo($http, icone, 'icone' ,webService + "/categoria/imagem/" + response.data.id);
            }
            input.value = null;

            $scope.categoriaCadastro = null;

            sucesso(toastr, "Categoria cadastrada com sucesso");
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao cadastrar categoria"));
        });
    };

    $scope.recuperar = function () {
        $http.put(webService + "/categoria/recuperar/" + $scope.categoriaRecuperacaoId).then(function (response) {
            atualizar();
            sucesso(toastr, "Categoria recuperada com sucesso");
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao recuperar categoria"));
        });
    };

    $scope.deletar = function () {
        $http.delete(webService + "/categoria/" + $scope.categoriaExclusaoId).then(function (response) {
            atualizar();
            excluido(toastr, "Categoria deletada com sucesso");
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro deletar categoria"));
        });
    };
    
    $scope.baixarImagem = function (id) {
        return webService + "/categoria/imagem/" + id;
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
    $scope.prepararEdicaoImagem = function (id) {
        $scope.categoriaImagemEdicaoId = id;
    };
    $scope.cancelarEdicaoImagem = function () {
        $scope.categoriaImagemEdicaoId = null;
    };

    //funções de atualizações e avisos
    function atualizar() {
        $scope.listarTodos();
        $scope.listarExcluidas();
        $scope.listarNaoExcluidas();
        $scope.cancelarEdicao();
        $scope.cancelarExclusao();
        $scope.cancelarRecuperacao();
        $scope.cancelarEdicaoImagem();
    }
    window.onload = atualizar();
});