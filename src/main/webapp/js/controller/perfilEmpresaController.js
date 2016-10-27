angular.module("platz").controller("perfilEmpresaController", function ($scope, $http, toastr) {

//funções de atualizações

    $scope.buscaEmpresa = function () {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id).then(function (response) {
            $scope.empresa = response.data;
            $scope.empresaEdicaoEndereco = $scope.empresa;
            $scope.empresaEdicaoSenha = $scope.empresa;
            $scope.empresaEdicaoInfo = $scope.empresa;
            $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
            $scope.eventosEmpresa();
        }, function (response) {
            console.log(response.data);
        });
    };

    $scope.eventosEmpresa = function () {
        $http.get(webService + "/eventos/empresa/" + $scope.empresa.id).then(function (response) {
            $scope.eventos = response.data;
            console.log($scope.eventos);
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao buscar eventos da empresa"));
        });
    };
    $scope.alterarSenha = function () {

        if ($scope.empresaEdicaoSenha.senha === $scope.empresaEdicaoSenha.confirmaSenha) {

            $http.put(webService + "/conta/senha/" + $scope.empresaEdicaoSenha.conta.id, $scope.empresaEdicaoSenha).then(function (response) {
                sucesso(toastr, "Senha editada com sucesso");
                $scope.empresaEdicaoSenha.senha = null;
                $scope.empresaEdicaoSenha.confirmaSenha = null;
            }, function (reasponse) {
                aviso(toastr, "falha ao editar senha, por favor tente novamente mais tarde");
            });

        } else {
            aviso(toastr, "as senhas não corresponde, digite-as novamente");
            $scope.empresaEdicaoSenha.senha = null;
            $scope.empresaEdicaoSenha.confirmaSenha = null;
        }


    };

    $scope.alterarInfoEmpresariais = function () {
        console.log($scope.empresaEdicaoInfo);
        $http.put(webService + "/empresa/" + $scope.empresaEdicaoInfo.id, $scope.empresaEdicaoInfo).then(function (response) {
            sucesso(toastr, "informações editada com sucesso");
        }, function (response) {
            aviso(toastr, "falha ao editar informações, por favor tente novamente mais tarde");

        });
        var input = document.getElementById("conta-empresa-img");
        var imgEmpresa = input.files[0];

        if (imgEmpresa != null && imgEmpresa != "" && typeof imgEmpresa != undefined) {
            if (!(!imgEmpresa.type.match('image.*'))) {
                enviarArquivo($http, imgEmpresa, 'imgPerfil', webService + "/empresa/imagem/" + $scope.empresaEdicaoInfo.id);
                sucesso(toastr, "Imagem atualizada");
            }
            input.value = null;
        }
    };

    $scope.alterarEndereco = function () {
        console.log($scope.empresaEdicaoEndereco);
        $http.put(webService + "/empresa/" + $scope.empresaEdicaoEndereco.id, $scope.empresaEdicaoEndereco).then(function (response) {
            sucesso(toastr, "endereço editado com sucesso");
        }, function (response) {
            aviso(toastr, "falha ao editar endereço, por favor tente novamente mais tarde");

        });
    };

    $scope.onblurCep = function () {
        cep = document.getElementById("conta-empresa-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {
            //console.log(response.data);
            $scope.empresaEdicaoEndereco.endereco.numero = "";
            $scope.empresaEdicaoEndereco.endereco.complemento = "";
            $scope.empresaEdicaoEndereco.endereco.cep = response.data.cep;
            $scope.empresaEdicaoEndereco.endereco.rua = response.data.logradouro;
            $scope.empresaEdicaoEndereco.endereco.bairro = response.data.bairro;
            $scope.empresaEdicaoEndereco.endereco.cidade = response.data.localidade;
            $scope.empresaEdicaoEndereco.endereco.uf = response.data.uf;
        }, function (response) {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });

    };

    $scope.deslogar = function () {
        $http.post(webService + "/logoff", null, gerarHeaders(document.getElementById("token").value)).then(function (response) {
            info(toastr, "logoff efetuado");
            location.href = "../index.jsp";
        }, function (response) {

        });
    };

    function atualizar() {
        console.log("atualizar");
        verificarToken($http, $scope, toastr, function () {
            $scope.buscaEmpresa();
        });
    }
    ;

    window.onload = function () {
        console.log("onload");
        $scope.permicao = false;
        atualizar();
    };




});
