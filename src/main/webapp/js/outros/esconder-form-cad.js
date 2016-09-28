/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Mudarestado(formAtivar, formDesativar) {

    //Variáveis
    var formAtivado = document.getElementById(formAtivar);
    var formDesativado = document.getElementById(formDesativar);


    $(formAtivado).fadeIn(1000);
    //Tornar um visível
    formAtivado.style.display = "block";

    //Tornar o outro invisível
    formDesativado.style.display = "none";

}

function selecionar(idBotaoAtivo, idBotaoInativo) {

    var botaoAtivo = document.getElementById(idBotaoAtivo);
    botaoAtivo.classList.add('botaoAtivo');

    var botaoInativo = document.getElementById(idBotaoInativo);
    botaoInativo.classList.remove('botaoAtivo');

}
