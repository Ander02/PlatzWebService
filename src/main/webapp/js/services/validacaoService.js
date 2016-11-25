angular.module("platz").service("validacaoService", function () {

    this.conteudo = function (toastr, string, nomeCampo) {
        //expressao regular
        
        var regExp = "";
        
        
        // aviso(toastr, "o campo " + nomeCampo + " não deve ter os caracteres $<>{}\\\"'");
        return true;
    };

    this.comprimento = function (toastr, string, min, max, nomeCampo) {
        if (string.length < min || string.length > max) {
            aviso(toastr, "O campo " + nomeCampo + " deve ter de " + min + " há " + max + " caracteres");
            return false;
        }
        return true;
    };

    this.valorMinimo = function (toastr, int, min, nomeCampo) {

        if (int < min) {
            aviso(toastr, "o campo " + nomeCampo + " deve ser maior ou igual ha " + min);
            return false;
        }
        return true;
    };

    this.valorMaximo = function (toastr, int, max, nomeCampo) {

        if (int > max) {
            aviso(toastr, "o campo " + nomeCampo + " deve ser menor ou igual ha" + max);
            return false;
        }
        return true;
    };

    this.valor = function (toastr, int, min, max, nomeCampo) {

        if (int > max || int < min) {
            aviso(toastr, "o campo " + nomeCampo + " deve ser um valor entre " + min + " e " + max);
            return false;
        }
        return true;
    };


});
