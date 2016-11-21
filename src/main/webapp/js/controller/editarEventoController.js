angular.module("platz").controller("editarEventoController", function ($scope, $http, toastr, loginService) {
    id = document.getElementById("idEvento").value;
    $scope.eventoEspecifico = function () {
        $http.get(webService + "/evento/" + id).then(function (response) {
            if (response.data.empresa.id === $scope.empresa.id) {
                $scope.evento = response.data;
                $scope.carregarInformacoes();
                $scope.imagemCapa = webService + "/evento/imagemCapa/" + id;
            } else {
                location.href = "/Empresa/index.jsp";
            }

        }, function (response) {
            location.href = "/Empresa/index.jsp";
        });
    };
    $scope.editar = function (evento) {
        evento.dataInicio = document.getElementById("datetimepicker-start-edit").value;
        evento.dataFim = document.getElementById("datetimepicker-end-edit").value;

        if (document.getElementById("evento-gratuito").checked) {
            evento.preco = 0;
        }
        console.log(evento);
        $http.put(webService + "/evento/" + id, evento, loginService.getHeaders()).then(function (response) {
            sucesso(toastr, "Evento Alterado com sucesso");
            location.href = "/eventoEspecifico.jsp?evento=" + id;
        }, function (response) {
            aviso(toastr, "Erro ao alterar evento, por favor verifique as informações e tente novamente");
        });
    };
    $scope.cancelar_Ativar = function () {
        if ($scope.evento.cancelado === undefined) {
            //cancelar
            $http.put(webService + "/evento/cancelar/" + id, null, loginService.getHeaders()).then(function (response) {
                info(toastr, "Evento cancelado com sucesso");
            }, function (response) {
                aviso(toastr, "falha ao cancelar o evento, atualize a pagina e tente novamente mais tarde");

            });
        } else {
            //reativar
            $http.put(webService + "/evento/descancelar/" + id, null, loginService.getHeaders()).then(function (response) {
                info(toastr, "Evento Ativado com sucesso");
            }, function (response) {
                aviso(toastr, "falha ao ativar o evento, atualize a pagina e tente novamente mais tarde");
            });
        }
    };

    $scope.carregarInformacoes = function () {
        $scope.eventoEdicao = new Object();
        $scope.eventoEdicao.nome = $scope.evento.nome;
        $scope.eventoEdicao.detalhes = $scope.evento.detalhes;
        $scope.eventoEdicao.lotacaoMin = $scope.evento.lotacaoMax;
        $scope.eventoEdicao.lotacaoMax = $scope.evento.lotacaoMin;
        $scope.eventoEdicao.dataFim = $scope.evento.dataFim;
        $scope.eventoEdicao.dataInicio = $scope.evento.dataInicio;
        $scope.eventoEdicao.idade = $scope.evento.idade;
        $scope.eventoEdicao.preco = $scope.evento.preco;
        $scope.eventoEdicao.endereco = new Object();
        $scope.eventoEdicao.endereco.cep = $scope.evento.endereco.cep;
        $scope.eventoEdicao.endereco.numero = $scope.evento.endereco.numero;
        $scope.eventoEdicao.endereco.rua = $scope.evento.endereco.rua;
        $scope.eventoEdicao.endereco.cep = $scope.evento.endereco.cep;
        $scope.eventoEdicao.endereco.complemento = $scope.evento.endereco.complemento;
        $scope.eventoEdicao.endereco.bairro = $scope.evento.endereco.bairro;
        $scope.eventoEdicao.endereco.cidade = $scope.evento.endereco.cidade.nome;
    };

    $scope.onblurCepEvento = function () {
        cep = document.getElementById("evento-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {

            $scope.eventoEdicao.endereco.rua = response.data.logradouro;
            $scope.eventoEdicao.endereco.bairro = response.data.bairro;
            $scope.eventoEdicao.endereco.cidade = response.data.localidade;
            $scope.eventoEdicao.endereco.uf = response.data.uf;

        }, function (response) {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });
    };



    $scope.buscaEmpresa = function () {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.empresa = response.data;
            $scope.imagemPerfil = webService + "/empresa/imagem/" + $scope.empresa.id;
            $scope.eventoEspecifico();
        }, function (response) {
        });
    };
    $scope.deslogar = function () {
        $http.post(webService + "/logoff", null, loginService.getHeaders()).then(function (response) {
            info(toastr, "logoff efetuado");
            location.href = "../index.jsp";
        }, function (response) {

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
