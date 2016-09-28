self.addEventListener('install', function (evento) {
    evento.waitUntil(
            caches.open('Platz-cache').then(function (cache) {
        return cache.addAll([
            '/',
            'js/main.js'

        ])
    })
            )
});

self.addEventListener('fetch', function (evento) {
    evento.respondWith(
            caches.match(evento.request).then(function (response) {
        if (response)
            return response;

        return fetch(evento.request);
    })
            )

})