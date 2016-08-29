package com.platz.service;

import com.platz.controller.EventoController;
import com.platz.http.cadastro.EventoCadastro;
import com.platz.http.leitura.EventoLeitura;
import com.platz.model.EventoModel;
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
 * @author Anderson
 */
@Path("")
public class EventoService {

    private final EventoController eventoController = new EventoController();

    @POST
    @Path(value = "/evento")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(EventoCadastro evento) {

        try {
            //Settar informações na model baseado na Conta de Cadastro passada
            EventoModel model = new EventoModel(evento);

            eventoController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Conta de Leitura
            return Response.status(Response.Status.CREATED).entity(new EventoLeitura(model)).build();

        } catch (Exception e) {

            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar evento").build();
        }
    }

    @GET
    @Path(value = "/eventos")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<EventoModel> models = eventoController.listarTodos();

            //Lista de Assuntos de Leitura baseado na lista de models
            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

    @GET
    @Path(value = "/evento/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        EventoModel model = eventoController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new EventoLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Evento não encontrada").build();
    }

    @GET
    @Path(value = "/eventos/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {
        try {

            //Buscar Models pelo nome
            List<EventoModel> models = eventoController.buscarPeloNome(nome);

            //Lista de Assuntos de Leitura baseado na lista de entidades
            List<EventoLeitura> listaDeLeitura = new EventoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeLeitura).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar eventos").build();
        }
    }

}
