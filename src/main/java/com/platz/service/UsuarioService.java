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
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
        try {
            //Instanciar uma nova model
            UsuarioModel model = new UsuarioModel(usuario);

            usuarioController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Mensagem de Leitura
            return Response.status(Response.Status.CREATED).entity(new UsuarioLeitura(model)).build();

        } catch (Exception e) {

            //Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar usuario").build();
        }
    }

    @GET
    @Path(value = "/usuarios")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {

        try {
            //Lista com todas as Models cadastradas
            List<UsuarioModel> models = usuarioController.listarTodos();
            //Converter a lista de models para uma lista de leitura
            List<UsuarioLeitura> listaDeUsuarios = new UsuarioLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeUsuarios).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar usuarios").build();
        }
    }

    @GET
    @Path(value = "/usuarios/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {

        try {
            //Lista com todas as Models cadastradas
            List<UsuarioModel> models = usuarioController.buscarPeloNome(nome);
            //Converter a lista de models para uma lista de leitura
            List<UsuarioLeitura> listaDeUsuarios = new UsuarioLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeUsuarios).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar usuarios").build();
        }
    }

    @GET
    @Path(value = "/usuario/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        UsuarioModel model = usuarioController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new UsuarioLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrada").build();
    }

    @GET
    @Path(value = "/usuario/cpf/{cpf}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloCPF(@PathParam("cpf") String cpf) {
        UsuarioModel model = usuarioController.buscarPeloCPF(cpf);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new UsuarioLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrada").build();
    }

}
