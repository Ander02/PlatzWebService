angular.module("platz").service("validacaoService", function () {

    this.conteudo = function (toastr, string, nomeCampo) {
        //expressao regular
        aviso(toastr, "o campo " + nomeCampo + " não deve ter os caracteres ABC");
        return true;
    };

    this.comprimento = function (toastr, string, min, max, nomeCampo) {
        if (string.length < min || string.length > max) {
            aviso(toastr, "O campo " + nomeCampo + " deve ter de " + min + " há " + max + " caracteres");
            return false;
        }
        return true;

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
