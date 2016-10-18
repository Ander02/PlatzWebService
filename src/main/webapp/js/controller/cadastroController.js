angular.module("platz").controller("cadastroController", function ($scope, $http, toastr) {

    $scope.cadastrarEmpresa = function () {


        if ($scope.empresa.conta.senha === $scope.empresa.conta.confirmaSenha) {
            $scope.empresa.endereco.uf = "SP";
            $scope.empresa.perfil = 1;
            console.log($scope.empresa);
            $http.post(webService + "/empresa", $scope.empresa).then(function (response) {
                sucesso(toastr, "Empresa cadastrada com sucesso");
                console.log(response.data);
            }, function (response) {
                erro(toastr, errorManager(response.config.url, response.status, "Falha ao cadastrar empresa, verifique os campos e tente novamente"));
                console.log(response.data);
            });

        } else {
            aviso(toastr, "Senhas não conhencide, por favor divige novamente");
            $scope.empresa.conta.senha = null;
            $scope.empresa.conta.confirmaSenha = null;
        }
    };

});