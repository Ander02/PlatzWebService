app.requires.push('isteven-multi-select');
app.controller("eventoCadastroController", function ($scope, $http, toastr, loginService) {

    //$scope.evento.categoriasId = []; 
    $scope.categoriasId = [];
//    $scope.evento = "";
    $scope.cadastrar = function (evento) {
        $scope.evento = evento;
        $scope.evento.categoriasId = new Array();
//
//        for (var i = 0; i < categorias.length; i++) {
//            if (categorias[i].checked === true) {
//                $scope.evento.categoriasId.push(categorias[i].value);
//            }
//        }

        for (var i = 0; i < $scope.categoriasSelecionadas.length; i++) {
            $scope.evento.categoriasId.push($scope.categoriasSelecionadas[i].id);
        }

        console.log($scope.evento.categoriasId);

        console.log($scope.evento);
        $scope.evento.empresaId = $scope.empresa.id;
        $scope.evento.dataInicio = document.getElementById("date-start").value;
        $scope.evento.dataFim = document.getElementById("date-end").value;
        $scope.evento.destaque = false;

        $http.post(webService + "/evento", $scope.evento, loginService.getHeaders()).then(function (response) {
            //Upload imagem capa
            var inputCapa = document.getElementById("cadastro-evento-img-capa");
            if (!(!inputCapa.files[0].type.match('image.*'))) {
                enviarArquivo($http, inputCapa.files[0], 'imagemCapa', webService + "/evento/imagem/" + response.data.id, $scope.token);
            }
            inputCapa.value = null;

            //Upload Imagem Galeria
            var inputGaleria = document.getElementById("cadastro-evento-imagem-galeria");
            for (var i = 0; i < inputGaleria.files.length; i++) {
                if (!(!inputGaleria.files[i].type.match('image.*'))) {
                    sleep(1000);
                    enviarArquivo($http, inputGaleria.files[i], 'imagemGaleria', webService + "/evento/imagens/" + response.data.id, $scope.token);
                }
            }
            inputGaleria.value = null;

            //$scope.evento = null;
            sucesso(toastr, "Evento Cadastrado com sucesso");

        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "falha ao cadastra eventos"));
        });
    };

    $scope.listarNaoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas", loginService.getHeaders()).then(function (response) {
            $scope.categorias = response.data;

            for (var i = 0; i < $scope.categorias.length; i++) {
                $scope.categoriasId.push($scope.categorias[i].id);
            }
            console.log($scope.categoriasId);
        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "Erro ao listar categorias"));
        });
    };

    $scope.onblurCepEvento = function () {
        cep = document.getElementById("evento-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {
            console.log(response.data);
            $scope.evento.endereco.rua = response.data.logradouro;
            $scope.evento.endereco.bairro = response.data.bairro;
            $scope.evento.endereco.cidade = response.data.localidade;
            $scope.evento.endereco.uf = response.data.uf;
        }, function (response) {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });
    };

    function atualizar() {
        loginService.verificarToken($http, toastr, "Empresa", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
            $scope.buscaEmpresa();
        });
        $scope.listarNaoExcluidas();

    }

    $scope.buscaEmpresa = function () {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id).then(function (response) {
            $scope.empresa = response.data;
            $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
        }, function (response) {
        });
    };

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };

});