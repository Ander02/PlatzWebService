app.controller("eventosController", function ($scope, $http, toastr, loginService) {

    $scope.inicializarMapa = function () {

        var centro = new google.maps.LatLng(-23.550520, -46.633309);

        // Opções padrões do mapa
        var myOptions = {
            zoom: 12,
            center: centro,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            mapTypeControl: true
        };

        // Objeto do tipo mapa
        var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

        // Se o navegador do usuário tem suporte ao Geolocation
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                /*
                 * Com a latitude e longitude que retornam do Geolocation, criamos
                 * um LatLng onde definimos a latitude e longitude acima como centro
                 * do mapa
                 */
                map.setCenter(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));

                // Janela de Informações
                var infowindow = new google.maps.InfoWindow({
                    content: "<h4>" + "Você Está Aqui" + "</h4>"
                });

                // Marcador com a sua localização
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
                    map: map,
                    title: "Você está aqui",
                    icon: 'css/icon/pointer.png'
                });

                //Evento de Click no marker
                marker.addListener("click", function () {
                    infowindow.open(map, marker);
                });

            });
        }
        // Objeto do Google Geocoder
        var geocoder = new google.maps.Geocoder();
        $http.get(webService + "/eventos/top/" + 100).then(function (response) {
            $scope.adicionarEventoNoMapa(0, response.data, geocoder, map);
        }, function () {
        });

    };

    $scope.adicionarEventoNoMapa = function (i, eventosDestaque, geocoder, map) {
        geocoder.geocode({
            'address': eventosDestaque[i].endereco.cep
        }, function (results, status) {
            // Se o status da busca é ok
            if (status == google.maps.GeocoderStatus.OK) {
                // Set a latitude
                latitude = results[0].geometry.location.lat();
                // Set a longitude
                longitude = results[0].geometry.location.lng();

                // Marcador
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(latitude, longitude),
                    map: map,
                    icon: 'css/icon/pointer2.png'

                });
                // Janela de Informações
                var infowindow = new google.maps.InfoWindow({
                    content: "<h4 class='card-title'>" + eventosDestaque[i].nome + "</h4>" +
                            "<h5><i class='fa fa-building-o animated bounceInDown'></i>" + eventosDestaque[i].empresa.nomeFantasia + "</h5>" +
                            "<h6><i class='fa fa-calendar animated bounceInDown'></i>" + eventosDestaque[i].dataInicio + "</h6>" +
                            "<h6><i class='fa fa-map-marker animated bounceInDown'></i>" + eventosDestaque[i].endereco.rua + " - " + eventosDestaque[i].endereco.bairro + "</h6>" +
                            "<h6><a class='btn btn-warning' href='eventoEspecifico.jsp?evento=" + eventosDestaque[i].id + "' role='button'>Ver Mais Detalhes &raquo;</a></h6>"
                });

                //Evento de Click no marker
                marker.addListener("click", function () {
                    infowindow.open(map, marker);
                });
                i++;
                if (i < eventosDestaque.length) {
                    $scope.adicionarEventoNoMapa(i, eventosDestaque, geocoder, map);
                }
            }
        });
    };

    $scope.listarCategoriasNaoExcluidas = function () {
        $http.get(webService + "/categorias/naoExcluidas").then(function (response) {
            $scope.categorias = response.data;
        }, function () {
        });
    };

    $scope.listarEventos = function () {
        $http.get(webService + "/eventos/naoCensurados").then(function (response) {
            $scope.eventos = response.data;
        }, function () {
        });
    };


    $scope.listarTop3Eventos = function () {
        $http.get(webService + "/eventos/top/" + 3).then(function (response) {
            $scope.top3Eventos = response.data;
        }, function () {
        });
    };


    $scope.listarTop15Eventos = function () {
        $http.get(webService + "/eventos/top/" + 15).then(function (response) {
            index = 1;
            var evento3 = new Array();
            var eventoTop15 = new Array();
            do {
                for (var i = index - 1; i < index + 2; i++) {
                    evento3.push(response.data[i]);
                }
                eventoTop15.push(evento3);
                index += 3;
                evento3 = new Array();
            } while (index <= response.data.length);
            $scope.top15Eventos = eventoTop15;

        }, function () {
        });
    };

    $scope.listarTop10Eventos = function () {
        $http.get(webService + "/eventos/top/" + 10).then(function (response) {
            $scope.top10Eventos = response.data;
        }, function () {
        });
    };


    $scope.baixarImagemCategoria = function (id) {
        return webService + "/categoria/imagem/" + id;
    };

    $scope.buscarImagemCapa = function (id) {
        return webService + "/evento/imagemCapa/" + id;
    };

//funções de atualizações
    function atualizar() {
        loginService.verificarToken($http, toastr, "Livre", function () {
            $scope.permicao = loginService.getPermicao();
            $scope.conta = loginService.getConta();
            $scope.token = loginService.getToken();
        });
        $scope.listarEventos();
        $scope.listarCategoriasNaoExcluidas();
        $scope.listarTop3Eventos();
        $scope.listarTop15Eventos();
        $scope.listarTop10Eventos();
    }

    window.onload = function () {
        $scope.permicao = false;
        atualizar();
        $scope.inicializarMapa();
    };


});
