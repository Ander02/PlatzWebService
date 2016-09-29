angular.module("platz").controller("categoriaController", function ($scope, $http, toastr) {

    //Faz um Get no Web Service recebendo uma URL
    $scope.listarTodos = function () {
        $http.get(webService + "/categorias").then(function (response) {
            $scope.categoriasAll = response.data;
        }, function (response) {
        });
    };
    $scope.listarExcluidas = function () {
        $http.get(webService + "/categorias/excluidas").then(function (response) {
            $scope.categoriasExcluidas = response.data;
        }, function (response) {
        });
    };
    $scope.listarNãoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas").then(function (response) {
            $scope.categorias = response.data;
        }, function (response) {
        });
    };
    $scope.alterar = function () {
        $http.put(webService + "/categoria/" + $scope.categoriaEdicao.id, $scope.categoriaEditada).then(function (response) {
            atualizar();
            $scope.categoriaEditada = null;
            location.reload();
        }, function (response) {

        });
    }
    $scope.cadastrar = function (categoria) {

        console.log(categoria);

        $http.post(webService + "/categoria", categoria).then(function (response) {

            console.log("Categoria Cadastrada com Sucesso");

            //Atualiza a lista
            atualizar();
            
            toastr.success('Categoria cadastrada com sucesso', 'Sucesso!',{
               progressBar: true
            });
        }, function (response) {

            console.log("Erro ao cadastrar");
            console.log(response);

        });
    };
    $scope.recuperar = function () {
        $http.put(webService + "/categoria/recuperar/" + $scope.categoriaRecuperacaoId).then(function (response) {
            atualizar();
            alert("categoria recuperada com sucesso");
        }, function (reponse) {

        });
    };

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

    $scope.deletar = function () {

        console.log($scope.categoriaExclusaoId);

        $http.delete(webService + "/categoria/" + $scope.categoriaExclusaoId).then(function (response) {

            atualizar();
            alert("deletado");
            console.log(response);

        }, function (response) {

        });
    };

    function atualizar() {
        $scope.listarTodos();
        $scope.listarExcluidas();
        $scope.listarNãoExcluidas();
        $scope.cancelarEdicao();
        $scope.cancelarExclusao();
        $scope.cancelarRecuperacao();
    }
    window.onload = atualizar();
});



