angular.module("platz").controller("contaController", function ($scope, $http, toastr) {

    $scope.listarAdministradores = function () {
        $http.get(webService + "/contas/administradores").then(function (response) {

            $scope.admintradores = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar Admintradores"));
        });
    };
    $scope.listarAdministradoresInativos = function () {
        $http.get(webService + "/contas/administradores/inativas").then(function (response) {
            $scope.admintradoresInativos = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar Admintradores"));
        });
    };
    $scope.listarAdministradoresBloqueados = function () {
        $http.get(webService + "/contas/administradores/bloqueadas").then(function (response) {

            $scope.admintradoresBloqueados = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar Admintradores"));
        });
    };

    $scope.listarUsuarios = function () {
        $http.get(webService + "/contas/usuarios").then(function (response) {
            $scope.usuarios = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar Usuarios"));
        });
    };
    $scope.listarUsuariosInativos = function () {
        $http.get(webService + "/contas/usuarios/inativas").then(function (response) {
            $scope.usuariosInativos = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar Usuarios"));
        });
    };
    $scope.listarUsuariosBloqueados = function () {
        $http.get(webService + "/contas/usuarios/bloqueadas").then(function (response) {
            $scope.usuariosBloqueados = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar Usuarios"));
        });
    };
    $scope.listarEmpresas = function () {
        $http.get(webService + "/contas/empresas").then(function (response) {
            $scope.empresas = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar empresas"));
        });
    };
    $scope.listarEmpresasInativos = function () {
        $http.get(webService + "/contas/empresas/inativas").then(function (response) {
            $scope.empresasInativos = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar empresas"));
        });
    };
    $scope.listarEmpresasBloqueados = function () {
        $http.get(webService + "/contas/empresas/bloqueadas").then(function (response) {

            $scope.empresasBloqueados = response.data;
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "erro ao listar empresas"));
        });
    };
    $scope.bloquear = function () {
        $http.put(webService + "/conta/bloquear/" + $scope.contaBloquearId).then(function (response) {
            atualizar();
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "falha ao bloquear conta"));
        });
    };
    $scope.inativar = function () {
        $http.put(webService + "/conta/inativar/" + $scope.contaInativarId).then(function (response) {
            atualizar();
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "falha ao inativar conta"));
        });
    };
    $scope.desbloquear = function () {
        $http.put(webService + "/conta/desbloquear/" + $scope.contaDesloquearId).then(function (response) {
            atualizar();
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "falha ao desbloquear conta"));
        });
    };
    $scope.ativar = function () {
        $http.put(webService + "/conta/ativar/" + $scope.contaAtivarId).then(function (response) {
            atualizar();
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "falha ao desativar conta"));
        });
    };

    $scope.getUsuario = function (contaId) {
        $http.get(webService + "/usuario/conta/" + contaId).then(function (response) {
            $scope.usuarioDetalhe = response.data;
            $scope.imagemUsuario = webService + "/usuario/imagem/" + $scope.usuarioDetalhe.id;
        }, function (response) {
            aviso(toastr, "falha ao buscar informações do Usuario");
        });
    };

    $scope.getEmpresa = function (contaId) {
        $http.get(webService + "/empresa/conta/" + contaId).then(function (response) {
            $scope.empresaDetalhe = response.data;
            console.log(empresaDetalhe);
            $scope.imagemEmpresa = webService + "/empresa/imagem/" + $scope.empresaDetalhe.id;
        }, function (response) {
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
    }
    $scope.cancelarBloqueamento = function () {
        $scope.contaBloquearId = null;
    }
    $scope.prepararDesbloqueamento = function (id) {
        $scope.contaDesloquearId = id;
    }
    $scope.cancelarDesbloqueamento = function () {
        $scope.contaDesloquearId = null;
    }
    $scope.prepararAtivacao = function (id) {
        $scope.contaAtivarId = id;
    }
    $scope.cancelarAtivacao = function () {
        $scope.contaAtivarId = null;
    }
    $scope.prepararInativacao = function (id) {
        $scope.contaInativarId = id;
    }
    $scope.cancelarInativacao = function () {
        $scope.contaInativarId = null;
    }

    $scope.cadastrar = function () {
        if ($scope.contaCadastro.senha === $scope.contaCadastro.senha2) {
            $http.post(webService + "/conta", $scope.contaCadastro).then(function (response) {
                atualizar();
                $scope.contaCadastro = null;
                sucesso(toastr, "Administrador cadastrado com sucesso");
            }, function (response) {
                erro(toastr, errorManager(response.config.url, response.status, "Erro ao cadastrar administrador"));
            });
        } else {
            $scope.contaCadastro.senha == null;
            $scope.contaCadastro.senha2 == null;
            aviso(toastr, "A senha não são iguais, por favor digite-as novamente");
        }
    };

    function atualizar() {
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
    window.onload = atualizar();
});