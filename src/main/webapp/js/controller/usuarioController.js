angular.module("platz").controller("usuarioController", function ($scope, $http, toastr, loginService) {
    console.log("ok");

    $scope.buscaUsuario = function () {
        $http.get(webService + "/usuario/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.usuario = response.data;
            $scope.usuarioEdicaoEndereco = $scope.usuario;
            $scope.imagemPerfil = webService + "/usuario/imagem/" + $scope.usuario.id;
        }, function () {
        });
    };



    $scope.onblurCep = function () {
        cep = document.getElementById("usuario-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {
            //console.log(response.data);
            $scope.usuarioEdicaoEndereco.endereco.numero = "";
            $scope.usuarioEdicaoEndereco.endereco.complemento = "";
            $scope.usuarioEdicaoEndereco.endereco.cep = response.data.cep;
            $scope.usuarioEdicaoEndereco.endereco.rua = response.data.logradouro;
            $scope.usuarioEdicaoEndereco.endereco.bairro = response.data.bairro;
            $scope.usuarioEdicaoEndereco.endereco.cidade = response.data.localidade;
            $scope.usuarioEdicaoEndereco.endereco.uf = response.data.uf;
        }, function (response) {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });

    };

//funções de atualizações
    function atualizar() {
        loginService.verificarToken($http, toastr, "Usuario", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
            $scope.buscaUsuario();
        });
    }


    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };
});
