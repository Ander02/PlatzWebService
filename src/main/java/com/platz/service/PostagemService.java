package com.platz.service;

import com.platz.controller.ContaController;
import com.platz.controller.EventoController;
import com.platz.controller.PostagemController;
import com.platz.http.cadastro.PostagemCadastro;
import com.platz.http.edicao.PostagemEdicao;
import com.platz.http.leitura.PostagemLeitura;
import com.platz.model.Perfil;
import com.platz.model.PostagemModel;
import com.platz.util.PerfilAuth;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
public class PostagemService {

    private final PostagemController postagemController = new PostagemController();

    @POST
    @Path(value = "/postagem")
    @PermitAll
    //@PerfilAuth({Perfil.USUARIO, Perfil.EMPRESA})
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(PostagemCadastro postagem) {

        PostagemModel model = new PostagemModel(postagem);
        try {
            // Cadastrar 
            postagemController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e o Postagem de Leitura
            return Response.status(Response.Status.CREATED).entity(new PostagemLeitura(model)).build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar postagem").build();
        }
    }

    @GET
    @Path(value = "/postagens")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<PostagemModel> models = postagemController.listarTodos();

            //Lista de Assuntos de Leitura baseado na lista de models
            List<PostagemLeitura> listaDePresenca = new PostagemLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDePresenca).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());            
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar postagem").build();
        }
    }

    @GET
    @Path(value = "/postagem/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        PostagemModel model = postagemController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {
            //Retorna um Status Code OK com a Postagem de leitura
            return Response.ok(new PostagemLeitura(model)).build();
        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("postagem não encontrada").build();
    }

    @GET
    @Path(value = "/postagem/evento/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEvento(@PathParam("id") String id) {
        try {
            List<PostagemModel> models = postagemController.buscarPeloEvento(new EventoController().buscarPorId(id));

            //Lista de Assuntos de Leitura baseado na lista de models
            List<PostagemLeitura> listaDeLeitura = new PostagemLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar Postagens").build();
        }

    }

    @GET
    @Path(value = "/postagem/conta/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPelaConta(@PathParam("id") String id) {
        try {
            List<PostagemModel> models = postagemController.buscarPelaConta(new ContaController().buscarPorId(id));

            //Lista de Assuntos de Leitura baseado na lista de models
            List<PostagemLeitura> listaDeLeitura = new PostagemLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar Postagens").build();
        }

    }

    @PUT
    @Path(value = "/postagem/{id}")
    @PerfilAuth({Perfil.USUARIO, Perfil.EMPRESA})
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response alterar(@PathParam("id") String id, PostagemEdicao postagem) {
        try {
            PostagemModel model = postagemController.buscarPorId(id);
            postagemController.alterar(model, postagem);
            return Response.ok(new PostagemLeitura(model)).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar Postagem").build();
        }
    }

    @DELETE
    @Path(value = "/postagem/{id}")
    @PerfilAuth({Perfil.ADMINISTRADOR, Perfil.EMPRESA, Perfil.USUARIO})
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response excluir(@PathParam("id") String id) {
        try {
            postagemController.excluir(postagemController.buscarPorId(id));
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao excluir Postagem").build();
        }
    }

}
