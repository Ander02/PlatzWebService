package com.platz.service;

import com.platz.controller.CidadeController;
import com.platz.controller.EstadoController;
import com.platz.http.cadastro.CidadeCadastro;
import com.platz.http.leitura.CidadeLeitura;
import com.platz.model.CidadeModel;
import com.platz.model.EstadoModel;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
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
public class CidadeService {

    private final CidadeController cidadeController = new CidadeController();

    @POST
    @Path(value = "/cidade")
    @DenyAll
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(CidadeCadastro cidade) {

        try {
            // Settar o nome da model baseado no nome do assunto passado
            CidadeModel model = new CidadeModel(cidade);

            // Cadastrar cidade
            cidadeController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e o Assunto de Leitura
            return Response.status(Response.Status.CREATED).entity(new CidadeLeitura(model)).build();

        } catch (Exception e) {
            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar cidade").build();
        }
    }

    @GET
    @Path(value = "/cidades")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as models, e converte para uma lista de leitura respondendo com um status code OK
            return Response.ok(new CidadeLeitura().converterLista(cidadeController.listarTodos())).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar cidades").build();
        }
    }

    @GET
    @Path(value = "/cidade/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        CidadeModel model = cidadeController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {
            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new CidadeLeitura(model)).build();
        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Cidade não encontrada").build();
    }

    @GET
    @Path(value = "/cidades/{nome}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNome(@PathParam("nome") String nome) {
        try {
            //Lista com todas as models cadastradas
            List<CidadeModel> models = cidadeController.buscarPeloNome(nome);

            //Retorna a lista com um Status Code OK
            return Response.ok(new CidadeLeitura().converterLista(models)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar cidades").build();
        }
    }

    @GET
    @Path(value = "/cidades/estado/{id}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEstado(@PathParam("id") String id) {
        try {
            EstadoModel estado = new EstadoController().buscarPorId(id);

            //Lista com todas as AssuntoEntity cadastradas
            List<CidadeModel> models = cidadeController.buscarPeloEstado(estado);

            //Retorna a lista com um Status Code OK
            return Response.ok(new CidadeLeitura().converterLista(models)).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar cidades").build();
        }
    }

    @GET
    @Path(value = "/cidade/{uf}/{nome}")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloNomeEUf(@PathParam("nome") String nome, @PathParam("uf") String uf) {

        CidadeModel model = cidadeController.buscarPeloNomeEUf(nome, uf);

        //Verifica se a model retornada não é nula
        if (model != null) {
            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new CidadeLeitura(model)).build();
        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Cidade não encontrada").build();
    }

}
