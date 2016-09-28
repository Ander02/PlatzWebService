if (navigator.serviceWorker) {
    navigator.serviceWorker.register('./sw.js').then(function () {
        console.log('Funcionou');
    }).catch(function () {
        console.log('não funcionou');
    })
}