angular.module("platz").controller("contaController", function ($scope, $http, toastr) {
    $scope.listarAdministradores = function () {
        $http.get(webService + "/contas/administradores").then(function (response) {
            $scope.admintradores = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar Admintradores"));
        });
    };
    $scope.listarAdministradoresInativos = function () {
        $http.get(webService + "/contas/administradores/inativas").then(function (response) {
            $scope.admintradoresInativos = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar Admintradores"));
        });
    };
    $scope.listarAdministradoresBloqueados = function () {
        $http.get(webService + "/contas/administradores/bloqueadas").then(function (response) {
            $scope.admintradoresBloqueados = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar Admintradores"));
        });
    };

    $scope.listarUsuarios = function () {
        $http.get(webService + "/contas/usuarios").then(function (response) {
            $scope.usuarios = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar Usuarios"));
        });
    };
    $scope.listarUsuariosInativos = function () {
        $http.get(webService + "/contas/usuarios/inativas").then(function (response) {
            $scope.usuariosInativos = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar Usuarios"));
        });
    };
    $scope.listarUsuariosBloqueados = function () {
        $http.get(webService + "/contas/usuarios/bloqueadas").then(function (response) {
            $scope.usuariosBloqueados = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar Usuarios"));
        });
    };
    $scope.listarEmpresas = function () {
        $http.get(webService + "/contas/empresas").then(function (response) {
            $scope.empresas = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar empresas"));
        });
    };
    $scope.listarEmpresasInativos = function () {
        $http.get(webService + "/contas/empresas/inativas").then(function (response) {
            $scope.empresasInativos = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar empresas"));
        });
    };
    $scope.listarEmpresasBloqueados = function () {
        $http.get(webService + "/contas/empresas/bloqueadas").then(function (response) {
            $scope.empresasBloqueados = response.data;
        }, function (response) {
            erro(toastr, errorManager(" ", response.status, "erro ao listar empresas"));
        });
    };

    function atualizar() {
        $scope.listarAdministradores();
        $scope.listarAdministradoresBloqueados();
        $scope.listarAdministradoresInativos();

        $scope.listarUsuarios();
        $scope.listarUsuariosBloqueados();
        $scope.listarUsuariosInativos();
    }
    window.onload = atualizar();
});