angular.module("platz").controller("contaController", function ($scope, $http, toastr, loginService, validacaoService) {

    $scope.listarAdministradores = function () {
        $http.get(webService + "/contas/administradores", loginService.getHeaders()).then(function (response) {
            $scope.admintradores = response.data;
        }, function () {
        });
    };
    $scope.listarAdministradoresInativos = function () {
        $http.get(webService + "/contas/administradores/inativas", loginService.getHeaders()).then(function (response) {
            $scope.admintradoresInativos = response.data;
        }, function () {
        });
    };
    $scope.listarAdministradoresBloqueados = function () {
        $http.get(webService + "/contas/administradores/bloqueadas", loginService.getHeaders()).then(function (response) {

            $scope.admintradoresBloqueados = response.data;
        }, function () {
        });
    };

    $scope.listarUsuarios = function () {
        $http.get(webService + "/contas/usuarios", loginService.getHeaders()).then(function (response) {
            $scope.usuarios = response.data;
        }, function () {
        });
    };
    $scope.listarUsuariosInativos = function () {
        $http.get(webService + "/contas/usuarios/inativas", loginService.getHeaders()).then(function (response) {
            $scope.usuariosInativos = response.data;
        }, function () {
        });
    };
    $scope.listarUsuariosBloqueados = function () {
        $http.get(webService + "/contas/usuarios/bloqueadas", loginService.getHeaders()).then(function (response) {
            $scope.usuariosBloqueados = response.data;
        }, function () {
        });
    };

    $scope.listarEmpresas = function () {
        $http.get(webService + "/contas/empresas", loginService.getHeaders()).then(function (response) {
            $scope.empresas = response.data;
        }, function () {
        });
    };
    $scope.listarEmpresasInativos = function () {
        $http.get(webService + "/contas/empresas/inativas", loginService.getHeaders()).then(function (response) {
            $scope.empresasInativos = response.data;
        }, function () {
        });
    };
    $scope.listarEmpresasBloqueados = function () {
        $http.get(webService + "/contas/empresas/bloqueadas", loginService.getHeaders()).then(function (response) {
            $scope.empresasBloqueados = response.data;
        }, function () {
        });
    };

    $scope.bloquear = function () {
        $http.put(webService + "/conta/bloquear/" + $scope.contaBloquearId, null, loginService.getHeaders()).then(function () {
            atualizar();
        }, function () {
            erro(toastr, "falha ao bloquear conta, tente novamente mais tarde");
        });
    };
    $scope.inativar = function () {
        $http.put(webService + "/conta/inativar/" + $scope.contaInativarId, null, loginService.getHeaders()).then(function () {
            atualizar();
        }, function () {
            erro(toastr, "falha ao inativar conta, tente novamente mais tarde");
        });
    };
    $scope.desbloquear = function () {
        $http.put(webService + "/conta/desbloquear/" + $scope.contaDesloquearId, null, loginService.getHeaders()).then(function () {
            atualizar();
        }, function () {
            erro(toastr, "falha ao desbloquear conta, tente novamente mais tarde");
        });
    };
    $scope.ativar = function () {
        $http.put(webService + "/conta/ativar/" + $scope.contaAtivarId, null, loginService.getHeaders()).then(function () {
            atualizar();
        }, function () {
            erro(toastr, "falha ao desativar conta, tente novamente mais tarde");
        });
    };

    $scope.getUsuario = function (contaId) {
        $http.get(webService + "/usuario/conta/" + contaId, loginService.getHeaders()).then(function (response) {
            $scope.usuarioDetalhe = response.data;
            $scope.imagemUsuario = webService + "/usuario/imagem/" + $scope.usuarioDetalhe.id;
        }, function () {
            aviso(toastr, "falha ao buscar informações do Usuario");
        });
    };

    $scope.getEmpresa = function (contaId) {
        $http.get(webService + "/empresa/conta/" + contaId, loginService.getHeaders()).then(function (response) {
            $scope.empresaDetalhe = response.data;
            $scope.imagemEmpresa = webService + "/empresa/imagem/" + $scope.empresaDetalhe.id;
        }, function () {
            aviso(toastr, "falha ao buscar informações da Empresa");
        });
    };

    $scope.isBloqueado = function (conta) {
        if (conta.bloqueado === null) {
            return false;
        } else {
            return true;
        }
    };

    $scope.prepararBloqueamento = function (id) {
        $scope.contaBloquearId = id;
    };
    $scope.cancelarBloqueamento = function () {
        $scope.contaBloquearId = null;
    };
    $scope.prepararDesbloqueamento = function (id) {
        $scope.contaDesloquearId = id;
    };
    $scope.cancelarDesbloqueamento = function () {
        $scope.contaDesloquearId = null;
    };
    $scope.prepararAtivacao = function (id) {
        $scope.contaAtivarId = id;
    };
    $scope.cancelarAtivacao = function () {
        $scope.contaAtivarId = null;
    };
    $scope.prepararInativacao = function (id) {
        $scope.contaInativarId = id;
    };
    $scope.cancelarInativacao = function () {
        $scope.contaInativarId = null;
    };

    $scope.cadastrar = function (contaCadastro) {


        if (!validacaoService.vazio(toastr, contaCadastro, "Conta")) {
            if (contaCadastro.senha === contaCadastro.senha2 && !validacaoService.vazio(toastr, contaCadastro.senha, "Senha")) {
                if (validacaoService.comprimento(toastr, contaCadastro.email, 1, 50, "email") && validacaoService.conteudo(toastr, contaCadastro.email, "email")) {

                    $http.post(webService + "/conta", contaCadastro, loginService.getHeaders()).then(function () {
                        atualizar();
                        $scope.contaCadastro = null;
                        sucesso(toastr, "Administrador cadastrado com sucesso");
                    }, function () {
                        erro(toastr, "Erro ao cadastrar administrador, tente novamente mais tarde");
                    });
                }
            } else {
                $scope.contaCadastro.senha = null;
                $scope.contaCadastro.senha2 = null;
                aviso(toastr, "A senha não são iguais, por favor digite-as novamente");
            }
        }
    };

    function atualizar() {
        loginService.verificarToken($http, toastr, "Administrador", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.listarAdministradores();
        $scope.listarAdministradoresBloqueados();
        $scope.listarAdministradoresInativos();

        $scope.listarUsuarios();
        $scope.listarUsuariosBloqueados();
        $scope.listarUsuariosInativos();

        $scope.listarEmpresas();
        $scope.listarEmpresasBloqueados();
        $scope.listarEmpresasInativos();
    }
    window.onload = function () {
        console.log("onload");
        $scope.permicao = false;
        atualizar();
    };
});