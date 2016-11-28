angular.module("platz").controller("usuarioController", function ($scope, $http, toastr, loginService) {

    $scope.buscaUsuario = function () {
        $http.get(webService + "/usuario/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.usuario = response.data;
            $scope.usuarioEdicaoSenha = $scope.usuario;
            $scope.usuarioEdicaoInfo = $scope.usuario;
            $scope.usuarioEdicaoEndereco = $scope.usuario;
            $scope.imagemPerfil = webService + "/usuario/imagem/" + $scope.usuario.id;
            $scope.buscaEventosCurtidos();
            $scope.buscarEventosPresenca();
        }, function () {
        });
    };

    $scope.buscarEventosPresenca = function () {
        $http.get(webService + "/presenca/conta/" + $scope.conta.id + "/presenca/0", loginService.getHeaders()).then(function (response) {
            $scope.eventoVou = response.data;
        }, function () {
        });
        $http.get(webService + "/presenca/conta/" + $scope.conta.id + "/presenca/1", loginService.getHeaders()).then(function (response) {
            $scope.eventoTalvezVou = response.data;
        }, function () {
        });
        $http.get(webService + "/presenca/conta/" + $scope.conta.id + "/presenca/2", loginService.getHeaders()).then(function (response) {
            $scope.eventoNaoVou = response.data;
        }, function () {
        });
    };

    $scope.buscaEventosCurtidos = function () {
        $http.get(webService + "/curtidas/usuario/" + $scope.usuario.id, loginService.getHeaders()).then(function (response) {
            var eventosCurtidos = new Array();
            for (var i = 0; i < response.data.length; i++) {
                eventosCurtidos.push(response.data[i].evento);
            }

            index = 1;
            var evento3 = new Array();
            var eventos = new Array();
            do {
                for (var i = index - 1; i < index + 2; i++) {
                    evento3.push(eventosCurtidos[i]);
                }
                eventos.push(evento3);
                index += 3;
                evento3 = new Array();
            } while (index <= response.data.length);
            $scope.eventosCurtidos = eventos;

        }, function () {

        });
    };

    $scope.descurtir = function (eventoId) {
        console.log($scope.usuario.id);
        console.log(eventoId);
        $http.delete(webService + "/descurtir/" + $scope.usuario.id + "/" + eventoId, loginService.getHeaders()).then(function (response) {
            atualizar();
        }, function (response) {
            info(toastr, "Falha ao discurtir");
        });
    };

    $scope.buscarImagemCapa = function (id) {
        return webService + "/evento/imagemCapa/" + id;
    };

    $scope.alterarSenha = function () {
        console.log($scope.usuarioEdicaoSenha);
        if ($scope.usuarioEdicaoSenha.conta.senha === $scope.usuarioEdicaoSenha.conta.confirmaSenha) {
            $http.put(webService + "/conta/senha/" + $scope.usuarioEdicaoSenha.conta.id, $scope.usuarioEdicaoSenha.conta, loginService.getHeaders()).then(function (response) {
                sucesso(toastr, "Senha editada com sucesso");
                $scope.usuarioEdicaoSenha.conta.senha = "";
                $scope.usuarioEdicaoSenha.conta.confirmaSenha = "";
            }, function () {
                aviso(toastr, "falha ao editar senha, por favor tente novamente mais tarde");
            });

        } else {
            aviso(toastr, "as senhas não corresponde, digite-as novamente");
            $scope.usuarioEdicaoSenha.conta.senha = "";
            $scope.usuarioEdicaoSenha.conta.confirmaSenha = "";
        }
        atualizar();
    };

    $scope.alterarInfoPessoais = function () {

        console.log($scope.usuarioEdicaoInfo);
        $scope.usuarioEdicaoInfo.endereco = null;
        $http.put(webService + "/usuario/" + $scope.usuarioEdicaoInfo.id, $scope.usuarioEdicaoInfo, loginService.getHeaders()).then(function (response) {
            sucesso(toastr, "informações editada com sucesso");
        }, function (response) {
            aviso(toastr, "falha ao editar informações, por favor tente novamente mais tarde");

        });

        var input = document.getElementById("conta-usuario-img");
        var imgUsuario = input.files[0];

        if (imgUsuario != null && imgUsuario != "" && typeof imgUsuario != undefined) {
            if (!(!imgUsuario.type.match('image.*'))) {
                enviarArquivo($http, imgUsuario, 'imgPerfil', webService + "/usuario/imagem/" + $scope.usuarioEdicaoInfo.id, $scope.token);
                sucesso(toastr, "Imagem atualizada");
            }
            input.value = null;
        }
        atualizar();
    };

    $scope.alterarEndereco = function () {
        console.log($scope.usuarioEdicaoEndereco);
        $http.put(webService + "/usuario/" + $scope.usuarioEdicaoEndereco.id, $scope.usuarioEdicaoEndereco, loginService.getHeaders()).then(function (response) {
            console.log(response.data);
            sucesso(toastr, "endereço editado com sucesso");
        }, function (response) {
            aviso(toastr, "falha ao editar endereço, por favor tente novamente mais tarde");

        });
        atualizar();
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
