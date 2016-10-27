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
    
    $scope.buscarImagemCapa = function (id){
        return webService + "/evento/imagemCapa/" + id;
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
        console.log($scope.empresaEdicaoSenha);

    };
    $scope.alterarInfoEmpresariais = function () {
        console.log($scope.empresaEdicaoInfo);

    };

    $scope.alterarEndereco = function () {
        console.log($scope.empresaEdicaoEndereco);
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
