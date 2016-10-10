angular.module("platz").controller("loginController", function ($scope, $http, toastr) {
    $scope.logar = function () {
        console.log($scope.login);
        $http.post(webService + "/login", $scope.login).then(function (response) {
            $scope.contaSession = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao realizar login"));
        });
    };
});