/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platz.service;

import com.platz.controller.UsuarioController;
import com.platz.http.cadastro.UsuarioCadastro;
import com.platz.http.leitura.UsuarioLeitura;
import com.platz.model.UsuarioModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 15153770
 */
@Path("")
public class UsuarioService {

    private final UsuarioController usuarioController = new UsuarioController();

    @POST
    @Path(value = "/usuario")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(UsuarioCadastro usuario) {

        //Instanciar uma nova model
        UsuarioModel model = new UsuarioModel(usuario);

        // Retorna a resposta para o cliente com o Status Code CREATED e a Mensagem de Leitura
        return Response.status(Response.Status.CREATED).entity(new UsuarioLeitura(model)).build();
       /* try {
            System.out.println("dhaskljfhsdnkl");
        } catch (Exception e) {

            //Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar usuario").build();
        }*/
    }
}
