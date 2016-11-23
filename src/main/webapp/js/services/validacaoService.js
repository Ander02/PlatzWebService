angular.module("platz").service("validacaoService", function () {

    this.conteudo = function (string){
        //expressao regular
        return true;
    };

    this.comprimento = function (string, min, max) {
        return !(string.length < min || string.length > max);
    };

    this.valorMinimo = function (int, min) {
        return int >= min;
    };

    this.valorMaximo = function (int, max) {
        return int <= max;
    };
    
    this.valor = function (int, min, max) {
        return int <= max && int >= min;
    };


});
