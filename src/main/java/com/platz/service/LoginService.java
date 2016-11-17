package com.platz.service;

import com.platz.controller.ContaController;
import com.platz.http.cadastro.Login;
import com.platz.http.leitura.ContaLeitura;
import com.platz.model.ContaModel;
import com.platz.model.Perfil;
import com.platz.util.PerfilAuth;
import com.platz.util.TokenUtil;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author Anderson
 */
@Path("")
public class LoginService {

    private final ContaController contaController = new ContaController();

    @POST
    @PermitAll
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        try {

            ContaModel model = contaController.login(login);
            //Retornar token na resposta
            if (model != null) {
                return Response.ok(new ContaLeitura(model)).header(HttpHeaders.AUTHORIZATION, model.getToken()).build();
            }

            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário e/ou senhas incorretos").build();

        } catch (Exception e) {
            System.out.println("Erro ao logar: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário e/ou senhas incorretos").build();
        }
    }

    @POST
    @PermitAll
    @Path("/loginAndroid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAndroid(Login login) {
        try {

            ContaModel model = contaController.loginAndroid(login);
            //Retornar token na resposta
            if (model != null) {
                return Response.ok(new ContaLeitura(model)).header(HttpHeaders.AUTHORIZATION, model.getToken()).build();
            }

            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário e/ou senhas incorretos").build();

        } catch (Exception e) {
            System.out.println("Erro ao logar: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário e/ou senhas incorretos").build();
        }
    }

    @POST
    //@PerfilAuth({Perfil.ADMINISTRADOR, Perfil.EMPRESA, Perfil.USUARIO})
    @PermitAll
    @Path("/logoff")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoff(@Context HttpHeaders httpHeaders) {

        try {
            //Pegar o authorization header
            MultivaluedMap<String, String> headers = httpHeaders.getRequestHeaders();
            List<String> authorizations = headers.get(HttpHeaders.AUTHORIZATION);
            if (authorizations == null || authorizations.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao fazer logoff").build();
            }

            //Pegar o token
            String token = authorizations.get(0).replaceFirst("Bearer ", "");

            //Pegar conta pelo token, anular o token e alterar o registro
            ContaModel model = contaController.getConta(token);
            contaController.logoff(model);

            return Response.ok().build();
        } catch (Exception e) {
            System.out.println("Erro ao fazer logoff: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao fazer logoff").build();
        }
    }

    @GET
    @PerfilAuth({Perfil.ADMINISTRADOR, Perfil.EMPRESA, Perfil.USUARIO})    
    @Path("/tokenIsValid/{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response tokenIsValid(@PathParam("token") String token) {
        if (contaController.getConta(token) != null) {
            boolean valido = new TokenUtil().isValid(token);
            if (!valido) {
                contaController.logoff(contaController.getConta(token));
            }
            return Response.ok(valido).build();
        }
        return Response.ok(false).build();

    }

    @GET
    @PerfilAuth({Perfil.ADMINISTRADOR, Perfil.EMPRESA, Perfil.USUARIO})
    @Path("/tokenAndroidIsValid/{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response tokenAndroidIsValid(@PathParam("token") String token) {
        if (contaController.getContaPorTokenAndroid(token) != null) {
            boolean valido = new TokenUtil().isValid(token);
            if (!valido) {
                contaController.logoff(contaController.getContaPorTokenAndroid(token));
            }
            return Response.ok(valido).build();
        }
        return Response.ok(false).build();

    }
}
