angular.module("platz").controller("categoriaController", function ($scope, $http) {

    //Faz um Get no Web Service recebendo uma URL
    $scope.listarTodos = function () {
        $http.get(webService + "/categorias").then(function (response) {
            $scope.categorias = response.data;
        }, function (response) {
            switch (response.status) {
                case 404:
                    console.log("Erro ao Acessar: " + webService + "/categorias");
                    break;
                default :
                    break;
            }
        });
    };

    $scope.cadastrar = function (categoria) {

        console.log(categoria);

        $http.post(webService + "/categoria", categoria).then(function (response) {

            console.log("Categoria Cadastrada com Sucesso");

            //Atualiza a lista
            $scope.listarTodos();

        }, function (response) {

            console.log("Erro ao cadastrar");
            console.log(response);

        });
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

            console.log("deletou");
            console.log(response);

        }, function (response) {

        });

    };

    window.onload = function (ev) {
        $scope.listarTodos();
    }
});



