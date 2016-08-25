package com.platz.service;

import com.platz.controller.EstadoController;
import com.platz.http.cadastro.EstadoCadastro;
import com.platz.http.leitura.EstadoLeitura;
import com.platz.model.EstadoModel;
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
public class EstadoService {

    private final EstadoController estadoController = new EstadoController();

    @POST
    @Path(value = "/estado")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response cadastrar(EstadoCadastro estado) {

        try {
            //Settar informações na model baseado na Conta de Cadastro passada
            EstadoModel model = new EstadoModel(estado);

            estadoController.cadastrar(model);

            // Retorna a resposta para o cliente com o Status Code CREATED e a Conta de Leitura
            return Response.status(Response.Status.CREATED).entity(new EstadoLeitura(model)).build();

        } catch (Exception e) {

            // Envia erro pelo console
            System.out.println("Erro: " + e.getMessage());
            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar estado").build();
        }
    }

}
