package com.platz.service;

import com.platz.controller.AssuntoController;
import com.platz.http.cadastro.AssuntoCadastro;
import com.platz.http.edicao.AssuntoEdicao;
import com.platz.http.leitura.AssuntoLeitura;
import com.platz.model.AssuntoModel;
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
public class AssuntoService {

    private final AssuntoController assuntoController = new AssuntoController();

    @POST
    @Path(value = "/assunto")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(AssuntoCadastro assunto) {
        AssuntoModel model = new AssuntoModel();

        try {
            // Settar o nome da model baseado no nome do assunto passado
            model.setNome(assunto.getNome());

            // Cadastrar assunto
            assuntoController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e o Assunto de Leitura
            return Response.status(Response.Status.CREATED).entity(new AssuntoLeitura(model)).build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar assunto").build();
        }
    }

    @GET
    @Path(value = "/assuntos")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<AssuntoModel> models = assuntoController.listarTodos();

            //Lista de Assuntos de Leitura baseado na lista de models
            List<AssuntoLeitura> listaDeAssuntos = new AssuntoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeAssuntos).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar assuntos").build();
        }
    }

    @GET
    @Path(value = "/assunto/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        AssuntoModel model = assuntoController.buscarPorId(id);

        //Verifica se a entidade retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com o Assunto de leitura
            return Response.ok(new AssuntoLeitura(model)).build();

        }

        //Se a entity for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Assunto não encontrada").build();
    }

    @GET
    @Path(value = "/assuntos/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {
        try {

            //Buscar Models pelo nome
            List<AssuntoModel> models = assuntoController.buscarPeloNome(nome);

            //Lista de Assuntos de Leitura baseado na lista de entidades
            List<AssuntoLeitura> listaDeAssuntos = new AssuntoLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeAssuntos).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar assuntos").build();
        }
    }

    @PUT
    @Path(value = "/assunto/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response alterar(@PathParam("id") String id, AssuntoEdicao assunto) {
        AssuntoModel model = new AssuntoModel();

        try {

            //Settar informações na model
            model.setId(id);
            model.setNome(assunto.getNome());
            
            //Alterar registro
            assuntoController.alterar(model);

            //Retorna Status Code OK com a entity de leitura com a modificação
            return Response.status(Response.Status.OK).entity(new AssuntoLeitura(model)).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar assunto").build();
        }
    }

    @DELETE
    @Path(value = "/assunto/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response deletar(@PathParam("id") String id) {
        try {

            AssuntoModel model = assuntoController.buscarPorId(id);

            assuntoController.excluir(model);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao alterar assunto").build();
        }
    }
}
