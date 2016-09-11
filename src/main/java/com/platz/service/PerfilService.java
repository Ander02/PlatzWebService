package com.platz.service;

import com.platz.controller.PerfilController;
import com.platz.http.leitura.PerfilLeitura;
import com.platz.model.Perfil;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Anderson
 */
@Path("")
public class PerfilService {

    private final PerfilController perfilController = new PerfilController();

    @GET
    @Path(value = "/perfis")
    @PermitAll
    @Produces(value = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response listarTodos() {
        try {
            //Lista com todas as CategoriaModels cadastradas
            List<Perfil> perfilEnumList = perfilController.listarTodos();

            //Lista de Perfis de Leitura baseado na lista de perfis de enum
            List<PerfilLeitura> listaDePerfis = new PerfilLeitura().converterLista(perfilEnumList);

            //Retorna a lista com um Status Code OK
            return Response.ok(listaDePerfis).build();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());

            //Retorna uma BadRequest ao usuário
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao listar perfis").build();
        }
    }

}
