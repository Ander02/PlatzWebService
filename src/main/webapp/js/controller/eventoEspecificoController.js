angular.module("platz").controller("eventoEspecificoController", function ($scope, $http, toastr, loginService, validacaoService) {
    id = document.getElementById("idEvento").value;
    var enderecoCompletoEvento;

    $scope.iniciarMapa = function () {

        var myOptions = {
            zoom: 15,
            center: centro,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            mapTypeControl: true
        };

        $scope.map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

        var centro = new google.maps.LatLng(-23.550520, -46.633309);

        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({
            'address': enderecoCompletoEvento
        }, function (results, status) {
            // Se o status da busca é ok
            if (status == google.maps.GeocoderStatus.OK) {

                latitude = results[0].geometry.location.lat();
                longitude = results[0].geometry.location.lng();

                $scope.map.setCenter(new google.maps.LatLng(latitude, longitude));
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(latitude, longitude),
                    title: $scope.evento.nome,
                    map: $scope.map
                });
            }
        });

        $scope.directionsDisplay = new google.maps.DirectionsRenderer();
        $scope.directionsDisplay.setMap($scope.map);
        $scope.directionsDisplay.setPanel(document.getElementById("directionsPanel"));

    };

    // Calcula Rota
    $scope.calcularRota = function () {

        var travelModeSelected;

        var tipoViagemSelect = document.getElementById("tipoViagemSelect").value;

        if (tipoViagemSelect === "DRIVING") {
            travelModeSelected = google.maps.DirectionsTravelMode.DRIVING;
        } else if (tipoViagemSelect === "TRANSIT") {
            travelModeSelected = google.maps.DirectionsTravelMode.TRANSIT;
        } else {
            travelModeSelected = google.maps.DirectionsTravelMode.DRIVING;
        }

        var directionsService = new google.maps.DirectionsService();
        var request = new Object();

        if (document.getElementById("checkboxLocalizacaoAtual").checked) {
            console.log(document.getElementById("checkboxLocalizacaoAtual").checked);

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {

                    //
                    var geocoder = new google.maps.Geocoder();

                    geocoder.geocode({
                        'location': new google.maps.LatLng(parseFloat(position.coords.latitude), parseFloat(position.coords.longitude))
                    }, function (results, status) {

                        request = {
                            origin: results[0].formatted_address,
                            destination: enderecoCompletoEvento,
                            travelMode: travelModeSelected,
                            optimizeWaypoints: true
                        };

                        directionsService.route(request, function (response, status) {
                            if (status == google.maps.DirectionsStatus.OK) {
                                for (var i = 0; i < response.routes.length; i++) {
                                    $scope.directionsDisplay.setDirections(response);
                                    $scope.directionsDisplay.setRouteIndex(i);
                                    console.log($scope.directionsDisplay);
                                }
                            }
                        });
                    });
                });
            }
        } else {
            request = {
                origin: document.getElementById("pontoInicial").value,
                destination: enderecoCompletoEvento,
                travelMode: travelModeSelected,
                optimizeWaypoints: true
            };

            directionsService.route(request, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    for (var i = 0; i < response.routes.length; i++) {
                        $scope.directionsDisplay.setDirections(response);
                        $scope.directionsDisplay.setRouteIndex(i);
                        console.log($scope.directionsDisplay);
                    }
                }
            });

        }

    };

    $scope.eventoEspecifico = function () {
        $http.get(webService + "/evento/" + id).then(function (response) {
            $scope.evento = response.data;
            console.log($scope.evento);
            $scope.imagemCapa = webService + "/evento/imagemCapa/" + id;
            enderecoCompletoEvento = $scope.evento.endereco.cep + " " + $scope.evento.endereco.rua + " " + $scope.evento.endereco.cidade.nome + " " + $scope.evento.endereco.cidade.estado.uf;
            $scope.iniciarMapa();
        }, function () {
            aviso(toastr, "código de evento invalido");
        });
    };

    $scope.participar = function (partipacao) {

        if ($scope.conta !== null && $scope.conta !== "" && typeof $scope.conta !== 'undefined' && $scope.conta.perfil !== "Administrador") {

            var presenca = {
                "contaId": $scope.conta.id,
                "eventoId": id,
                "tipoPresenca": partipacao
            };

            $http.post(webService + "/presenca", presenca, loginService.getHeaders()).then(function (response) {
                atualizar();
            }, function () {

            });
        } else {
            pedidoLogin("Por favor, realize o login como usuario ou empresa para ter acesso a essa funcionalidade");
        }
    };

    $scope.avaliar = function (nota) {

        if ($scope.usuario !== null && $scope.usuario !== "" && typeof $scope.usuario !== 'undefined') {
            avaliacao = {
                nota: nota,
                eventoId: id,
                usuarioId: $scope.usuario.id
            };

            $http.post(webService + "/avaliar", avaliacao, loginService.getHeaders()).then(function (response) {
                atualizar();
            }, function () {

            });

        } else {
            pedidoLogin("Por favor, realize o login como usuario para ter acesso a essa funcionalidade");
        }

    };

    $scope.curtir = function () {

        if ($scope.usuario !== null && $scope.usuario !== "" && typeof $scope.usuario !== 'undefined') {
            $scope.curtido = !$scope.curtido;

            curtida = {
                eventoId: id,
                usuarioId: $scope.usuario.id,
                curtida: $scope.curtido
            };
            $http.post(webService + "/curtir", curtida, loginService.getHeaders()).then(function (response) {
            }, function () {
            });



        } else {
            pedidoLogin("Por favor, realize o login como usuario para ter acesso a essa funcionalidade");
        }
    };

    var pedidoLogin = function (mensagem) {
        toastr.clear();
        generateToastr(toastr, mensagem, "info", "Realize o login", true, true, 0, 2500);
        atualizar();
    };

    $scope.buscaUsuario = function () {
        $http.get(webService + "/usuario/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.usuario = response.data;
            $http.get(webService + "/avaliacao/evento/" + id + "/usuario/" + $scope.usuario.id, loginService.getHeaders()).then(function (response) {
                $scope.notaUsuario = response.data.nota;
            }, function () {
            });

            $http.get(webService + "/curtidas/evento/" + id + "/usuario/" + $scope.usuario.id, loginService.getHeaders()).then(function (response) {
                $scope.curtido = response.data.curtido;
            }, function () {
            });
            $scope.buscaParticipacao();
        }, function () {
        });
    };

    $scope.buscaEmpresa = function () {
        $http.get(webService + "/empresa/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.empresa = response.data;
            $scope.buscaParticipacao();
        }, function () {
        });
    };

    $scope.buscaParticipacao = function () {

        $http.get(webService + "/presenca/evento/" + id + "/conta/" + $scope.conta.id, loginService.getHeaders()).then(function (response) {
            $scope.participacao = response.data.tipoPresenca;
        }, function () {
        });
    };

    $scope.comentar = function (comentario) {
        if (validacaoService.comprimento(comentario, 8, 4096) && validacaoService.conteudo(comentario)) {

            if ($scope.conta !== null && $scope.conta !== "" && typeof $scope.conta !== 'undefined' && $scope.conta.perfil !== "Administrador") {
                postagem = {
                    contaId: $scope.conta.id,
                    eventoId: id,
                    conteudo: comentario
                };
                $http.post(webService + "/postagem", postagem, loginService.getHeaders()).then(function () {
                    sucesso(toastr, "Cometario realizado");
                    $scope.comentario = '';
                    atualizar();
                }, function () {
                    aviso(toastr, "falhar ao comentar, tente novamente mais tarde");
                });

            } else {
                pedidoLogin("Por favor, realize o login como usuario ou empresa para ter acesso a essa funcionalidade");
            }
        } else {
            aviso(toastr, " O comentário deve ter entre 8 há 4096 digitos");
        }
    };

    $scope.listarPostagem = function () {
        $http.get(webService + "/postagem/evento/" + id, loginService.getHeaders()).then(function (response) {
            $scope.postagens = response.data;
            for (var i = 0; i < $scope.postagens.length; i++) {
                if ($scope.postagens[i].usuario === null) {
                    $scope.postagens[i].conta = $scope.postagens[i].empresa.conta;
                } else if ($scope.postagens[i].empresa === null) {
                    $scope.postagens[i].conta = $scope.postagens[i].usuario.conta;
                }
            }
        }, function () {
        });
    };

    $scope.baixarImagem = function (postagem) {
        if (postagem.usuario !== null) {
            return webService + "/usuario/imagem/" + postagem.usuario.id;
        } else if (postagem.empresa !== null) {
            return webService + "/empresa/imagem/" + postagem.empresa.id;
        } else {
            return "/img/outras/teste-perfil.jpg";
        }

    };

    $scope.getMedia = function () {
        $http.get(webService + "/avaliacao/evento/media/" + id).then(function (response) {
            $scope.media = parseFloat(response.data);
            $scope.mediaArredondada = Math.round($scope.media);
        }, function (response) {
            console.log(response.data);
        });
    };

    $scope.bloquearEvento = function () {
        $http.put(webService + "/evento/censurar/" + id, null, loginService.getHeaders()).then(function (response) {
            console.log(response.data);
            sucesso(toastr, "Evento censurado");
            atualizar();
        }, function () {
            aviso(toastr, "falha ao censurar evento, tente novamente mais tarde");
        });
    };
    $scope.bloquearPostagem = function () {

    };
    $scope.denuciarEvento = function (mensagem) {
        var email;
        if ($scope.conta !== undefined) {
            email = $scope.conta.email;
        } else {
            email = $scope.emailDenunciaEvento;
        }

        $http.get(webService + "/assuntos/Denúncia", loginService.getHeaders()).then(function (response) {
            console.log(response.data);

            $scope.denunciaEvento = {
                conteudo: "Denúncia no evento: " + location.href + "  Mensagem do usuário:" + mensagem,
                assuntoId: response.data[0].id,
                email: email
            };

            $http.post(webService + "/mensagem", $scope.denunciaEvento, loginService.getHeaders()).then(function () {
                sucesso(toastr, "Denúncia realizada");
            }, function () {
                aviso(toastr, "falha ao realizar denúncia tente novamente mais tarde");
            });
        }, function () {
            aviso(toastr, "falha ao realizar denúncia tente novamente mais tarde");
        });
    };
    $scope.denuciarPostagem = function () {

    };
    $scope.ExcluirPostagem = function () {

    };
    $scope.editarPostagem = function (novoComentario) {

    };
    $scope.prepararDenunciaPostagem = function (postagem) {

    };
    $scope.prepararBloqueamentoPostagem = function (postagem) {

    };
    $scope.prepararEdicaoPostagem = function (postagem) {

    };
    $scope.prepararExclusaoPostagem = function (postagem) {

    };
    $scope.cancelarDenunciaPostagem = function (postagem) {

    };
    $scope.cancelarBloqueamentoPostagem = function (postagem) {

    };
    $scope.cancelarEdicaoPostagem = function (postagem) {

    };
    $scope.cancelarExclusaoPostagem = function (postagem) {

    };


    function atualizar() {
        loginService.verificarToken($http, toastr, "Livre", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
            if ($scope.conta.perfil === "Usuario") {
                $scope.buscaUsuario();
            } else if ($scope.conta.perfil === "Empresa") {
                $scope.buscaEmpresa();
            }
        });
        $scope.eventoEspecifico();
        $scope.getMedia();
        $scope.listarPostagem();
    }



    window.onload = function () {
        $scope.usuario = "";
        $scope.permicao = false;
        atualizar();
    };

});
