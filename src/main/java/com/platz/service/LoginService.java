package com.platz.service;

import com.platz.controller.ContaController;
import com.platz.http.cadastro.Login;
import com.platz.http.leitura.ContaLeitura;
import com.platz.model.ContaModel;
import com.platz.util.EncriptAES;
import com.platz.util.TokenUtil;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
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
            String senhaEncriptada = new EncriptAES().byteParaString(new EncriptAES().encrypt(login.getSenha(), EncriptAES.getChaveEncriptacao()));

            //autenticar usuario
            ContaModel model = contaController.getConta(login.getEmail(), senhaEncriptada);

            if (model != null) {
                String token = new TokenUtil().criarToken(model.getId(), model.getEmail());
                model.setToken(token);
                contaController.alterar(model);

                //Retornar token na resposta
                return Response.ok(new ContaLeitura(model)).header(HttpHeaders.AUTHORIZATION, token).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário ou senhas incorretos").build();

            }
        } catch (Exception e) {
            System.out.println("Erro ao logar: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário ou senhas incorretos").build();
        }

    }

}
