angular.module("platz").controller("eventosController", function ($scope, $http, toastr) {

    $scope.listarCategoriasNaoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas").then(function (response) {
            $scope.categorias = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar categorias"));
        });
    };

    $scope.listarEventos = function () {
        $http.get(webService + "/eventos").then(function (response) {
            $scope.eventos = response.data;
        }, function (response) {
            console.log(response.data);
        });
    };


    $scope.listarTop3Eventos = function () {
        $http.get(webService + "/eventos/top/" + 3).then(function (response) {
            // console.log(response.data);
            $scope.top3Eventos = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar top 3"));
        });
    };


    $scope.listarTop15Eventos = function () {
        $http.get(webService + "/eventos/top/" + 15).then(function (response) {
            index = 1;
            var evento3 = new Array();
            var eventoTop15 = new Array();
            do {
                for (var i = index - 1; i < index + 2; i++) {
                    evento3.push(response.data[i]);
                }
                eventoTop15.push(evento3);
                index += 3;
                evento3 = new Array();
            } while (index <= response.data.length);
            $scope.top15Eventos = eventoTop15;

        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar top 15"));
        });
    };


    $scope.baixarImagemCategoria = function (id) {
        return webService + "/categoria/imagem/" + id;
    };



    window.onload = function () {
        $scope.listarEventos();
        $scope.listarCategoriasNaoExcluidas();
        $scope.listarTop3Eventos();
        $scope.listarTop15Eventos();

    };


});