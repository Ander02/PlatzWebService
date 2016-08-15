package com.platz.service;

import com.platz.controller.ContaController;
import com.platz.http.cadastro.ContaCadastro;
import com.platz.http.leitura.ContaLeitura;
import com.platz.model.ContaModel;
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
public class ContaService {

    private final ContaController contaController = new ContaController();

    @POST
    @Path(value = "/conta")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(ContaCadastro conta) {
        //Instanciar uma nova model
        ContaModel model = new ContaModel();

        try {
            //Settar informações na model baseado na Conta de Cadastro passada
            model.setEmail(conta.getEmail());
            model.setSenha(conta.getSenha());
            model.setPerfil(conta.getPerfil());

            contaController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Conta de Leitura
            return Response.status(Response.Status.CREATED).entity(new ContaLeitura(model)).build();

        } catch (Exception e) {

            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar conta").build();
        }
    }

    @GET
    @Path(value = "/contas")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {

        try {
            //Lista com todas as ContasaModels cadastradas
            List<ContaModel> models = contaController.listarTodos();
            //Converter a lista de models para uma lista de leitura
            List<ContaLeitura> listaDeContas = new ContaLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeContas).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar contas").build();
        }

    }

    @GET
    @Path(value = "/conta/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        ContaModel model = contaController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new ContaLeitura(model)).build();

        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build();
    }

    @GET
    @Path(value = "/conta/email/{email}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEmail(@PathParam("email") String email) {
        ContaModel model = contaController.buscarPeloEmail(email);

        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new ContaLeitura(model)).build();
        }
        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build();

    }

    @GET
    @Path(value = "/contas/inativos")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarInativos() {

        try {
            //Lista com todas as ContasaModels cadastradas
            List<ContaModel> models = contaController.buscarInativos();
            //Converter a lista de models para uma lista de leitura
            List<ContaLeitura> listaDeContas = new ContaLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeContas).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar contas").build();
        }

    }

    @GET
    @Path(value = "/contas/ativos")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarAtivos() {

        try {
            //Lista com todas as ContasaModels cadastradas
            List<ContaModel> models = contaController.buscarAtivos();
            //Converter a lista de models para uma lista de leitura
            List<ContaLeitura> listaDeContas = new ContaLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeContas).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar contas").build();
        }
    }

}
