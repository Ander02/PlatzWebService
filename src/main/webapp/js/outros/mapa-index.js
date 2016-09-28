var centro = new google.maps.LatLng(-23.550520, -46.633309);

var minhaImagem = 'css/icons/pointer3.png';
var imagemEventos = 'css/icons/pointer2.png';


// Matriz contando os inputs dos endereços dos eventos em destaque
var matrizDeInputsDeEnderecos = $(".enderecoCompleto");

// Matriz contando as strings dos endereços dos eventos em destaque
var eventosEmDestaqueEndereco = [];

//Matriz contendo os inputs com os detalhes
var matrizDeInputsDetalhes = $(".detalhesMapa");

var detalhesDosEventosEmDestaque = [];

for (var i = 0; i < matrizDeInputsDeEnderecos.length; i++) {
    eventosEmDestaqueEndereco[i] = matrizDeInputsDeEnderecos[i].value;
    detalhesDosEventosEmDestaque[i] = matrizDeInputsDetalhes[i].value;

    console.log(detalhesDosEventosEmDestaque[i]);

}

// Carregar o mapa assim que a página termina de carregar
window.onload = initialize();

// função que inicializa o mapa
function initialize() {

    // Opções padrões do mapa
    var myOptions = {
        zoom: 12,
        center: centro,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeControl: true
    }

    // Objeto do tipo mapa
    var map = new google.maps.Map(document.getElementById("map_canvas"),
            myOptions);

    // Se o navegador do usuário tem suporte ao Geolocation
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            /*
             * Com a latitude e longitude que retornam do Geolocation, criamos
             * um LatLng onde definimos a latitude e longitude acima como centro
             * do mapa
             */
            map.setCenter(new google.maps.LatLng(position.coords.latitude,
                    position.coords.longitude));

            // Marcador com a sua localização
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(position.coords.latitude,
                        position.coords.longitude),
                map: map,
                title: "você está aqui",
                icon: minhaImagem

            });

        });
    }

    // Objeto do Google Geocoder
    var geocoder = new google.maps.Geocoder();
    // Geocoder
    for (var i = 0; i < eventosEmDestaqueEndereco.length; i++) {

        geocoder.geocode({
            'address': eventosEmDestaqueEndereco[i]
        }, function (results, status) {
            // Se o status da busca é ok
            if (status == google.maps.GeocoderStatus.OK) {
                // Set a latitude
                latitude = results[0].geometry.location.lat();
                // Set a longitude
                longitude = results[0].geometry.location.lng();

                // Janela de Informações
                var infowindow = new google.maps.InfoWindow({
                    content: "<h4>" + "Detalhes Adicionais" + "</h4>"
                });

                // Marcador
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(latitude, longitude),
                    map: map,
                    icon: imagemEventos

                });

                //Evento de Click no marker
                marker.addListener("click", function () {
                    infowindow.open(map, marker);
                });
            }
        });
    }

} 