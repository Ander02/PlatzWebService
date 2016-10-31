angular.module("platz").service("loginService", function () {
    this.conta;
    this.token;
    this.permicao = false;

    this.getConta = function () {
        return this.conta;
    };

    this.getToken = function () {
        return this.token;
    };
    
    this.getPermicao = function () {
        return this.permicao;
    };

    this.verificarToken = function ($http, toastr, local, success) {

        try {
            this.token = document.getElementById("token").value;
            console.log(this.token);
            $http.get(webService + "/tokenIsValid/" + this.token).then(function (response) {
                var valido = response.data;

                if (valido == "false") {
                    console.log("t isn't valid");
                    this.permicao = false;
                    this.logoff($http, toastr);
                    location.href = "../login.jsp";
                } else {
                    console.log("t is valid " + this.token);
                    $http.get(webService + "/conta/token/" + this.token).then(function (response) {

                        this.conta = response.data;
                        this.verificarPermicao(local, $http, toastr, success);
                    }, function (response) {
                        this.permicao = false;
                        this.logoff($http, toastr);
                        location.href = "../login.jsp";
                    });
                }

            }, function (response) {
                console.log("request failed");
                this.permicao = false;
                logoff($http, toastr);
            });
        } catch (err) {
            aviso(toastr, "falha ao manter sessão, por favor logue-se novamente");
            this.logoff($http, toastr);
            location.href = "../login.jsp";
        }
    };

    this.verificarPermicao = function (local, $http, toastr, success) {
        if (local === "Livre" || local === this.conta.perfil) {
            this.permicao = true;
            success();
        } else {
            this.logoff($http, toastr);
        }
    };

    this.logoff = function ($http, toastr) {
        aviso(toastr, "Erro ao logar, por favor tente novamente");
        $http.post(webService + "/logoff", null, gerarHeaders(this.token));
        location.href = "../login.jsp";
    };
});
