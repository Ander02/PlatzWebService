angular.module("platz").service("loginService", function () {
    var conta;
    var token;
    var permicao;

    this.getConta = function () {
        return conta;
    };

    this.getToken = function () {
        return token;
    };

    this.getPermicao = function () {
        return permicao;
    };

    this.verificarToken = function ($http, toastr, local, success) {

        try {
            token = document.getElementById("token").value;
            console.log(token);
            $http.get(webService + "/tokenIsValid/" + token, {
                headers: {
                    Authorization: "Bearer " + token
                }
            }).then(function (response) {
                var valido = response.data;

                if (valido == "false") {
                    console.log("t isn't valid");
                    permicao = false;
                    logoff($http, toastr);
                    location.href = "../login.jsp";
                } else {
                    console.log("t is valid ");
                    $http.get(webService + "/conta/token/" + token, {
                        headers: {
                            Authorization: "Bearer " + token
                        }
                    }).then(function (response) {

                        conta = response.data;

                        if (local === "Livre" || local === conta.perfil) {
                            permicao = true;
                            success();
                        } else {
                            logoff($http, toastr);
                        }

                    }, function (response) {
                        permicao = false;
                        logoff($http, toastr);
                        location.href = "../login.jsp";
                    });
                }

            }, function (response) {
                console.log("request failed");
                permicao = false;
                logoff($http, toastr);
            });
        } catch (err) {
            aviso(toastr, "falha ao manter sessão, por favor logue-se novamente");
            logoff($http, toastr);
            location.href = "../login.jsp";
        }
    };

//gera os headers para autorização e permição
    this.getHeaders = function () {
        return {
            headers: {
                Authorization: "Bearer " + token
            }
        };
    };

    this.logoff = logoff;

    var logoff = function ($http, toastr) {
        aviso(toastr, "Erro ao logar, por favor tente novamente");
        $http.post(webService + "/logoff", null, {
            headers: {
                Authorization: "Bearer " + token
            }
        });
        location.href = "../login.jsp";
    };
});
