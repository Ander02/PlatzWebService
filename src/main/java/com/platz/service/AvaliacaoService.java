package com.platz.service;

import com.platz.controller.AvaliacaoController;
import com.platz.controller.EventoController;
import com.platz.controller.UsuarioController;
import com.platz.http.cadastro.AvaliacaoCadastro;
import com.platz.http.edicao.AvaliacaoEdicao;
import com.platz.http.leitura.AvaliacaoLeitura;
import com.platz.model.AvaliacaoModel;
import java.util.List;
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
 * @author Anderson
 */
@Path("")
public class AvaliacaoService {

    private final AvaliacaoController avaliacaoController = new AvaliacaoController();

    @POST
    @Path(value = "/avaliacao")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(AvaliacaoCadastro avaliacao) {

        AvaliacaoModel model = new AvaliacaoModel(avaliacao);
        try {
            // Cadastrar assunto
            avaliacaoController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e o Assunto de Leitura
            return Response.status(Response.Status.CREATED).entity(new AvaliacaoLeitura(model)).build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar avaliação").build();
        }
    }

    @GET
    @Path(value = "/avaliacoes")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<AvaliacaoModel> models = avaliacaoController.listarTodos();

            //Lista de Assuntos de Leitura baseado na lista de models
            List<AvaliacaoLeitura> listaDeAvaliacoes = new AvaliacaoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeAvaliacoes).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }
    }

    @GET
    @Path(value = "/avaliacao/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        AvaliacaoModel model = avaliacaoController.buscarPorId(id);

        //Verifica se a entidade retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com o Assunto de leitura
            return Response.ok(new AvaliacaoLeitura(model)).build();

        }

        //Se a entity for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Avaliação não encontrada").build();
    }

    @GET
    @Path(value = "/avaliacao/evento/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEvento(@PathParam("id") String id) {
        try {
            List<AvaliacaoModel> models = avaliacaoController.buscarPeloEvento(new EventoController().buscarPorId(id));

            //Lista de Leitura baseado na lista de models
            List<AvaliacaoLeitura> listaDeLeitura = new AvaliacaoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }

    }

    @GET
    @Path(value = "/avaliacao/usuario/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloUsuario(@PathParam("id") String id) {
        try {
            List<AvaliacaoModel> models = avaliacaoController.buscarPeloUsuario(new UsuarioController().buscarPorId(id));

            //Lista de Leitura baseado na lista de models
            List<AvaliacaoLeitura> listaDeLeitura = new AvaliacaoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }
    }

    @GET
    @Path(value = "/avaliacao/evento/media/{id}")
    @Produces(value = MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public Response mediaPorEvento(@PathParam("id") String id) {
        try {
            Double media = avaliacaoController.mediaPorEvento(new EventoController().buscarPorId(id));
            return Response.ok(media).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao buscar media da avaliação").build();
        }
    }

    @PUT
    @Path(value = "/avaliacao/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") String id, AvaliacaoEdicao avaliacao) {
        try {
            AvaliacaoModel model = avaliacaoController.buscarPorId(id);
            avaliacaoController.alterar(model, avaliacao);
            return Response.ok(new AvaliacaoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar avaliações").build();
        }
    }

    @DELETE
    @Path(value = "/avaliacao/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response deletar(@PathParam("id") String id) {
        try {
            avaliacaoController.excluir(avaliacaoController.buscarPorId(id));

            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao excluir avaliação").build();
        }
    }

}
