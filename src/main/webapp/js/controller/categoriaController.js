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

            var icone = document.getElementById("InputIconeCategoriaEdicao");

            enviarArquivo(icone.files[0], webService + "/categoria/imagem/" + response.data.id);

            icone.value = null;

            alterado(toastr, "Categoria editar com sucesso");
            sleep(1000);
            location.reload();
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao alterar categoria"));
        });
    };
    $scope.cadastrar = function () {

        $http.post(webService + "/categoria", $scope.categoriaCadastro).then(function (response) {
            atualizar();

            $scope.categoriaCadastro = null;

            var icone = document.getElementById("InputIconeCategoriaCadastro");

            enviarArquivo(icone.files[0], webService + "/categoria/imagem/" + response.data.id);

            icone.value = null;

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

    function enviarArquivo(arquivo, url) {
        var formData = new FormData();
        formData.append('icone', arquivo);
        $http.put(url, formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).success(function (response) {
            console.log("Arquivo Enviado");
            console.log(response);

            atualizar();

        }).error(function (response) {
            console.log("Erro ao enviar");
            console.log(response);
        });
    }

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
});