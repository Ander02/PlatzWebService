angular.module("platz").controller("categoriaController", function ($scope, $http, toastr, loginService, validacaoService) {

    //funções que realizam consulta no webService    
    $scope.listarExcluidas = function () {
        $http.get(webService + "/categorias/excluidas", loginService.getHeaders()).then(function (response) {
            $scope.categoriasExcluidas = response.data;
        }, function () {
        });
    };
    $scope.listarNaoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas", loginService.getHeaders()).then(function (response) {
            $scope.categorias = response.data;
        }, function () {
        });
    };

    $scope.alterar = function (categoriaEditada) {
        if (validacaoService.comprimento(toastr, categoriaEditada.nome, 0, 35, "nome") && validacaoService.conteudo(toastr, categoriaEditada.nome, "nome")) {
            $http.put(webService + "/categoria/" + $scope.categoriaEdicao.id, categoriaEditada, loginService.getHeaders()).then(function () {
                atualizar();
                alterado(toastr, "Categoria editar com sucesso");
            }, function () {
                erro(toastr, "Erro ao alterar categoria, tente novamente mais tarde");
            });
        }
    };

    $scope.alterarImagem = function () {

        var input = document.getElementById("InputIconeCategoriaEdicao");
        if (validacaoService.contemImagem(toastr, input.files)) {

            var icone = input.files[0];

            if (!(!icone.type.match('image.*'))) {
                enviarArquivo($http, icone, 'icone', webService + "/categoria/imagem/" + $scope.categoriaImagemEdicaoId, $scope.token);
            }
            input.value = null;
            atualizar();
        }
    };


    $scope.cadastrar = function (cadastroCategoria) {
        var input = document.getElementById("InputIconeCategoriaCadastro");
        if (!validacaoService.vazio(toastr, cadastroCategoria, "Categoria")) {
            if (validacaoService.comprimento(toastr, cadastroCategoria.nome, 1, 35, "nome")
                    && validacaoService.conteudo(toastr, cadastroCategoria.nome, "nome")
                    && validacaoService.contemImagem(toastr, input.files)) {

                $http.post(webService + "/categoria", cadastroCategoria, loginService.getHeaders()).then(function (response) {


                    var icone = input.files[0];

                    if (!(!icone.type.match('image.*'))) {
                        enviarArquivo($http, icone, 'icone', webService + "/categoria/imagem/" + response.data.id, $scope.token);
                    }
                    input.value = null;

                    atualizar();
                    sucesso(toastr, "Categoria cadastrada com sucesso");
                }, function () {
                    erro(toastr, "Erro ao cadastrar categoria, por favor tente novamente mais tarde");
                });
            }
        }
    };

    $scope.recuperar = function () {
        $http.put(webService + "/categoria/recuperar/" + $scope.categoriaRecuperacaoId, loginService.getHeaders()).then(function () {
            atualizar();
            sucesso(toastr, "Categoria recuperada com sucesso");
        }, function () {
            erro(toastr, "Erro ao recuperar categoria, por favor tente novamente mais tarde");
        });
    };

    $scope.deletar = function () {
        $http.delete(webService + "/categoria/" + $scope.categoriaExclusaoId, loginService.getHeaders()).then(function () {
            atualizar();
            excluido(toastr, "Categoria deletada com sucesso");
        }, function () {
            erro(toastr, "Erro ao deletar categoria, por favor tente novamente mais tarde");
        });
    };

    $scope.baixarImagem = function (id) {
        return webService + "/categoria/imagem/" + id;
    };
    //funções de preparação de variaveis

    $scope.prepararRecuperacao = function (id) {
        $scope.categoriaRecuperacaoId = id;
    };

    $scope.cancelarRecuperacao = function () {
        $scope.categoriaRecuperacaoId = null;
    };
    $scope.prepararEdicao = function (categoria) {
        $scope.categoriaEdicao = categoria;
    };

    $scope.cancelarEdicao = function () {
        $scope.categoriaEdicao = null;
    };

    $scope.prepararExclusao = function (id) {
        $scope.categoriaExclusaoId = id;
    };

    $scope.cancelarExclusao = function () {
        $scope.categoriaExclusaoId = null;
    };
    $scope.prepararEdicaoImagem = function (id) {
        $scope.categoriaImagemEdicaoId = id;
    };
    $scope.cancelarEdicaoImagem = function () {
        $scope.categoriaImagemEdicaoId = null;
    };

    //funções de atualizações e avisos
    function atualizar() {
        loginService.verificarToken($http, toastr, "Administrador", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.listarExcluidas();
        $scope.listarNaoExcluidas();
        $scope.cancelarEdicao();
        $scope.cancelarExclusao();
        $scope.cancelarRecuperacao();
        $scope.cancelarEdicaoImagem();
        $scope.categoriaCadastro = "";
    }

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
    };
});
