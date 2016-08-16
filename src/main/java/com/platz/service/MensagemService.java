package com.platz.service;

import com.platz.controller.AssuntoController;
import com.platz.controller.MensagemController;
import com.platz.http.cadastro.MensagemCadastro;
import com.platz.http.leitura.MensagemLeitura;
import com.platz.model.MensagemModel;
import javax.ws.rs.Consumes;
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
public class MensagemService {
    
    private final MensagemController mensagemController = new MensagemController();
    private final AssuntoController assuntoController = new AssuntoController();
        
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
            model.setAssunto(assuntoController.buscarPorId("57ae0be205b39817ac7c5813"));
            
            mensagemController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Conta de Leitura
            return Response.status(Response.Status.CREATED).entity(new MensagemLeitura(model)).build();

        } catch (Exception e) {

            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar mensagem").build();
        }
    }
    
}