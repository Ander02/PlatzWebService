angular.module("platz").controller("cadastroController", function ($scope, $http, toastr) {

    $scope.cadastrarEmpresa = function () {

        if ($scope.empresa.conta.senha === $scope.empresa.conta.confirmaSenha) {
            $scope.empresa.endereco.uf = "SP";
            $scope.empresa.perfil = 1;
            console.log($scope.empresa);
            $http.post(webService + "/empresa", $scope.empresa).then(function (response) {
                sucesso(toastr, "Empresa cadastrada com sucesso");
                $scope.empresa = null;
            }, function (response) {
                erro(toastr, errorManager(response.config.url, response.status, "Falha ao cadastrar empresa, verifique os campos e tente novamente"));
            });

        } else {
            aviso(toastr, "Senhas digitadas não são iguais, por favor divige novamente");
            $scope.empresa.conta.senha = null;
            $scope.empresa.conta.confirmaSenha = null;
        }
    };

    $scope.cadastrarUsuario = function () {
        
        if ($scope.usuario.conta.senha === $scope.usuario.conta.confirmaSenha) {
            $scope.usuario.endereco.uf = "SP";
            $scope.usuario.perfil = 2;
            console.log($scope.usuario);
            $http.post(webService + "/usuario", $scope.usuario).then(function (response) {
                sucesso(toastr, "Usuario cadastrada com sucesso");
            }, function (response) {
                erro(toastr, errorManager(response.config.url, response.status, "Falha ao cadastrar usuario, verifique os campos e tente novamente"));
            });

        } else {
            aviso(toastr, "Senhas digitadas não são iguais, por favor divige novamente");
            $scope.usuario.conta.senha = null;
            $scope.usuario.conta.confirmaSenha = null;
        }
    };

    

});

