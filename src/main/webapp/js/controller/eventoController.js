angular.module("platz").controller("eventoController", function ($scope, $http, toastr) {

    $scope.cadastrar = function () {

//        var categorias = document.querySelectorAll(".categorias");
//        $scope.evento.categoriasId = new Array();
//
//        for (var i = 0; i < categorias.length; i++) {
//            if (categorias[i].checked === true) {
//                $scope.evento.categoriasId.push(categorias[i].value);
//            } 
//        }

        $scope.evento.empresaId = $scope.empresa.id;
        $scope.evento.dataInicio = document.getElementById("date-start").value;
        $scope.evento.dataFim = document.getElementById("date-end").value;
        $scope.evento.destaque = false;

        $http.post(webService + "/evento", $scope.evento, gerarHeaders(document.getElementById("token").value)).then(function (response) {
            //Upload imagem capa
            var inputCapa = document.getElementById("cadastro-evento-img-capa");
            if (!(!inputCapa.files[0].type.match('image.*'))) {
                enviarArquivo($http, inputCapa.files[0], 'imagemCapa', webService + "/evento/imagem/" + response.data.id);
            }
            inputCapa.value = null;

            //Upload Imagem Galeria
            var inputGaleria = document.getElementById("cadastro-evento-imagem-galeria");
            for (var i = 0; i < inputGaleria.files.length; i++) {
                if (!(!inputGaleria.files[i].type.match('image.*'))) {
                    console.log(i);
                    enviarArquivo($http, inputGaleria.files[i], 'imagemGaleria', webService + "/evento/imagens/" + response.data.id);
                    sleep(1000);
                }
            }

            inputGaleria.value = null;

            $scope.evento = null;
            sucesso(toastr, "Evento Cadastrado com sucesso");

        }, function (response) {
            erro(toastr, errorManager(response.config.url, response.status, "falha ao cadastra eventos"));
        });
    };

    $scope.listarNaoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas").then(function (response) {
            $scope.categorias = response.data;
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
        console.log("atualizar");
        verificarToken($http, $scope, toastr, function () {
            $scope.buscaEmpresa();
        });
        $scope.listarNaoExcluidas();
    }

    $scope.buscaEmpresa = function () {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id).then(function (response) {
            $scope.empresa = response.data;
            $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
        }, function (response) {
            console.log(response.data);
        });
    };

    window.onload = function () {
        console.log("onload");
        $scope.permicao = false;
        atualizar();
    };

});