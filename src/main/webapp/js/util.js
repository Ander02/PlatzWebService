/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//função que gerar um delay no codigo
function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}

//Faz o upload de um arquivo para o service
function enviarArquivo($http, arquivo, name, url, token) {
    var formData = new FormData();
    formData.append(name, arquivo);
    console.log(formData);
    $http.put(url, formData, {        
        transformRequest: angular.identity,
        headers: {
            'Content-Type': undefined,
             Authorization: "Bearer " + token
        }
    }).success(function (response) {
        console.log("Arquivo Enviado para " + url);
    }).error(function (response) {
        console.log("Erro ao enviar para " + url);
        console.log(response);
    });
}
/*
function verificarToken($http, $scope, toastr, sucess) {
    try {

        var token = document.getElementById("token").value;
        $scope.token = token;
        $http.get(webService + "/tokenIsValid/" + token).then(function (response) {
            var valido = response.data;

            if (valido == "false") {
                console.log("t isn't valid");
                $scope.permicao = false;
                console.log($scope.permicao);
                logoff($http, toastr, token);
                location.href = "../login.jsp";
            } else {
                console.log("t is valid" + token);
                $http.get(webService + "/conta/token/" + token).then(function (response) {
                    $scope.permicao = true;
                    console.log($scope.permicao);
                    $scope.conta = response.data;
                    //console.log($scope.conta);
                    var regExp = new RegExp("/" + $scope.conta.perfil);
                    if (!regExp.test(window.location.href)) {
                        $scope.permicao = false;
                        console.log($scope.permicao);
                        $scope.conta = null;
                        logoff($http, toastr, token);
                        location.href = "../login.jsp";
                    } else {
                    }
                }, function (response) {
                    $scope.permicao = false;
                    console.log($scope.permicao);
                    logoff($http, toastr, token);
                    location.href = "../login.jsp";
                });
            }

        }, function (response) {
            console.log("request failed");
            $scope.permicao = false;
            console.log($scope.permicao);
            logoff($http, toastr, token);
        });
    } catch (err) {
        aviso(toastr, "falha ao manter sessão, por favor logue-se novamente");
        location.href = "../login.jsp";
        logoff($http, toastr, token);
    }

}*/

//funções que gerencia os tipo de erro
function errorManager(erro, status, mensagem) {
    switch (status) {
        case 200:
            return "Status: ok. - erro não identificado" + " - " + erro;
            break;
        case 201:
            return "Status: Criado. - erro não identificado" + " - " + erro;
            break;
        case 400:
            return "Status: Bad Request. - " + mensagem + " - " + erro;
            break;
        case 401:
            return "Status: Não autorizado. - " + mensagem + " - " + erro;
            break;
        case 403:
            return "Status: Proibido. - " + mensagem + " - " + erro;
            break;
        case 404:
            return "Status: Não Encontrado. - " + mensagem + " - " + erro;
            break;
        case 500:
            return "Erro de servidor, por favor contate o administrador do sitema e relate o erro: " + mensagem + " - " + erro;
            break;
        default:
            return status + "Erro não identificado. " + mensagem + " - " + erro;
            break;
    }
}

//funções de aviso para o usuario do sistema
function sucesso(toastr, mensagem) {
    toastr.success(mensagem, 'Sucesso', {
        progressBar: true,
        closeButton: true,
        timeOut: 5000,
        extendTimeOut: 2000
    });
}
function espere(toastr, mensagem) {
    toastr.info(mensagem, 'Enviando', {
        progressBar: true,
        closeButton: true,
        timeOut: 0,
        extendTimeOut: 5000
    });
}
function confirmacao(toastr, mensagem) {
    toastr.success(mensagem, 'Confirmação', {
        progressBar: true,
        closeButton: true,
        timeOut: 1000,
        extendTimeOut: 500
    });
}
function excluido(toastr, mensagem) {
    toastr.success(mensagem, 'Deletado', {
        progressBar: true,
        closeButton: true,
        timeOut: 5000,
        extendTimeOut: 2000
    });
}
function alterado(toastr, mensagem) {
    toastr.success(mensagem, 'Alterado', {
        progressBar: true,
        closeButton: true,
        timeOut: 5000,
        extendTimeOut: 2000
    });
}

function info(toastr, mensagem) {
    toastr.info(mensagem, 'Informações', {
        progressBar: true,
        closeButton: true,
        timeOut: 5000,
        extendTimeOut: 2000
    });
}
function erro(toastr, mensagem) {
    toastr.error(mensagem, 'Erro', {
        progressBar: true,
        closeButton: true,
        timeOut: 0,
        extendTimeOut: 3000
    });
}
function aviso(toastr, mensagem) {
    toastr.warning(mensagem, 'Aviso', {
        progressBar: true,
        closeButton: true,
        timeOut: 0,
        extendTimeOut: 3000
    });
}



