package com.platz.service;

import com.platz.controller.AvaliacaoController;
import com.platz.controller.EventoController;
import com.platz.controller.UsuarioController;
import com.platz.http.cadastro.AvaliacaoCadastro;
import com.platz.http.edicao.AvaliacaoEdicao;
import com.platz.http.leitura.AvaliacaoLeitura;
import com.platz.model.AvaliacaoModel;
import com.platz.model.Perfil;
import com.platz.util.PerfilAuth;
import java.util.List;
import javax.annotation.security.DenyAll;
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
 * @author Anderson
 */
@Path("")
public class AvaliacaoService {

    private final AvaliacaoController avaliacaoController = new AvaliacaoController();

    @POST
    @Path(value = "/avaliar")
    @PerfilAuth(Perfil.USUARIO)
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
            e.printStackTrace();
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar avaliação").build();
        }
    }

    @GET
    @Path(value = "/avaliacoes")
    @PerfilAuth(Perfil.ADMINISTRADOR)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {

            //Lista com todas as models, e converte para uma lista de leitura respondendo com um status code OK
            return Response.ok(new AvaliacaoLeitura().converterLista(avaliacaoController.listarTodos())).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }
    }

    @GET
    @Path(value = "/avaliacao/{id}")
    @PermitAll
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
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEvento(@PathParam("id") String id) {
        try {
            List<AvaliacaoModel> models = avaliacaoController.buscarPeloEvento(new EventoController().buscarPorId(id));

            //Retorna a lista com um Status Code OK
            return Response.ok(new AvaliacaoLeitura().converterLista(models)).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }

    }

    @GET
    @Path(value = "/avaliacao/usuario/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloUsuario(@PathParam("id") String id) {
        try {
            List<AvaliacaoModel> models = avaliacaoController.buscarPeloUsuario(new UsuarioController().buscarPorId(id));

            //Retorna a lista com um Status Code OK
            return Response.ok(new AvaliacaoLeitura().converterLista(models)).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }
    }

    @GET
    @Path(value = "/avaliacao/evento/{idEvento}/usuario/{idUsuario}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEventoEUsuario(@PathParam("idEvento") String idEvento, @PathParam("idUsuario") String idUsuario) {
        try {
            AvaliacaoModel model = avaliacaoController.buscarPeloEventoEUsuario(new EventoController().buscarPorId(idEvento), new UsuarioController().buscarPorId(idUsuario));

            //Retorna a lista com um Status Code OK
            return Response.ok(new AvaliacaoLeitura(model)).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar avaliações").build();
        }

    }

    @GET
    @Path(value = "/avaliacao/evento/media/{id}")
    @PermitAll
    @Produces(value = MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public Response mediaPorEvento(@PathParam("id") String id) {
        try {
            return Response.ok(avaliacaoController.mediaPorEvento(new EventoController().buscarPorId(id))).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao buscar media da avaliação").build();
        }
    }

    @PUT
    @Path(value = "/avaliacao/{id}")
    @DenyAll
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
    @PerfilAuth(Perfil.USUARIO)
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
