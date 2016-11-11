/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var checkboxGratuito = document.getElementById("evento-gratuito");
var inputPreco = document.getElementById("evento-preco");

checkboxGratuito.addEventListener("change", function (ev) {
    console.log("inputPreco");
    if (checkboxGratuito.checked) {
        inputPreco.disabled = true;
    } else {
        inputPreco.disabled = false;
    }
});

