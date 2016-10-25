angular.module("platz").controller("loginController", function ($scope, $http, toastr) {

    $scope.logar = function () {
        login = {
            email: document.getElementById("email").value,
            senha: document.getElementById("senha").value
        };
        $http.post(webService + "/login", login).then(function (response) {
            $scope.contaLogin = response.data;
            location.href = "sessao.jsp?token=" + $scope.contaLogin.token + "&perfil=" + $scope.contaLogin.perfil;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao logar, usuario ou senha incorreto"));
        });
    };

    $scope.logarPageLogin = function () {
        login = {
            email: document.getElementById("emailLogin").value,
            senha: document.getElementById("senhaLogin").value
        };

        $http.post(webService + "/login", login).then(function (response) {
            $scope.contaLogin = response.data;
            location.href = "sessao.jsp?token=" + $scope.contaLogin.token + "&perfil=" + $scope.contaLogin.perfil;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao logar, usuario ou senha incorreto"));
        });
    };

    $scope.deslogar = function () {
        $http.post(webService + "/logoff", null, gerarHeaders(document.getElementById("token").value)).then(function (response) {
            info(toastr, "logoff efetuado");
            location.href = "../index.jsp";
        }, function (response) {

        });

    };
    console.log("vai tomar no seu orificio anal ");

});