angular.module("platz").controller("perfilEmpresaController", function ($scope, $http, toastr, loginService) {

//funções de atualizações

    $scope.buscaEmpresa = function () {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.empresa = response.data;
            $scope.empresaEdicaoEndereco = $scope.empresa;
            $scope.empresaEdicaoSenha = $scope.empresa;
            $scope.empresaEdicaoInfo = $scope.empresa;
            $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
            $scope.eventosEmpresa();
        }, function () {
        });
    };

    $scope.buscarImagemCapa = function (id) {
        return webService + "/evento/imagemCapa/" + id;
    };

    $scope.eventosEmpresa = function () {
        $http.get(webService + "/eventos/empresa/" + $scope.empresa.id, loginService.getHeaders()).then(function (response) {
            $scope.eventos = response.data;
        }, function () {
        });
    };

    $scope.alterarSenha = function () {

        if ($scope.empresaEdicaoSenha.conta.senha === $scope.empresaEdicaoSenha.conta.confirmaSenha) {
            $scope.empresaEdicaoSenha.endereco = null;
            $http.put(webService + "/conta/senha/" + $scope.empresaEdicaoSenha.conta.id, $scope.empresaEdicaoSenha.conta, loginService.getHeaders()).then(function (response) {
                sucesso(toastr, "Senha editada com sucesso");
                $scope.empresaEdicaoSenha.conta.senha = null;
                $scope.empresaEdicaoSenha.conta.confirmaSenha = null;
            }, function () {
                aviso(toastr, "falha ao editar senha, por favor tente novamente mais tarde");
            });

        } else {
            aviso(toastr, "as senhas não corresponde, digite-as novamente");
            $scope.empresaEdicaoSenha.conta.senha = null;
            $scope.empresaEdicaoSenha.conta.confirmaSenha = null;
        }
    };


    $scope.alterarInfoEmpresariais = function () {
        $scope.empresaEdicaoInfo.endereco = null;
        $http.put(webService + "/empresa/" + $scope.empresaEdicaoInfo.id, $scope.empresaEdicaoInfo, loginService.getHeaders()).then(function (response) {
            sucesso(toastr, "informações editada com sucesso");
        }, function () {
            aviso(toastr, "falha ao editar informações, por favor tente novamente mais tarde");

        });
        var input = document.getElementById("conta-empresa-img");
        var imgEmpresa = input.files[0];

        if (imgEmpresa != null && imgEmpresa != "" && typeof imgEmpresa != undefined) {
            if (!(!imgEmpresa.type.match('image.*'))) {
                enviarArquivo($http, imgEmpresa, 'imgPerfil', webService + "/empresa/imagem/" + $scope.empresaEdicaoInfo.id, $scope.token);
                sucesso(toastr, "Imagem atualizada");
            }
            input.value = null;
        }
    };

    $scope.alterarEndereco = function () {
        $http.put(webService + "/empresa/" + $scope.empresaEdicaoEndereco.id, $scope.empresaEdicaoEndereco, loginService.getHeaders()).then(function (response) {
            sucesso(toastr, "endereço editado com sucesso");
        }, function () {
            aviso(toastr, "falha ao editar endereço, por favor tente novamente mais tarde");

        });
    };

    $scope.onblurCep = function () {
        cep = document.getElementById("conta-empresa-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {
            $scope.empresaEdicaoEndereco.endereco.numero = "";
            $scope.empresaEdicaoEndereco.endereco.complemento = "";
            $scope.empresaEdicaoEndereco.endereco.cep = response.data.cep;
            $scope.empresaEdicaoEndereco.endereco.rua = response.data.logradouro;
            $scope.empresaEdicaoEndereco.endereco.bairro = response.data.bairro;
            $scope.empresaEdicaoEndereco.endereco.cidade = response.data.localidade;
            $scope.empresaEdicaoEndereco.endereco.uf = response.data.uf;
        }, function () {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });

    };

    $scope.deslogar = function () {
        $http.post(webService + "/logoff", null, loginService.getHeaders()).then(function (response) {
            info(toastr, "logoff efetuado");
            location.href = "../index.jsp";
        }, function () {

        });
    };

    function atualizar() {
        loginService.verificarToken($http, toastr, "Empresa", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
            $scope.buscaEmpresa();
        });
    }    

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };

});
