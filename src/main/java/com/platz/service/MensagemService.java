package com.platz.service;

import com.platz.controller.AssuntoController;
import com.platz.controller.MensagemController;
import com.platz.http.cadastro.MensagemCadastro;
import com.platz.http.leitura.MensagemLeitura;
import com.platz.model.MensagemModel;
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
public class MensagemService {
    
    private final MensagemController mensagemController = new MensagemController();
    
    @POST
    @Path(value = "/mensagem")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(MensagemCadastro mensagem) {
        //Instanciar uma nova model
        MensagemModel model = new MensagemModel();
        
        try {
            //Settar informações na model baseado na Conta de Cadastro passada
            model.setEmail(mensagem.getEmail());
            model.setConteudo(mensagem.getConteudo());
            model.setAssunto(new AssuntoController().buscarPorId(mensagem.getAssuntoId()));
            
            mensagemController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Mensagem de Leitura
            return Response.status(Response.Status.CREATED).entity(new MensagemLeitura(model)).build();
            
        } catch (Exception e) {

            //Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar mensagem").build();
        }
    }
    
    @GET
    @Path(value = "/mensagens")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        
        try {
            //Lista com todas as ContasaModels cadastradas
            List<MensagemModel> models = mensagemController.listarTodos();
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeContas = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeContas).build();
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar contas").build();
        }
        
    }
    
    @GET
    @Path(value = "/mensagem/{id}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloId(@PathParam("id") String id) {
        MensagemModel model = mensagemController.buscarPorId(id);

        //Verifica se a model retornada não é nula
        if (model != null) {

            //Retorna um Status Code OK com a conta de leitura
            return Response.ok(new MensagemLeitura(model)).build();
            
        }

        //Se a model for nula retorna um Status Code Not Found
        return Response.status(Response.Status.NOT_FOUND).entity("Conta não encontrada").build();
    }
    
    @GET
    @Path(value = "/mensagens/{email}")
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response buscarPeloEmail(@PathParam("email") String email) {
        
        try {
            //Lista com todas as ContasaModels cadastradas
            List<MensagemModel> models = mensagemController.buscarPeloEmail(email);
            //Converter a lista de models para uma lista de leitura
            List<MensagemLeitura> listaDeContas = new MensagemLeitura().converterLista(models);
            //Retorna a lista com um Status Code OK
            return Response.ok(listaDeContas).build();
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar contas").build();
        }
        
    }
    
}
