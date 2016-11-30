angular.module("platz").controller("cadastroController", function ($scope, $http, toastr, validacaoService) {
    $scope.conta;
    $scope.onblurCepEmpresa = function () {
        cep = document.getElementById("empresa-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {
            console.log(response.data);
            $scope.empresa.endereco.rua = response.data.logradouro;
            $scope.empresa.endereco.bairro = response.data.bairro;
            $scope.empresa.endereco.cidade = response.data.localidade;
            $scope.empresa.endereco.uf = response.data.uf;
        }, function () {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });
    };
    $scope.onblurCepUsuario = function () {
        cep = document.getElementById("usuario-cep").value;
        $http.get("https://viacep.com.br/ws/" + cep + "/json/").then(function (response) {
            $scope.usuario.endereco.cep = response.data.cep;
            $scope.usuario.endereco.rua = response.data.logradouro;
            $scope.usuario.endereco.bairro = response.data.bairro;
            $scope.usuario.endereco.cidade = response.data.localidade;
            $scope.usuario.endereco.uf = response.data.uf;
        }, function () {
            aviso(toastr, "CEP inexistente, por favor verifique-o");
        });
    };
    $scope.cadastrarEmpresa = function () {
        if (!validacaoService.vazio(toastr, $scope.empresa, "Empresa")) {
            if (!validacaoService.vazio(toastr, $scope.empresa.conta, "Empresa - conta")) {
                if (!validacaoService.vazio(toastr, $scope.empresa.endereco, "Empresa - endereço")) {
                    if (validacaoService.comprimento(toastr, $scope.empresa.conta.email, 1, 50, "Email") && validacaoService.conteudo(toastr, $scope.empresa.conta.email, "Email")) {
                        if (!validacaoService.vazio(toastr, $scope.empresa.conta.senha, "Senha") && validacaoService.comprimento(toastr, $scope.empresa.conta.senha, 4, 40, "Senha")) {
                            if (validacaoService.comprimento(toastr, $scope.empresa.razaoSocial, 1, 70, "Razão Social") && validacaoService.conteudo(toastr, $scope.empresa.razaoSocial, "Razão Social")) {
                                if (validacaoService.comprimento(toastr, $scope.empresa.nomeFantasia, 1, 50, "nome fantasia") && validacaoService.conteudo(toastr, $scope.empresa.nomeFantasia, "nome fantasia")) {
                                    if (validacaoService.conteudo(toastr, $scope.empresa.cnpj, "CNPJ") && validacaoService.comprimento(toastr, $scope.empresa.cnpj, 18, 18, "CNPJ")) {
                                        if (validacaoService.conteudo(toastr, $scope.empresa.telefone, "Telefone") && validacaoService.comprimento(toastr, $scope.empresa.telefone, 1, 15, "Telefone")) {
                                            if (validacaoService.comprimento(toastr, $scope.empresa.endereco.cep, 8, 9, "Cep")) {
                                                if ($scope.empresa.conta.senha === $scope.empresa.conta.confirmaSenha) {
                                                    $scope.empresa.perfil = 1;
                                                    $http.post(webService + "/empresa", $scope.empresa).then(function (response) {

                                                        $scope.conta = response.data.conta;
                                                        $scope.conta.senha = $scope.empresa.conta.senha;
                                                        var input = document.getElementById("conta-empresa-img");
                                                        var imagemPerfil = input.files[0];
                                                        if (!(!imagemPerfil.type.match('image.*'))) {
                                                            enviarArquivo($http, imagemPerfil, 'imgPerfil', webService + "/empresa/imagem/" + response.data.id);
                                                        }

                                                        $scope.empresa = null;
                                                        input.value = null;

                                                        sucesso(toastr, "Empresa cadastrada com sucesso");
                                                        $("#modalLogar").modal();

                                                    }, function (response) {
                                                        erro(toastr, errorManager(response.config.url, response.status, "Falha ao cadastrar empresa, verifique os campos e tente novamente"));
                                                    });
                                                } else {
                                                    aviso(toastr, "Senhas digitadas não são iguais, por favor digige novamente");
                                                    $scope.empresa.conta.senha = null;
                                                    $scope.empresa.conta.confirmaSenha = null;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };
    $scope.cadastrarUsuario = function () {
        console.log($scope.usuario);
        if (!validacaoService.vazio(toastr, $scope.usuario, "Usuário")) {
            if (!validacaoService.vazio(toastr, $scope.usuario.conta, "Usuário - conta")) {
                if (!validacaoService.vazio(toastr, $scope.usuario.endereco, "Usuário - endereço")) {
                    if (validacaoService.comprimento(toastr, $scope.usuario.conta.email, 1, 50, "Email") && validacaoService.conteudo(toastr, $scope.usuario.conta.email, "Email")) {
                        if (!validacaoService.vazio(toastr, $scope.usuario.conta.senha, "Senha") && validacaoService.comprimento(toastr, $scope.usuario.conta.senha, 4, 40, "Senha")) {

                            if (validacaoService.comprimento(toastr, $scope.usuario.nome, 3, 64, "nome") && validacaoService.conteudo(toastr, $scope.usuario.nome, "nome")) {
                                if (validacaoService.conteudo(toastr, $scope.usuario.cpf, "CPF") && validacaoService.comprimento(toastr, $scope.usuario.cpf, 14, 14, "CPF")) {
                                    if (validacaoService.conteudo(toastr, $scope.usuario.telefone, "Telefone") && validacaoService.comprimento(toastr, $scope.usuario.telefone, 1, 15, "Telefone")) {
                                        if (validacaoService.comprimento(toastr, $scope.usuario.endereco.cep, 8, 9, "Cep")) {

                                            if ($scope.usuario.conta.senha === $scope.usuario.conta.confirmaSenha) {
                                                //$scope.usuario.endereco.uf = "SP";
                                                $scope.usuario.perfil = 2;
                                                $scope.usuario.dataNascimento = document.getElementById("date").value;
                                                $scope.usuario.cpf = document.getElementById("conta-usuario-cpf").value;
                                                console.log($scope.usuario);
                                                $http.post(webService + "/usuario", $scope.usuario).then(function (response) {

                                                    $scope.conta = response.data.conta;
                                                    $scope.conta.senha = $scope.usuario.conta.senha;
                                                    var input = document.getElementById("conta-usuario-img");
                                                    var imagemPerfil = input.files[0];
                                                    console.log(imagemPerfil);
                                                    if (!(!imagemPerfil.type.match('image.*'))) {
                                                        enviarArquivo($http, imagemPerfil, 'imgPerfil', webService + "/usuario/imagem/" + response.data.id);
                                                    }

                                                    $scope.usuario = null;
                                                    input.value = null;

                                                    sucesso(toastr, "Usuario cadastrado com sucesso");

                                                    $("#modalLogar").modal();

                                                }, function () {
                                                    erro(toastr, "Falha ao cadastrar usuario, verifique os campos e tente novamente mais tarde");
                                                });
                                            } else {
                                                aviso(toastr, "Senhas digitadas não são iguais, por favor divige novamente");
                                                $scope.usuario.conta.senha = null;
                                                $scope.usuario.conta.confirmaSenha = null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };
    $scope.logar = function () {
        login = {
            email: $scope.conta.email,
            senha: $scope.conta.senha
        };
        $http.post(webService + "/login", login).then(function (response) {
            $scope.contaLogin = response.data;
            location.href = "sessao.jsp?token=" + $scope.contaLogin.token + "&perfil=" + $scope.contaLogin.perfil;
        }, function () {
            erro(toastr, "usuario ou senha incorreto");
        });
    };
});
