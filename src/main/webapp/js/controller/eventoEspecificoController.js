angular.module("platz").controller("eventoEspecificoController", function ($scope, $http, toastr, loginService) {
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

        var start = document.getElementById("pontoInicial").value;
        var directionsService = new google.maps.DirectionsService();

        var request = {
            origin: start,
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
    };

    $scope.eventoEspecifico = function () {
        $http.get(webService + "/evento/" + id).then(function (response) {
            $scope.evento = response.data;
            $scope.imagemCapa = webService + "/evento/imagemCapa/" + id;
            enderecoCompletoEvento = $scope.evento.endereco.cep + " " + $scope.evento.endereco.rua;
            $scope.iniciarMapa();
        }, function (response) {
            console.log(response.data);
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

    $scope.listarPostagem = function () {
        $http.get(webService + "/postagens", loginService.getHeaders()).then(function (response) {
            $scope.postagens = response.data;
        }, function () {

        });
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

    $scope.getMedia = function () {
        $http.get(webService + "/avaliacao/evento/media/" + id).then(function (response) {
            $scope.media = parseFloat(response.data);
            $scope.mediaArredondada = Math.round($scope.media);
        }, function (response) {
            console.log(response.data);
        });
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
