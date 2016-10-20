angular.module("platz").controller("eventoController", function ($scope, $http, toastr) {
    id = document.getElementById("idEvento").value;
    $scope.listarEventos = function () {
        $http.get(webService + "/eventos").then(function (response) {
            console.log(response.data);
        }, function (response) {
            console.log(response.data);
        });
    };
    $scope.eventoEspecifico = function () {

        $http.get(webService + "/evento/" + id).then(function (response) {
            $scope.evento = response.data;
        }, function (response) {
            console.log(response.data);
        });
    };
    $scope.avaliar = function (nota) {
        console.log(nota);
    };
    $scope.getMedia = function () {
        $http.get(webService + "/avaliacao/evento/media/" + id).then(function (response) {
            $scope.media = response.data;
            $scope.mediaArredondada = Math.round($scope.media);
        }, function (response) {
            console.log(response.data);
        });
    };
    
    

    window.onload = function () {
        $scope.listarEventos();
        $scope.eventoEspecifico();
        $scope.getMedia();

    };
});
