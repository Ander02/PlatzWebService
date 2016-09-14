package com.platz.service;

import com.platz.controller.ContaController;
import com.platz.controller.EventoController;
import com.platz.controller.PresencaController;
import com.platz.http.cadastro.PresencaCadastro;
import com.platz.http.edicao.PresencaEdicao;
import com.platz.http.leitura.PresencaLeitura;
import com.platz.model.Perfil;
import com.platz.model.PresencaModel;
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
public class PresencaService {

    private final PresencaController presencaController = new PresencaController();

    @POST
    @Path(value = "/presenca")
    @PerfilAuth(Perfil.USUARIO)
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(PresencaCadastro presenca) {

        PresencaModel model = new PresencaModel(presenca);
        try {
            // Cadastrar assunto
            presencaController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e o Assunto de Leitura
            return Response.status(Response.Status.CREATED).entity(new PresencaLeitura(model)).build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar presença").build();
        }
    }

    @GET
    @Path(value = "/presencas")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<PresencaModel> models = presencaController.listarTodos();

            //Lista de Assuntos de Leitura baseado na lista de models
            List<PresencaLeitura> listaDePresenca = new PresencaLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDePresenca).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar presença").build();
        }
    }

    @GET
    @Path(value = "/presenca/{id}")
    @PermitAll

    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        PresencaModel model = presencaController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {
            //Retorna um Status Code OK com a Presenca de leitura
            return Response.ok(new PresencaLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Presença não encontrada").build();
    }

    @GET
    @Path(value = "/presenca/evento/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEvento(@PathParam("id") String id) {
        try {
            List<PresencaModel> models = presencaController.buscarPeloEvento(new EventoController().buscarPorId(id));

            //Lista de Assuntos de Leitura baseado na lista de models
            List<PresencaLeitura> listaDeLeitura = new PresencaLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar Presenças").build();
        }

    }

    @GET
    @Path(value = "/presenca/conta/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPelaConta(@PathParam("id") String id) {
        try {
            List<PresencaModel> models = presencaController.buscarPeloConta(new ContaController().buscarPorId(id));

            //Lista de Assuntos de Leitura baseado na lista de models
            List<PresencaLeitura> listaDeLeitura = new PresencaLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar Presenças").build();
        }
    }

    @PUT
    @Path(value = "/presenca/{id}")
    @PerfilAuth(Perfil.USUARIO)
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response alterar(@PathParam("id") String id, PresencaEdicao presenca) {
        try {
            PresencaModel model = presencaController.buscarPorId(id);
            presencaController.alterar(model, presenca);
            return Response.ok(new PresencaLeitura(model)).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar Presença").build();
        }
    }

    @DELETE
    @Path(value = "/presenca/{id}")
    @PerfilAuth(Perfil.USUARIO)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response excluir(@PathParam("id") String id) {
        try {
            presencaController.excluir(presencaController.buscarPorId(id));
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao excluir Presença").build();
        }
    }

}
