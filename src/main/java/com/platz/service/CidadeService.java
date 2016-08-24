package com.platz.service;

import com.platz.controller.CidadeController;
import com.platz.http.cadastro.CidadeCadastro;
import com.platz.http.leitura.CidadeLeitura;
import com.platz.model.CidadeModel;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Anderson
 */
@Path("")
public class CidadeService {

    private final CidadeController cidadeController = new CidadeController();

    @POST
    @Path(value = "/assunto")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(CidadeCadastro cidade) {

        try {
            // Settar o nome da model baseado no nome do assunto passado
            CidadeModel model = new CidadeModel();

            // Cadastrar assunto
            cidadeController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e o Assunto de Leitura
            return Response.status(Response.Status.CREATED).entity(new CidadeLeitura(model)).build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar assunto").build();
        }
    }

    @GET
    @Path(value = "/cidades")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as AssuntoEntity cadastradas
            List<CidadeModel> models = cidadeController.listarTodos();

            //Lista de Assuntos de Leitura baseado na lista de models
            List<CidadeLeitura> listaDeAssuntos = new CidadeLeitura().converterLista(models);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeAssuntos).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar cidades").build();
        }
    }

}
