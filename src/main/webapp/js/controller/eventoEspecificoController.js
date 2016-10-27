angular.module("platz").controller("eventoEspecificoController", function ($scope, $http, toastr) {
    id = document.getElementById("idEvento").value;

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
            $scope.media = parseFloat(response.data);
            $scope.mediaArredondada = Math.round($scope.media);
        }, function (response) {
            console.log(response.data);
        });
    };

    window.onload = function () {
        $scope.eventoEspecifico();
        $scope.getMedia();
    };
});
