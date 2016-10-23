angular.module("platz").controller("cadastroController", function ($scope, $http, toastr) {
    $scope.conta;

    $scope.onblurCepEmpresa = function () {
        cep = document.getElementById("empresa-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {
            console.log(response.data);
            $scope.empresa.endereco.rua = response.data.logradouro;
            $scope.empresa.endereco.bairro = response.data.bairro;
            $scope.empresa.endereco.cidade = response.data.localidade;
            $scope.empresa.endereco.uf = response.data.uf;
        }, function (response) {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });
    };

    $scope.onblurCepUsuario = function () {
        cep = document.getElementById("usuario-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {
            console.log(response.data);
            $scope.usuario.endereco.cep = response.data.cep;
            $scope.usuario.endereco.rua = response.data.logradouro;
            $scope.usuario.endereco.bairro = response.data.bairro;
            $scope.usuario.endereco.cidade = response.data.localidade;
            $scope.usuario.endereco.uf = response.data.uf;
        }, function (response) {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });

    };

    $scope.cadastrarEmpresa = function () {

        if ($scope.empresa.conta.senha === $scope.empresa.conta.confirmaSenha) {
            //$scope.empresa.endereco.uf = "SP";
            $scope.empresa.perfil = 1;
            console.log($scope.empresa);
            $http.post(webService + "/empresa", $scope.empresa).then(function (response) {
                sucesso(toastr, "Empresa cadastrada com sucesso");
                $scope.empresa = null;
                $scope.conta = response.data.conta;
                $scope.conta.senha = $scope.empresa.conta.senha;
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
       $scope.usuario.dataNascimento = document.getElementById("date").value;

        if ($scope.usuario.conta.senha === $scope.usuario.conta.confirmaSenha) {
            //$scope.usuario.endereco.uf = "SP";
            $scope.usuario.perfil = 2;
            console.log($scope.usuario);
            $http.post(webService + "/usuario", $scope.usuario).then(function (response) {
                sucesso(toastr, "Usuario cadastrado com sucesso");
                $scope.usuario = null;
                $scope.conta = response.data.conta;
                $scope.conta.senha = $scope.usuario.conta.senha;
            }, function (response) {
                erro(toastr, errorManager(response.config.url, response.status, "Falha ao cadastrar usuario, verifique os campos e tente novamente"));
            });

        } else {
            aviso(toastr, "Senhas digitadas não são iguais, por favor divige novamente");
            $scope.usuario.conta.senha = null;
            $scope.usuario.conta.confirmaSenha = null;
        }
    };

    $scope.logar = function () {
        login = {
            email: $scope.conta.email,
            senha: $scope.conta.senha
        };

        console.log(login);
        $http.post(webService + "/login", login).then(function (response) {
            $scope.contaLogin = response.data;
            location.href = "sessao.jsp?token=" + $scope.contaLogin.token + "&perfil=" + $scope.contaLogin.perfil;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao logar, usuario ou senha incorreto"));
        });
    };

});