angular.module("platz").controller("categoriaController", function ($scope, $http) {

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
    $scope.alterar = function (id) {
        $scope.put(webService +"categoria/"+ $scope.categoriaEdicaoId).then(function (response){
            
        }, function (response){
            
        });
    }
    $scope.cadastrar = function (categoria) {

        console.log(categoria);

        $http.post(webService + "/categoria", categoria).then(function (response) {

            console.log("Categoria Cadastrada com Sucesso");

            //Atualiza a lista
            atualizar();
            alert("Categoria cadastrada com Sucesso");
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
     $scope.prepararEdicao = function (id) {
        $scope.categoriaEdicaoId = id;
    };

    $scope.cancelarEdicao = function () {
        $scope.categoriaEdicaoId = null;
    };
    
    $scope.prepararExclusao = function (id) {
        $scope.categoriaExclusaoId = id;
    };

    $scope.cancelarExclusao = function () {
        $scope.categoriaEdicaoId = null;
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
    }
    window.onload = atualizar();
});



