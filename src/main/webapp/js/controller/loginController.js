angular.module("platz").controller("loginController", function ($scope, $http, toastr) {
    $scope.logar = function () {
        login = {
            email: document.getElementById("email").value,
            senha: document.getElementById("senha").value
        }
        $http.post(webService + "/login", login).then(function (response) {
            $scope.contaLogin = response.data;
            location.href = "sessao.jsp?token=" + $scope.contaLogin.token + "&perfil=" + $scope.contaLogin.perfil;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao logar, usuario ou senha incorreto"));
        });
    };

    $scope.deslogar = function () {
        $http.post(webService + "/logoff").then(function (response) {
            info(toastr, "logoff efetuado");
            location.href = "../index.jsp";
        }, function (response) {

        });
        
    };
    
    window.onload = function (){
        console.log("login inside");
    };
});