/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}
function errorManager(erro, status, mensagem) {
    switch (status) {
        case 200:
            alert("Status ok: erro não identificado" + " - " + erro);
            break;
        case 201:
            alert("Status Criado: erro não identificado" + " - " + erro);
            break;
        case 400:
            alert("Status Bad Request: " + mensagem + " - " + erro);
            break;
        case 401:
            alert("Status Não autorizado: " + mensagem + " - " + erro);            
            break;
        case 403:
            alert("Status Proibido: " + mensagem + " - " + erro);
            break;
        case 404:
            alert("Status Não Encotrado: " + mensagem + " - " + erro);
            break;
        case 500:
            alert("Erro de servidor, por favor contate o administrador do sitema e relate o erro: " + mensagem + " - " + erro);
            break;
        default:
            alert("Erro não identificado. " + mensagem + " - " + erro);
            break;
    }
}


